package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents service document of the webmaster API.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/tasks/how-to-get-service-document.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/tasks/how-to-get-service-document.xml
 *
 * @author Ernest Sadykov
 * @since 11.03.2014
 */
@XmlRootElement(name = "service")
class ServiceDocument {

    @XmlPath("workspace/collection/@href")
    private String hostUrl;

    public String getHostsUrl() {
        return hostUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("hostsUrl", getHostsUrl())
                .toString();
    }
}
