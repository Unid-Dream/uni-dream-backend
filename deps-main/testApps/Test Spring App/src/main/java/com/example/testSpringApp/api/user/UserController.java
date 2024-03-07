package com.example.testSpringApp.api.user;

import com.example.testSpringApp.api.ContextHolder;
import com.example.testSpringApp.feign.test.TestApi;
import com.example.testSpringApp.feign.test.TestApiQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreRsqlJooq.model.PaginationResponse;
import pwh.springWebStarter.response.UnifiedResponse;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping({"/api/user"})
@Tag(name = "User") // OpenAPI Documentation
@Validated // enable validation for this controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {
    private final TestApi testApi;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query",
            description = "default limit: 20 | fields: created_on | country | name"
    )
    public @Valid PaginationResponse<Map<String, String>> createUser(
            @Valid
            @ParameterObject // annotate this when POJO is a query for OpenAPI Documentation
            PaginationRequest payload
    ) {
        log.info("Query: {}", payload);
        return null;
    }

    @GetMapping("role")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get Sample User Role"
    )
    public @Valid UnifiedResponse<GetUseRoleResponse> getUserRole(
            @Valid GetUseRoleQuery query
    ) {
        testApi.getPosts(TestApiQuery.builder()
                .postId(1)
                .userRole(UserRole.ADMIN)
                .build());
        return UnifiedResponse.of(GetUseRoleResponse.builder()
                .userRole(query.getUserRole())
                .build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(
            summary = "Create"
    )
    public @Valid UnifiedResponse<CreateUserResponse> createUser(
            @Valid // validated request
            @RequestBody
            CreateUserRequest payload
    ) {
        log.info("Payload: {}", payload);
        log.info("Holder: {}", ContextHolder.get().getTraceId());
//        log.info("Test API: {}", testApi.getPosts(TestApiQuery.builder().postId(1).build()));
        return
                UnifiedResponse.of(
                        CreateUserResponse.builder()
                                .respondAnswer(UUID.randomUUID().toString())
                                .userRoleDetail(
                                        List.of(
                                                CreateUserResponse.UserRoleDetail.builder()
                                                        .shortDescription(payload.getQuestions().get(0).getQuestion())
                                                        .description(new ArrayList<>(List.of("hahaha", payload.getQuestions().get(0).getQuestion())) {{
                                                        }})
                                                        .another(
                                                                new ArrayList<>() {{
                                                                    add(LocalDateTime.now());
                                                                    add(LocalDateTime.parse("2023-01-01T11:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
                                                                }}
                                                        )
                                                        .build()
                                        )
                                )
                                .build()
                );
    }
}
