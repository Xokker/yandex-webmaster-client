package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URL;
import java.util.List;

/**
 * Represents information about indexed pages.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-indexed.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-indexed.xml
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
@XmlRootElement(name = "host")
public class IndexInfo {

    private static class Urls {
        @XmlElement(name = "url")
        private List<URL> indexedUrls;
    }

    @XmlElement(name = "index-count")
    private int indexCount;

    @XmlElement(name = "last-week-index-urls")
    private Urls urls;

    public int getIndexCount() {
        return indexCount;
    }

    public List<URL> getIndexedUrls() {
        return urls.indexedUrls;
    }

    void setIndexCount(Integer indexCount) {
        this.indexCount = indexCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("indexCount", indexCount)
                .append("urls", urls)
                .toString();
    }
}
