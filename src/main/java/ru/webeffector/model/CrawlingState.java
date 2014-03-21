package ru.webeffector.model;

/**
 * Site indexing status.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/errors.xml#index-verify
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/errors.xml#index-verify
 *
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
public enum CrawlingState {
    INDEXED("Site indexed"),
    NOT_INDEXED("Site is not indexed"),
    WAITING("Site is waiting for indexing");

    private final String description;

    CrawlingState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
