package ru.webeffector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Represents statistics about top shows and top clicks.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/host-tops.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/host-tops.xml
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
@XmlRootElement(name = "host")
public class Tops {
    private static class TopQueriesDetails {
        @XmlElement(name = "total-shows-count")
        private Integer totalShowsCount;

        @XmlElement(name = "top-shows-percent")
        private Integer topShowsPercent;

        @XmlElementWrapper(name = "top-shows")
        @XmlElement(name = "top-info")
        private List<TopEntity> topShows;

        @XmlElement(name = "total-clicks-count")
        private Integer totalClicksCount;

        @XmlElement(name = "top-clicks-percent")
        private Integer topClicksPercent;

        @XmlElementWrapper(name = "top-clicks")
        @XmlElement(name = "top-info")
        private List<TopEntity> topClicks;
    }

    private TopQueriesDetails topQueriesDetails;

    public Integer getTotalShowsCount() {
        return topQueriesDetails.totalShowsCount;
    }

    public void setTotalShowsCount(Integer totalShowsCount) {
        this.topQueriesDetails.totalShowsCount = totalShowsCount;
    }

    public Integer getTopShowsPercent() {
        return topQueriesDetails.topShowsPercent;
    }

    public void setTopShowsPercent(Integer topShowsPercent) {
        this.topQueriesDetails.topShowsPercent = topShowsPercent;
    }

    public List<TopEntity> getTopShows() {
        return topQueriesDetails.topShows;
    }

    public void setTopShows(List<TopEntity> topShows) {
        this.topQueriesDetails.topShows = topShows;
    }

    public Integer getTotalClicksCount() {
        return topQueriesDetails.totalClicksCount;
    }

    public void setTotalClicksCount(Integer totalClicksCount) {
        this.topQueriesDetails.totalClicksCount = totalClicksCount;
    }

    public Integer getTopClicksPercent() {
        return topQueriesDetails.topClicksPercent;
    }

    public void setTopClicksPercent(Integer topClicksPercent) {
        this.topQueriesDetails.topClicksPercent = topClicksPercent;
    }

    public List<TopEntity> getTopClicks() {
        return topQueriesDetails.topClicks;
    }

    public void setTopClicks(List<TopEntity> topClicks) {
        this.topQueriesDetails.topClicks = topClicks;
    }
}
