package pwh.coreStarter.exception;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class UnifiedException extends RuntimeException {
    @NotBlank
    @Getter
    @Setter
    private String errorCode;
    @Getter
    private Integer httpStatus;

    @Getter
    @Setter
    private I18n i18n;


    public UnifiedException(@NotBlank String errorCode,
                            String message,
                            Integer httpStatus,
                            Throwable cause
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
        //需要检查当前message是否为json格式
        return String.format(
                "[%s]%s",
                errorCode,
                formatIfPresent(message, ": ", null).orElse("")
        );
    }

    @Data
    @AllArgsConstructor
    public static class I18n {
        private String english;
        private String chineseSimplified;
        private String chineseTraditional;
    }




}
