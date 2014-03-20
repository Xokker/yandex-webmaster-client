package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents information about pages excluded from indexing.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-errors.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-errors.xml
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
@XmlRootElement(name = "host")
@XmlAccessorType(XmlAccessType.FIELD)
public class HostErrors {
    @XmlPath("url-errors/@count")
    private Integer urlErrorsTotalCount;

    private Map<Integer, UrlErrorsInfo> urlErrors;

    HostErrors() {
        urlErrors = new HashMap<>();
    }

    @XmlPath("url-errors/url-errors-with-code")
    private void setUrlErrorsInfoList(List<UrlErrorsInfo> urlErrorsInfoList) {
        for (UrlErrorsInfo urlErrorsInfo : urlErrorsInfoList) {
            urlErrors.put(urlErrorsInfo.getCode(), urlErrorsInfo);
        }
    }

    private List<UrlErrorsInfo> getUrlErrorsInfoList() {
        return null;
    }

    public Integer getUrlErrorsTotalCount() {
        return urlErrorsTotalCount;
    }

    public void setUrlErrorsTotalCount(Integer urlErrorsTotalCount) {
        this.urlErrorsTotalCount = urlErrorsTotalCount;
    }

    public Map<Integer, UrlErrorsInfo> getUrlErrors() {
        return urlErrors;
    }

    public void setUrlErrors(Map<Integer, UrlErrorsInfo> urlErrors) {
        this.urlErrors = urlErrors;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("urlErrorsTotalCount", urlErrorsTotalCount)
                .append("urlErrors", urlErrors)
                .toString();
    }
}
