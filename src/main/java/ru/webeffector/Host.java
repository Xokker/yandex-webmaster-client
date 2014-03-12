package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAttribute;
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
@XmlRootElement
public class Host {

    @XmlAttribute(name = "href")
    private String url;

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

    private History tcyHistory;

    @XmlElement
    private Verification verification;

    private Webmaster webmaster;

    private Links links;

    public Integer getUrlCount() {
        return urlCount;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Boolean isVirused() {
        return virused;
    }

    public DateTime getLastAccess() {
        return lastAccess;
    }

    public Integer getIndexCount() {
        return indexCount;
    }

    public Integer getTcy() {
        return tcy;
    }

    private void initHostStats() {
        Host host = webmaster.makeRequest(getLinks().get(LinkType.host_information));
        verification = host.verification;
        crawling = host.crawling;
        virused = host.virused;
        lastAccess = host.lastAccess;
        tcy = host.tcy;
        urlCount = host.urlCount;
        urlErrors = host.getUrlErrors();
        indexCount = host.getIndexCount();
        internalLinksCount = host.getInternalLinksCount();
        linksCount = host.getLinksCount();
    }

    public Integer getLinksCount() {
        if (linksCount == null) {
            initHostStats();
        }
        return linksCount;
    }

    public Integer getUrlErrors() {
        if (urlErrors == null) {
            initHostStats();
        }
        return urlErrors;
    }

    public IndexInfo getIndexInfo() {
        return webmaster.makeRequest(getLinks().get(LinkType.indexed_urls));
    }

    public Verification getVerification() {
        return verification;
    }

    public Crawling getCrawling() {
        return crawling;
    }

    void setWebmaster(Webmaster webmaster) {
        this.webmaster = webmaster;
    }


    public Integer getInternalLinksCount() {
        if (internalLinksCount == null) {
            initHostStats();
        }
        return internalLinksCount;
    }

    private Links getLinks() {
        if (links == null) {
            links = webmaster.makeRequest(url);
        }
        return links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("url", url)
                .append("type", name)
                .append("virused", virused)
                .append("lastAccess", lastAccess)
                .append("urlCount", urlCount)
                .append("indexCount", indexCount)
                .append("tcy", tcy)
                .append("verification", verification)
                .append("crawling", crawling)
                .toString();
    }
}

