package ru.webeffector.exception;

/**
 * @author Ernest Sadykov
 * @since 14.03.2014
 */
public class InvalidRequestException extends WebmasterException {
    @Override
    public int getErrorCode() {
        return 400;
    }

    public InvalidRequestException() {
    }

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestException(Throwable cause) {
        super(cause);
    }

    public InvalidRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
