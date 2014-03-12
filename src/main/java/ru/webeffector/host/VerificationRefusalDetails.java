package ru.webeffector.host;

/**
 * Reasons for refusal to verify site management rights.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/errors.xml#denial-verify
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/errors.xml#denial-verify
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
public enum VerificationRefusalDetails {
    DNS_NO_SUCH_ENTRY("The specified DNS record does not exist"),
    DNS_TEXT_PARSE_EXCEPTION("Error parsing the DNS record"),
    EXTERNAL_VERIFICATION_CANCELLED("External verification of rights was canceled"),
    HTML_FILE_IO_EXCEPTION("Error accessing the HTML file"),
    HTML_FILE_PARSER_EXCEPTION("Error parsing the HTML file"),
    HTML_FILE_WRONG_CONTENT("Invalid HTML file content"),
    MANUAL_VERIFICATION_CANCELLED("Manual verification of rights was canceled"),
    META_TAG_IO_EXCEPTION("Error accessing metatag"),
    META_TAG_PARSER_EXCEPTION("Error parsing metatag"),
    META_TAG_VERIFICATION_TAG_NOT_FOUND("Missing metatag in the header on the site's main page"),
    TXT_FILE_IO_EXCEPTION("Error accessing text file"),
    TXT_FILE_METHOD_NOT_APPLICABLE_FOR_HOST("This site does not allow verifying rights using a text file"),
    TXT_FILE_NON_ZERO_LENGTH("The specified text file has nonzero size"),
    WHOIS_SERVER_CONNECTION_EXCEPTION("Error connecting to the WHOIS server"),
    WHOIS_EMAIL_NOT_FOUND("The specified email address does not exist in the WHOIS record for this site"),
    WHOIS_UNCONFIRMED_EMAIL("The specified email address was not confirmed in the WHOIS record for this site"),
    EXTERNAL_VERIFICATION_USED_ALREADY("External verification of rights is being used"),
    EXTERNAL_VERIFICATION_INTERNAL_ERROR("Error in external verification of rights"),
    PDD_NOT_APPLICABLE("This site does not allow verifying rights via Mail for Domains"),
    NOT_APPLICABLE_FOR_AUTO_VERIFICATION("This site does not allow verifying rights automatically"),
    CANCELLED("Operation canceled"),
    OTHER_MAIN_MIRROR_CHANGED("The address of the main site mirror changed"),
    UNKNOWN("Unknown error");

    private final String description;

    VerificationRefusalDetails(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
