package ru.webeffector.host;

/**
 * @author Ernest Sadykov
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
