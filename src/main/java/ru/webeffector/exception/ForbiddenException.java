package ru.webeffector.exception;

/**
 * @author Ernest Sadykov
 * @since 14.03.2014
 */
public class ForbiddenException extends WebmasterException {
    @Override
    public int getErrorCode() {
        return 403;
    }

    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

    public ForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
