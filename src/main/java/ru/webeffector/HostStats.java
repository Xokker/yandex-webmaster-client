package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Class represents host.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts.xml
 *
 * @author Ernest Sadykov
 * @since 10.03.2014
 */
@XmlRootElement(name = "host")
@XmlAccessorType(XmlAccessType.FIELD)
public class HostStats {

    @XmlElement
    private String name;

    @XmlElement
    private Boolean virused;

    @XmlElement(name = "last-access")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private DateTime lastAccess;

    @XmlElement(name = "url-count")
    private Integer urlCount;

    @XmlElement(name = "index-count")
    private Integer indexCount;

    @XmlElement(name = "tcy")
    private Integer tcy;

    @XmlElement(name = "crawling")
    private Crawling crawling;

    @XmlElement(name = "internal-links-count")
    private Integer internalLinksCount;

    @XmlElement(name = "links-count")
    private Integer linksCount;

    @XmlElement(name = "url-errors")
    private Integer urlErrors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVirused() {
        return virused;
    }

    public void setVirused(Boolean virused) {
        this.virused = virused;
    }

    public DateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(DateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Integer getUrlCount() {
        return urlCount;
    }

    public void setUrlCount(Integer urlCount) {
        this.urlCount = urlCount;
    }

    public Integer getIndexCount() {
        return indexCount;
    }

    public void setIndexCount(Integer indexCount) {
        this.indexCount = indexCount;
    }

    public Integer getTcy() {
        return tcy;
    }

    public void setTcy(Integer tcy) {
        this.tcy = tcy;
    }

    public Crawling getCrawling() {
        return crawling;
    }

    public void setCrawling(Crawling crawling) {
        this.crawling = crawling;
    }

    public Integer getInternalLinksCount() {
        return internalLinksCount;
    }

    public void setInternalLinksCount(Integer internalLinksCount) {
        this.internalLinksCount = internalLinksCount;
    }

    public Integer getLinksCount() {
        return linksCount;
    }

    public void setLinksCount(Integer linksCount) {
        this.linksCount = linksCount;
    }

    public Integer getUrlErrors() {
        return urlErrors;
    }

    public void setUrlErrors(Integer urlErrors) {
        this.urlErrors = urlErrors;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("type", name)
                .append("virused", virused)
                .append("lastAccess", lastAccess)
                .append("urlCount", urlCount)
                .append("indexCount", indexCount)
                .append("tcy", tcy)
                .append("crawling", crawling)
                .toString();
    }
}

