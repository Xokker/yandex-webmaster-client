package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents information about external links to the site that were detected by
 * the robot over the past week.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-links.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-links.xml
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 * TODO: test this mapping (using saved xml)
 */
@XmlRootElement(name = "host")
@XmlAccessorType(XmlAccessType.FIELD)
public class IncomingLinks {
    @XmlElement(name = "links-count")
    private Integer linksCount;

    @XmlElementWrapper(name = "last-week-links")
    @XmlElement(name = "url")
    private List<String> lastWeekLinks = new ArrayList<>();

    public Integer getLinksCount() {
        return linksCount;
    }

    public void setLinksCount(Integer linksCount) {
        this.linksCount = linksCount;
    }

    public List<String> getLastWeekLinks() {
        return lastWeekLinks;
    }

    public void setLastWeekLinks(List<String> lastWeekLinks) {
        this.lastWeekLinks = lastWeekLinks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("linksCount", linksCount)
                .append("lastWeekLinks", lastWeekLinks)
                .toString();
    }
}
