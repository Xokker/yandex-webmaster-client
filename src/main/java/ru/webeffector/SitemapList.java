package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 21.03.2014
 */
@XmlRootElement(name = "sitemaps")
@XmlAccessorType(XmlAccessType.NONE)
class SitemapList {

    @XmlElement(name = "sitemap")
    List<Sitemap> sitemaps;

    SitemapList() {
        sitemaps = new ArrayList<>();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("sitemaps", sitemaps)
                .toString();
    }
}
