package com.example.testSpringApp.feign.test;

import com.example.testSpringApp.api.user.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestApiQuery {
    private Integer postId;
    private UserRole userRole;
}
