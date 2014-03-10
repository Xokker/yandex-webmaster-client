package ru.webeffector.host;

/**
 * @author Ernest Sadykov
 */


public class Verification {
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
}
