package ru.webeffector;

/**
 * Represents ways to verify rights.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/errors.xml#type-verify
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/errors.xml#type-verify
 *
 * @author Ernest Sadykov
 * @since 13.03.2014
 */
public enum VerificationConfirmationType {

    AUTO(false, "Automatic rights verification."),
    DNS_RECORD(true, "Adding a TXT record to the site's DNS record."),
    HTML_FILE(true, "Placing an HTML file in the site's root directory."),
    MANUAL(false, "Manual verification of rights."),
    META_TAG(true, "Adding a metatag to the header on the site's main page."),
    PDD(false, "Verifying rights via Yandex.Mail for Domains."),
    TXT_FILE(true, "Placing a text file in the site's root directory."),
    PDD_EXTERNAL(false, "External verification of rights via Yandex.Mail for Domains."),
    DELEGATION(false, "Rights are delegated by another user."),
    WHOIS(true, "Verifying data with the information provided by the WHOIS service. This method only works for second-level domains (such as example.com).");

    private boolean verificationStatusCheckable;
    private String description;

    VerificationConfirmationType(boolean verificationStatusCheckable, String description) {
        this.verificationStatusCheckable = verificationStatusCheckable;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVerificationStatusCheckable() {
        return verificationStatusCheckable;
    }
}
