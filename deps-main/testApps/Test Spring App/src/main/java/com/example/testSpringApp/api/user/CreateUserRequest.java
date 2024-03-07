package com.example.testSpringApp.api.user;

import com.example.testSpringApp.Exceptions;
import com.example.testSpringApp.acl.ACL;
import com.example.testSpringApp.data.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserRequest {
    @NotNull
    @Valid
    private List<Question> questions;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Question {
        private String question;

        @JsonSetter(Fields.question)
        @Schema(
                name = Fields.question,
                description = "ACL: admin"
        )
        public void setQ(String value) {
            // remove field(s) base on user role when receiving request
            // or throw exceptions
            this.question = ACL.of(value)
                    .onDefault(i -> {
                        // should be a global-referenced exception instead of inline
                        throw Exceptions.InsufficientInput(null, Fields.question);
                    })
                    // remove field
                    .onNotUser(i -> null)
                    .on(UserRole.ADMIN.toString(), i -> i)
                    .get();
        }
    }
}
