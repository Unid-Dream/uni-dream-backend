package pwh.coreStarter.exception;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class UnifiedException extends RuntimeException {
    @NotBlank
    @Getter
    private String errorCode;
    @Getter
    private Integer httpStatus;

    public UnifiedException(@NotBlank String errorCode, String message,
                            Integer httpStatus, Throwable cause
    ) {
        super(formatMessage(errorCode, message), cause);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public UnifiedException(@NotBlank String errorCode, String message,
                            Integer httpStatus
    ) {
        this(errorCode, message, httpStatus, null);
    }


    private static Optional<String> formatIfPresent(
            Object value,
            @NotBlank String prefix,
            String postfix
    ) {
        var val = ObjectUtils.defaultIfNull(value, "").toString();
        if (StringUtils.isNotBlank(val)) {
            return Optional.of(String.format("%s%s%s", prefix, val, StringUtils.defaultIfBlank(postfix, "")));
        }
        return Optional.empty();
    }

    private static String formatMessage(
            @NotBlank String errorCode,
            String message
    ) {
        return String.format(
                "[%s]%s",
                errorCode,
                formatIfPresent(message, ": ", null).orElse("")
        );
    }

}
