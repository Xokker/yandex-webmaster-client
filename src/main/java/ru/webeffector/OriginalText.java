package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents on of the original text which were uploaded to yandex.webmaster.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-original-texts.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-original-texts.xml
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OriginalText {

    @XmlElement
    private String id;

    @XmlPath("link/@href")
    private String url;

    @XmlElement
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("url", url)
                .append("content", content)
                .toString();
    }
}
