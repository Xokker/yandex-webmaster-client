package ru.webeffector.exception;

/**
 * Represents error that can occur during performing request to
 * yandex.webmaster.
 *
 * API docs [EN]: http://api.yandex.com/webmaster/doc/dg/reference/errors.xml
 * API docs [RU]: http://api.yandex.ru/webmaster/doc/dg/reference/errors.xml
 *
 * @author Ernest Sadykov
 * @since 14.03.2014
 */
public abstract class WebmasterException extends RuntimeException {
    public abstract int getErrorCode();

    protected WebmasterException() {
    }

    protected WebmasterException(String message) {
        super(message);
    }

    protected WebmasterException(String message, Throwable cause) {
        super(message, cause);
    }

    protected WebmasterException(Throwable cause) {
        super(cause);
    }

    protected WebmasterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
