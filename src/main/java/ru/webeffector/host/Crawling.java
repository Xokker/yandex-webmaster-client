package ru.webeffector.host;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.*;

/**
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Crawling {
    @XmlAttribute(name = "state")
    private CrawlingState crawlingState;

    @XmlElement(name = "details")
    private CrawlingDenialDetails crawlingDenialDetails;

    public CrawlingState getCrawlingState() {
        return crawlingState;
    }

    public void setCrawlingState(CrawlingState crawlingState) {
        this.crawlingState = crawlingState;
    }

    public CrawlingDenialDetails getCrawlingDenialDetails() {
        return crawlingDenialDetails;
    }

    public void setCrawlingDenialDetails(CrawlingDenialDetails crawlingDenialDetails) {
        this.crawlingDenialDetails = crawlingDenialDetails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("crawlingState", crawlingState)
                .append("crawlingDenialDetails", crawlingDenialDetails)
                .toString();
    }
}
