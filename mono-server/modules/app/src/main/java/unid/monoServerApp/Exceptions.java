package unid.monoServerApp;

import org.springframework.http.HttpStatus;
import pwh.coreStarter.exception.UnifiedException;
import pwh.springWebStarter.ErrorCodeGlobal;
import unid.monoServerMeta.model.UniErrorCode;

public class Exceptions {
    public static UnifiedException unauthorized(String... message) {
        throw new UnifiedException(
                ErrorCode.UNAUTHORISED,
                String.join(" ", message),
                HttpStatus.UNAUTHORIZED.value()
        );
    }

    public static UnifiedException forbidden(String... message) {
        throw new UnifiedException(
                ErrorCode.FORBIDDEN,
                String.join(" ", message),
                HttpStatus.FORBIDDEN.value()
        );
    }

    public static UnifiedException notFound(String... message) {
        throw new UnifiedException(
                ErrorCodeGlobal.ERROR_NOT_FOUND,
                String.join(" ", message),
                HttpStatus.NOT_FOUND.value()
        );
    }

    public static UnifiedException business(UniErrorCode.Business error) {
        throw new UnifiedException(
                String.valueOf(error.code()),
                error.message(),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    public static UnifiedException client(UniErrorCode.Client error) {
        throw new UnifiedException(
                String.valueOf(error.code()),
                error.message(),
                HttpStatus.BAD_REQUEST.value()
        );
    }


    public static UnifiedException invalidTimeslot(String... message) {
        throw new UnifiedException(
                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                "Invalid Timeslot " + String.join(" ", message),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    public static UnifiedException badRequest(String... message) {
        throw new UnifiedException(
                ErrorCodeGlobal.ERROR_INVALID_INPUT,
                String.join(" ", message),
                HttpStatus.BAD_REQUEST.value()
        );
    }

    public static UnifiedException unknownError(String... message) {
        throw new UnifiedException(
                ErrorCodeGlobal.ERROR_INTERNAL_ERROR,
                String.join(" ", message),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }
}
