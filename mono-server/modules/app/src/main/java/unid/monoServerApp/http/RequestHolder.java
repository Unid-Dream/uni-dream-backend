package unid.monoServerApp.http;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;
import pwh.springWebStarter.holder.WebContextHolder;
import unid.jooqMono.model.enums.UserRoleEnum;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class RequestHolder extends WebContextHolder.ContextHolder {
    private UserSessionPayload userSessionPayload;

    public RequestHolder(
            String traceId,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ) {
        super(traceId, httpRequest, httpResponse);
    }

    // sugar method
    public static RequestHolder get() {
        return WebContextHolder.getAs(RequestHolder.class);
    }

    public String getAuthToken() {
        return getHttpRequest().getHeader("UNIDTOKEN");
    }

    public boolean isAuthed() {
        return getUser() != null;
    }

    public UserSessionPayload getUser() {
        return userSessionPayload;
    }

    public void setUser(UserSessionPayload userSessionPayload) {
        this.userSessionPayload = userSessionPayload;
    }

    @Data
    @SuperBuilder
    @Jacksonized
    @Validated
    @NoArgsConstructor
    @ToString
    public static class UserSessionPayload {
        @NotNull
        private UUID userId;
        @NotNull
        private UserRoleEnum userRole;
        @Nullable
        private UUID userProfileId;
        @NotNull
        private Boolean profileApproved;
    }
}
