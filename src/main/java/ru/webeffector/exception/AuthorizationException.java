package ru.webeffector.exception;

/**
 * @author Ernest Sadykov
 * @since 14.03.2014
 */
public class AuthorizationException extends WebmasterException {
    @Override
    public int getErrorCode() {
        return 401;
    }

    public AuthorizationException() {
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationException(Throwable cause) {
        super(cause);
    }

    public AuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
