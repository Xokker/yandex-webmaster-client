package ru.webeffector.host;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.*;

/**
 * Class represents information about site (host) verification.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-verify.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-verify.xml
 *
 * @author Ernest Sadykov
 * @since 10.03.2014
 *
 * @see ru.webeffector.host.Host
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Verification {
    @XmlAttribute(name = "state")
    private VerificationStatus verificationStatus;

    @XmlElement(name = "details")
    private VerificationRefusalDetails verificationRefusalDetails;

    private String uin;
    // ...

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public VerificationRefusalDetails getVerificationRefusalDetails() {
        return verificationRefusalDetails;
    }

    public void setVerificationRefusalDetails(VerificationRefusalDetails verificationRefusalDetails) {
        this.verificationRefusalDetails = verificationRefusalDetails;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("uin", uin)
                .append("verificationRefusalDetails", verificationRefusalDetails)
                .append("verificationStatus", verificationStatus)
                .toString();
    }
}
