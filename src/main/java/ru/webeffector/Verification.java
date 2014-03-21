package ru.webeffector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.persistence.oxm.annotations.XmlPath;
import org.joda.time.DateTime;
import ru.webeffector.model.DateTimeAdapter;
import ru.webeffector.model.VerificationConfirmationType;
import ru.webeffector.model.VerificationState;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlAccessorType(XmlAccessType.NONE)
public class Verification {

    @XmlPath("verification/@state")
    private VerificationState verificationState;

    @XmlPath("verification/type/text()")
    private VerificationConfirmationType verificationConfirmationType;

    @XmlPath("verification/possible-to-cancel/text()")
    private Boolean cancellationPossibility;

    @XmlPath("verification/uin/text()")
    private String uin;

    @XmlPath("verification/date/text()")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private DateTime date;

    @XmlElement
    private String name;

    private Boolean otherUsersExist;

    Verification() {
        otherUsersExist = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlPath("other-users-exist/text()")
    public Boolean getOtherUsersExist() {
        return otherUsersExist;
    }

    private void setOtherUsersExist(Boolean otherUsersExist) {
        // it is empty tag, if it is passed, that means true
        // if it's not, that means false
        this.otherUsersExist = true;
    }

    public VerificationState getVerificationState() {
        return verificationState;
    }

    public void setVerificationState(VerificationState verificationState) {
        this.verificationState = verificationState;
    }

    public VerificationConfirmationType getVerificationConfirmationType() {
        return verificationConfirmationType;
    }

    public void setVerificationConfirmationType(VerificationConfirmationType verificationConfirmationType) {
        this.verificationConfirmationType = verificationConfirmationType;
    }

    public Boolean getCancellationPossibility() {
        return cancellationPossibility;
    }

    public void setCancellationPossibility(Boolean cancellationPossibility) {
        this.cancellationPossibility = cancellationPossibility;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("verificationState", verificationState)
                .append("verificationConfirmationType", verificationConfirmationType)
                .append("cancellationPossibility", cancellationPossibility)
                .append("uin", uin)
                .append("date", date)
                .append("name", name)
                .append("otherUsersExist", otherUsersExist)
                .toString();
    }
}
