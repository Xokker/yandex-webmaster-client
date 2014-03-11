package ru.webeffector.host;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.net.URL;

/**
 * @author Ernest Sadykov
 */
@XmlRootElement
public class Host {
    static class IndexInfo {
        private int indexCount;
        private URL[] indexedUrls;
    }

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

    private Stats stats;
    private IndexInfo indexInfo;

    @XmlElement
    private Verification verification;
    private History tcyHistory;
    // ...

    public int getUrlCount() {
        if (stats == null) {
            return urlCount;
        }
        return stats.getUrlCount();
    }

    @Override
    public String toString() {
        return "Host{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", virused=" + virused +
                ", lastAccess=" + lastAccess +
                ", urlCount=" + urlCount +
                ", indexCount=" + indexCount +
                ", tcy=" + tcy +
                ", verification=" + verification +
                '}';
    }
}

class DateTimeAdapter extends XmlAdapter<String, DateTime> {
    @Override
    public DateTime unmarshal(String v) throws Exception {
        return new DateTime(v);
    }

    @Override
    public String marshal(DateTime v) throws Exception {
        return v.toString();
    }

}
