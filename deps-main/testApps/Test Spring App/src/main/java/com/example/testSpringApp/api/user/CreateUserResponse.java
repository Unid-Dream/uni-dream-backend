package com.example.testSpringApp.api.user;

import com.example.testSpringApp.acl.ACL;
import com.example.testSpringApp.data.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserResponse {
    @NotBlank
    private String respondAnswer;

    @NotNull
    @Valid
    private List<@NotNull UserRoleDetail> userRoleDetail;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UserRoleDetail {
        @NotBlank
        private String shortDescription;

        @NotNull
        private List<@NotBlank String> description;

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
        private List<LocalDateTime> another;

        @JsonGetter(Fields.description)
        @Schema(
                name = Fields.description,
                description = "ACL: admin"
        )
        public List<String> acl() {
            // remove field(s) base on user role when output response
            // or throw exceptions
            return ACL.of(description)
                    .onDefault(i -> List.of())
                    .onNotUser(i -> null)
                    .on(UserRole.ADMIN.toString(), i -> i)
                    .get();
        }
    }
}
