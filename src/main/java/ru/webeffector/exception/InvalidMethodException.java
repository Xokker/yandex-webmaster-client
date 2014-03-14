package ru.webeffector.exception;

/**
 * @author Ernest Sadykov
 * @since 14.03.2014
 */
public class InvalidMethodException extends WebmasterException {
    @Override
    public int getErrorCode() {
        return 405;
    }

    public InvalidMethodException() {
    }

    public InvalidMethodException(String message) {
        super(message);
    }

    public InvalidMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMethodException(Throwable cause) {
        super(cause);
    }

    public InvalidMethodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
