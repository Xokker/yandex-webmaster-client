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

    // TODO: to think about package structure, that method should not be public
    public static WebmasterException newException(int errorCode, String message) {
        switch (errorCode) {
            case 400:
                return new InvalidRequestException(message);
            case 401:
                return new AuthorizationException(message);
            case 403:
                return new ForbiddenException(message);
            case 405:
                return new InvalidMethodException(message);
            case 409:
                return new DataConflictException(message);
            case 500:
                return new InternalServerErrorException(message);
            default:
                throw new IllegalArgumentException("Possible values of error " +
                        "codes: 400, 401, 403, 405, 409 and 500. Passed: " +
                        errorCode);
        }
    }

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
