package ru.webeffector.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Represents detailed information about sitemap indexing,
 * i.e. number of errors, submit date, source of sitemap, etc.
 *
 * @author Ernest Sadykov
 * @since 21.03.2014
 */
@XmlAccessorType(XmlAccessType.NONE)
public class SitemapAdditionalInfo {

    public enum SitemapFormat { TEXT, RSS, XML }

    public enum SitemapType { SITEMAP, SITEMAPINDX }

    @XmlElement(name = "url-count")
    private Integer urlCount;

    @XmlElement(name = "url-error-count")
    private Integer urlErrorCount;

    @XmlElement(name = "error-count")
    private Integer errorCount;

    @XmlElement(name = "warning-count")
    private Integer warningCount;

    @XmlElement(name = "submitted-on")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private DateTime submitDate;

    @XmlElement(name = "added-by")
    private String addedBy;

    @XmlElement(name = "processed-on")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private DateTime processDate;

    @XmlElement(name = "real-processed-on")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private DateTime realProcessDate;

    @XmlElement
    private SitemapFormat format;

    @XmlElement
    private SitemapType type;

    public Integer getUrlCount() {
        return urlCount;
    }

    public void setUrlCount(Integer urlCount) {
        this.urlCount = urlCount;
    }

    public Integer getUrlErrorCount() {
        return urlErrorCount;
    }

    public void setUrlErrorCount(Integer urlErrorCount) {
        this.urlErrorCount = urlErrorCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public Integer getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(Integer warningCount) {
        this.warningCount = warningCount;
    }

    public DateTime getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(DateTime submitDate) {
        this.submitDate = submitDate;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public DateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(DateTime processDate) {
        this.processDate = processDate;
    }

    public DateTime getRealProcessDate() {
        return realProcessDate;
    }

    public void setRealProcessDate(DateTime realProcessDate) {
        this.realProcessDate = realProcessDate;
    }

    public SitemapFormat getFormat() {
        return format;
    }

    public void setFormat(SitemapFormat format) {
        this.format = format;
    }

    public SitemapType getType() {
        return type;
    }

    public void setType(SitemapType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("urlCount", urlCount)
                .append("urlErrorCount", urlErrorCount)
                .append("errorCount", errorCount)
                .append("warningCount", warningCount)
                .append("submitDate", submitDate)
                .append("addedBy", addedBy)
                .append("processDate", processDate)
                .append("realProcessDate", realProcessDate)
                .append("format", format)
                .append("type", type)
                .toString();
    }
}
