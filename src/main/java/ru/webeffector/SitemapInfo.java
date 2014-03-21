package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents verbose information about sitemap.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/sitemap-id.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/sitemap-id.xml
 *
 * @author Ernest Sadykov
 * @since 21.03.2014
 */
@XmlRootElement(name = "sitemap")
@XmlAccessorType(XmlAccessType.NONE)
class SitemapInfo {

    @XmlPath("link[@rel='sitemap-index-children']/@href")
    private String childrenUrl;

    @XmlPath("info[@type='latest']")
    private SitemapAdditionalInfo latestInfo;

    @XmlPath("info[@type='in-search']")
    private SitemapAdditionalInfo inSearchInfo;

    public String getChildrenUrl() {
        return childrenUrl;
    }

    public void setChildrenUrl(String childrenUrl) {
        this.childrenUrl = childrenUrl;
    }

    public SitemapAdditionalInfo getLatestInfo() {
        return latestInfo;
    }

    public void setLatestInfo(SitemapAdditionalInfo latestInfo) {
        this.latestInfo = latestInfo;
    }

    public SitemapAdditionalInfo getInSearchInfo() {
        return inSearchInfo;
    }

    public void setInSearchInfo(SitemapAdditionalInfo inSearchInfo) {
        this.inSearchInfo = inSearchInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("childrenUrl", childrenUrl)
                .append("latestInfo", latestInfo)
                .append("inSearchInfo", inSearchInfo)
                .toString();
    }
}
