package ru.webeffector.host;

/**
 * Class represents site statistics.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/hosts-stats.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/hosts-stats.xml
 *
 * @author Ernest Sadykov
 * @since 10.03.2014
 */
public class Stats {
    private int urlErrorsCount;
    private int urlCount;
    // ...

    public int getUrlErrorsCount() {
        return urlErrorsCount;
    }

    public void setUrlErrorsCount(int urlErrorsCount) {
        this.urlErrorsCount = urlErrorsCount;
    }

    public int getUrlCount() {
        return urlCount;
    }

    public void setUrlCount(int urlCount) {
        this.urlCount = urlCount;
    }
}
