package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.*;
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
@XmlAccessorType(XmlAccessType.FIELD)
public class IndexInfo {

    @XmlElement(name = "index-count")
    private Integer indexCount;

    @XmlElementWrapper(name = "last-week-index-urls")
    @XmlElement(name = "url")
    private List<String> urls;

    public Integer getIndexCount() {
        return indexCount;
    }

    public void setIndexCount(Integer indexCount) {
        this.indexCount = indexCount;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("indexCount", indexCount)
                .append("urls", urls)
                .toString();
    }
}
