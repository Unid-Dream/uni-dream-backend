package unid.monoServerApp.api;

import unid.jooqMono.model.enums.UserRoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ACL {
    boolean authed() default false;

    boolean noAuthed() default false;

    UserRoleEnum[] allowedRoles() default {};

    boolean matchingSessionProfileId() default false;

    boolean matchingSessionUserId() default false;

    boolean skipMatchingForAdministrative() default false;

    boolean educatorProfileApproved() default false;

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.PARAMETER})
    @interface ProfileId {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.PARAMETER})
    @interface UserId {
    }
}
