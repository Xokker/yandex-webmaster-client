package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.persistence.oxm.annotations.XmlPath;
import ru.webeffector.model.SitemapAdditionalInfo;
import ru.webeffector.model.SitemapInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Collections;
import java.util.List;

/**
 * Represents one of the user site's Sitemap files and contains information
 * about it.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/sitemaps.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/sitemaps.xml
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Sitemap {

    @XmlPath("link[@rel='sitemap-content']/@href")
    private String sitemapUrl;

    @XmlPath("link[@rel='self']/@href")
    private String sitemapInfoUrl;

    /**
     * Verbose information about sitemap.
     */
    private SitemapInfo sitemapInfo;

    private Webmaster webmaster;

    Sitemap() {

    }

    void setWebmaster(Webmaster webmaster) {
        this.webmaster = webmaster;
    }

    public SitemapInfo getSitemapInfo(boolean reload) {
        if (reload || sitemapInfo == null) {
            sitemapInfo = webmaster.makeRequest(sitemapInfoUrl, SitemapInfo.class);
        }
        return sitemapInfo;
    }

    public SitemapInfo getSitemapInfo() {
        return getSitemapInfo(false);
    }

    /**
     * Gets a list of a user site's Sitemap child files and information about each of them.
     *
     * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/sitemap-id-children.xml
     * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/sitemap-id-children.xml
     *
     * @return list of sitemaps
     */
    public List<Sitemap> children() {
        if (getChildrenUrl() == null) {
            return Collections.emptyList();
        }
        return webmaster.makeRequest(getChildrenUrl(), SitemapList.class).sitemaps;
    }

    public String getChildrenUrl() {
        return getSitemapInfo().getChildrenUrl();
    }

    public SitemapAdditionalInfo getLatestInfo() {
        return getSitemapInfo().getLatestInfo();
    }

    public SitemapAdditionalInfo getInSearchInfo() {
        return getSitemapInfo().getInSearchInfo();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("sitemapUrl", sitemapUrl)
                .append("sitemapInfoUrl", sitemapInfoUrl)
                .append("sitemapInfo", sitemapInfo)
                .toString();
    }
}
