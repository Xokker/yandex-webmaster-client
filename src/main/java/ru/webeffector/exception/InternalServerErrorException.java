package ru.webeffector.exception;

/**
 * @author Ernest Sadykov
 * @since 14.03.2014
 */
public class InternalServerErrorException extends WebmasterException {
    @Override
    public int getErrorCode() {
        return 500;
    }

    public InternalServerErrorException() {
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }

    public InternalServerErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
