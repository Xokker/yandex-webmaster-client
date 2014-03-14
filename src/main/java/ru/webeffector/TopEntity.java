package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents one of the of top shows or one of the top clicks.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-tops.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-tops.xml
 *
 * @author Ernest Sadykov
 * @since 13.03.2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TopEntity {

    @XmlAttribute(name = "query_id")
    private String queryId;

    @XmlElement(name = "query")
    private String queryText;

    @XmlElement
    private Integer count;

    @XmlElement
    private Integer position;

    @XmlElement(name = "is-custom")
    private Boolean custom;

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean isCustom() {
        return custom;
    }

    public void setCustom(Boolean custom) {
        this.custom = custom;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("queryId", queryId)
                .append("queryText", queryText)
                .append("count", count)
                .append("position", position)
                .append("custom", custom)
                .toString();
    }
}
