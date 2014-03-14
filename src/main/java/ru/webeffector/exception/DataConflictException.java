package ru.webeffector.exception;

/**
 * @author Ernest Sadykov
 * @since 14.03.2014
 */
public class DataConflictException extends WebmasterException {
    @Override
    public int getErrorCode() {
        return 409;
    }

    public DataConflictException() {
    }

    public DataConflictException(String message) {
        super(message);
    }

    public DataConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataConflictException(Throwable cause) {
        super(cause);
    }

    public DataConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
