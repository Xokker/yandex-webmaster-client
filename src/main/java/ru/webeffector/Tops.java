package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.persistence.oxm.annotations.XmlPath;

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
    @XmlPath("top-queries/total-shows-count/text()")
    private Integer totalShowsCount;

    @XmlPath("top-queries/top-shows-percent/text()")
    private Double topShowsPercent;

    @XmlPath("top-queries/top-shows/top-info")
    private List<TopEntity> topShows;

    @XmlPath("top-queries/total-clicks-count/text()")
    private Integer totalClicksCount;

    @XmlPath("top-queries/top-clicks-percent/text()")
    private Double topClicksPercent;

    @XmlPath("top-queries/top-clicks/top-info")
    private List<TopEntity> topClicks;

    public Integer getTotalShowsCount() {
        return totalShowsCount;
    }

    public void setTotalShowsCount(Integer totalShowsCount) {
        this.totalShowsCount = totalShowsCount;
    }

    public Double getTopShowsPercent() {
        return topShowsPercent;
    }

    public void setTopShowsPercent(Double topShowsPercent) {
        this.topShowsPercent = topShowsPercent;
    }

    public List<TopEntity> getTopShows() {
        return topShows;
    }

    public void setTopShows(List<TopEntity> topShows) {
        this.topShows = topShows;
    }

    public Integer getTotalClicksCount() {
        return totalClicksCount;
    }

    public void setTotalClicksCount(Integer totalClicksCount) {
        this.totalClicksCount = totalClicksCount;
    }

    public Double getTopClicksPercent() {
        return topClicksPercent;
    }

    public void setTopClicksPercent(Double topClicksPercent) {
        this.topClicksPercent = topClicksPercent;
    }

    public List<TopEntity> getTopClicks() {
        return topClicks;
    }

    public void setTopClicks(List<TopEntity> topClicks) {
        this.topClicks = topClicks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("totalShowsCount", totalShowsCount)
                .append("topShowsPercent", topShowsPercent)
                .append("topShows", topShows)
                .append("totalClicksCount", totalClicksCount)
                .append("topClicksPercent", topClicksPercent)
                .append("topClicks", topClicks)
                .toString();
    }
}
