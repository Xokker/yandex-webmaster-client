package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents information about number of pages excluded from indexing
 * and reason of exclusion.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-errors.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-errors.xml
 *
 * @author Ernest Sadykov
 * @since 19.03.2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class UrlErrorsInfo {
    @XmlAttribute(name = "code")
    private Integer code;

    @XmlElement(name = "count")
    private Integer errorCount;

    @XmlElement(name = "severity")
    private ErrorSeverity errorSeverity;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public ErrorSeverity getErrorSeverity() {
        return errorSeverity;
    }

    public void setErrorSeverity(ErrorSeverity errorSeverity) {
        this.errorSeverity = errorSeverity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("code", code)
                .append("errorCount", errorCount)
                .append("errorSeverity", errorSeverity)
                .toString();
    }
}
