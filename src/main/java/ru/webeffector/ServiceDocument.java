package ru.webeffector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents service document of the webmaster API.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/tasks/how-to-get-service-document.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/tasks/how-to-get-service-document.xml
 *
 * @author Ernest Sadykov
 */
@XmlRootElement(name = "service")
class ServiceDocument {
    static private class Collection {
        @XmlAttribute(name = "href")
        private String hostUrl;
    }

    static private class Workspace {
        @XmlElement(name = "collection")
        private Collection collection;
    }

    @XmlElement(name = "workspace")
    private Workspace workspace;

    public String getHostsUrl() {
        return workspace.collection.hostUrl;
    }
}
