package ru.webeffector.model;

/**
 * Reasons the site was not indexed.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/errors.xml#denial-index
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/errors.xml#denial-index
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
public enum CrawlingDenialDetails {
    WAITING("Site is waiting for indexing"),
    CONNECTION_FAILED("Lost connection during site indexing"),
    ROBOTS_TXT("The robots.txt file is formed to not allow site indexing"),
    INTERNAL_ERROR("Internal robot error");
    
    private final String description;

    CrawlingDenialDetails(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
