package ru.webeffector.host;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ernest Sadykov
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Verification {
    @XmlAttribute(name = "state")
    private VerificationStatus verificationStatus;
    private String uin;
    // ...

    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    @Override
    public String toString() {
        return "Verification{" +
                "verificationStatus=" + verificationStatus +
                ", uin='" + uin + '\'' +
                '}';
    }
}
