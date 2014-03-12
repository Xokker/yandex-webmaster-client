package ru.webeffector.host;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;
import ru.webeffector.Webmaster;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Map;

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
    private boolean virused;

    @XmlElement(name = "last-access")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private DateTime lastAccess;

    @XmlElement(name = "url-count")
    private int urlCount = -1;

    @XmlElement(name = "index-count")
    private int indexCount = -1;

    @XmlElement(name = "tcy")
    private int tcy = -1;

    private IndexInfo indexInfo;

    @XmlElement
    private Verification verification;

    @XmlElement(name = "crawling")
    private Crawling crawling;

    private History tcyHistory;

    private Webmaster webmaster;

    private Links links;
    // ...

    public int getUrlCount() {
        return urlCount;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public boolean isVirused() {
        return virused;
    }

    public DateTime getLastAccess() {
        return lastAccess;
    }

    public int getIndexCount() {
        return indexCount;
    }

    public int getTcy() {
        return tcy;
    }

    public IndexInfo getIndexInfo() {
        return indexInfo;
    }

    public Verification getVerification() {
        return verification;
    }

    public Crawling getCrawling() {
        return crawling;
    }

    public Webmaster getWebmaster() {
        return webmaster;
    }

    public void setWebmaster(Webmaster webmaster) {
        this.webmaster = webmaster;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("url", url)
                .append("name", name)
                .append("virused", virused)
                .append("lastAccess", lastAccess)
                .append("urlCount", urlCount)
                .append("indexCount", indexCount)
                .append("tcy", tcy)
                .append("indexInfo", indexInfo)
                .append("verification", verification)
                .append("crawling", crawling)
                .toString();
    }
}

class Links { }