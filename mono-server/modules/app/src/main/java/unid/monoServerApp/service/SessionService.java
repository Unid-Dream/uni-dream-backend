package unid.monoServerApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.Routines;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.http.RequestHolder;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SessionService {
    private final DSLContext dslContext;

    public void allowAuthedOnly() {
        allowAuthedOnly(UserRoleEnum.values());
    }

    public void allowAuthedOnly(UserRoleEnum... allowedUserRoles) {
        if (!RequestHolder.get().isAuthed()) {
            throw Exceptions.unauthorized("UNAUTHORISED");
        }
        if (!Arrays.asList(allowedUserRoles).contains(RequestHolder.get().getUser().getUserRole())) {
            throw Exceptions.forbidden("Permission Denied");
        }
    }

    public void allowMatchedProfileId(UUID profileId) {
        if (!RequestHolder.get().isAuthed()) {
            return;
        }
        var currentUserProfileId = RequestHolder.get().getUser().getUserProfileId();
        if (!profileId.equals(currentUserProfileId)) {
            throw Exceptions.forbidden("Permission Denied | Profile Missing or Not Matched");
        }
    }

    public void allowMatchedUserId(UUID userId) {
        if (!RequestHolder.get().isAuthed()) {
            return;
        }
        var currentUserId = RequestHolder.get().getUser().getUserId();
        if (!userId.equals(currentUserId)) {
            throw Exceptions.forbidden("Permission Denied");
        }
    }

    public void allowUnAuthedOnly() {
        if (RequestHolder.get().isAuthed()) {
            throw Exceptions.unauthorized("UNAUTHORISED");
        }
    }

    public void initDatabaseSession() {
        var session = RequestHolder.get();
        if (session != null && session.isAuthed()) {
            log.info("Database Appending User Session | User: {}", session.getUser().getUserId());
            Routines.setCurrentAppUser(
                    dslContext.configuration(),
                    String.format("mono_%s", session.getUser().getUserId().toString())
            );
        }
    }

    public boolean isAdministrativeRole() {
        if (!RequestHolder.get().isAuthed()) {
            return false;
        }
        if (Set.of(UserRoleEnum.ADMIN, UserRoleEnum.ROOT).contains(RequestHolder.get().getUser().getUserRole())) {
            return true;
        }
        return false;
    }

    public void allowProfiledAndApprovedEducator() {
        var session = RequestHolder.get();
        if (session.isAuthed()) {
            switch (session.getUser().getUserRole()) {
                case EDUCATOR:
                    if (!session.getUser().getProfileApproved()) {
                        throw Exceptions.forbidden("Profile Is Not Approved");
                    }
                    break;
                default:
                    return;
            }
        }
    }
}
