package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Class represents information about site (host) verification.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-verify.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-verify.xml
 *
 * @author Ernest Sadykov
 * @since 10.03.2014
 *
 * @see Host
 */
@XmlRootElement(name = "host")
@XmlAccessorType(XmlAccessType.FIELD)
public class Verification {

    private static class VerificationDetails {
        @XmlAttribute(name = "state")
        private VerificationState verificationState;

        @XmlElement(name = "type")
        private VerificationConfirmationType verificationConfirmationType;

        @XmlElement(name = "possible-to-cancel")
        private Boolean cancellationPossibility;

        @XmlElement
        private String uin;

        @XmlElement
        @XmlJavaTypeAdapter(DateTimeAdapter.class)
        private DateTime date;
    }

    @XmlElement
    private String name;

    @XmlElement(name = "verification")
    private VerificationDetails verificationDetails;

    @XmlElement(name = "other-users-exist")
    private Boolean otherUsersExist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VerificationDetails getVerificationDetails() {
        return verificationDetails;
    }

    public void setVerificationDetails(VerificationDetails verificationDetails) {
        this.verificationDetails = verificationDetails;
    }

    public Boolean getOtherUsersExist() {
        return otherUsersExist;
    }

    public void setOtherUsersExist(Boolean otherUsersExist) {
        this.otherUsersExist = otherUsersExist;
    }

    public VerificationState getVerificationState() {
        return verificationDetails.verificationState;
    }

    public void setVerificationState(VerificationState verificationState) {
        this.verificationDetails.verificationState = verificationState;
    }

    public VerificationConfirmationType getVerificationConfirmationType() {
        return verificationDetails.verificationConfirmationType;
    }

    public void setVerificationConfirmationType(VerificationConfirmationType verificationConfirmationType) {
        this.verificationDetails.verificationConfirmationType = verificationConfirmationType;
    }

    public Boolean getCancellationPossibility() {
        return verificationDetails.cancellationPossibility;
    }

    public void setCancellationPossibility(Boolean cancellationPossibility) {
        this.verificationDetails.cancellationPossibility = cancellationPossibility;
    }

    public String getUin() {
        return verificationDetails.uin;
    }

    public void setUin(String uin) {
        this.verificationDetails.uin = uin;
    }

    public DateTime getDate() {
        return verificationDetails.date;
    }

    public void setDate(DateTime date) {
        this.verificationDetails.date = date;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("name", name)
                .append("verificationDetails", verificationDetails)
                .append("otherUsersExist", otherUsersExist)
                .append("verificationState", verificationDetails.verificationState)
                .append("verificationConfirmationType", verificationDetails.verificationConfirmationType)
                .append("cancellationPossibility", verificationDetails.cancellationPossibility)
                .append("uin", verificationDetails.uin)
                .append("date", verificationDetails.date)
                .toString();
    }
}
