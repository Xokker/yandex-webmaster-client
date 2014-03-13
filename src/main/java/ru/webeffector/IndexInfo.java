package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
        private List<String> indexedUrls;

        @XmlElement(name = "url")
        private List<String> getIndexedUrls() { return indexedUrls; }
        private void setIndexedUrls(List<String> u) {
            indexedUrls = u; }
    }

    @XmlElement(name = "index-count")
    private Integer indexCount;

    @XmlElement(name = "last-week-index-urls")
    private Urls urls;

    public int getIndexCount() {
        return indexCount;
    }

    public List<String> getIndexedUrls() {
        return urls.indexedUrls;
    }

    public void setIndexCount(Integer indexCount) {
        this.indexCount = indexCount;
    }

    public void setIndexedUrls(List<String> urls) {
        this.urls.indexedUrls = urls;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("indexCount", indexCount)
                .append("urls", urls)
                .toString();
    }
}
