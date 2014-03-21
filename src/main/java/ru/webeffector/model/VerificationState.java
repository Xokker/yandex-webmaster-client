package ru.webeffector.model;

/**
 * Status of verification of site management rights.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/errors.xml#status-verify
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/errors.xml#status-verify
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */

public enum VerificationState {
    IN_PROGRESS("Checking site management rights"),
    NEVER_VERIFIED("Site management rights have not been verified previously"),
    VERIFICATION_FAILED("Error in verifying rights"),
    VERIFIED("Rights were verified"),
    WAITING("Waiting in the verification queue");

    private final String description;

    VerificationState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
