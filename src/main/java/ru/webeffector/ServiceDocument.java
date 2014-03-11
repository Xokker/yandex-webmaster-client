package ru.webeffector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
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
