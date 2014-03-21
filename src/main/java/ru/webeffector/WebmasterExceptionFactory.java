package ru.webeffector;

import ru.webeffector.exception.*;

/**
 * @author Ernest Sadykov
 * @since 21.03.2014
 */
public class WebmasterExceptionFactory {
    private WebmasterExceptionFactory() {
        // utility class, no need for constructor
    }

    static WebmasterException newException(int errorCode, String message) {
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
}
