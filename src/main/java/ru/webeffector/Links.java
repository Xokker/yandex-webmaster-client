package ru.webeffector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.EnumMap;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
@XmlRootElement(name = "host")
class Links {

    static class Link {
        @XmlAttribute(name = "rel")
        private String key;
        @XmlAttribute(name = "href")
        private String value;
    }

    private List<Link> links;
    private EnumMap<LinkType, String> linksMap = new EnumMap<>(LinkType.class);

    public String get(LinkType linkType) {
        return linksMap.get(linkType);
    }

    @XmlElement(name = "link")
    private List<Link> getLinks() {
        return links;
    }

    private void setLinks(List<Link> links) {
        for (Link link : links) {
            linksMap.put(LinkType.valueOf(link.key.replace('-', '_')), link.value);
        }
        links = null;
    }
}
