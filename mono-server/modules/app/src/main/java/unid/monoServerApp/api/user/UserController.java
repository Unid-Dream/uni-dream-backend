package unid.monoServerApp.api.user;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.coreRsqlJooq.jooq.PaginatedQuery;
import pwh.coreRsqlJooq.jooq.PaginatedQuerySorting;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreRsqlJooq.model.PaginationResponse;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Constant;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.UserMapper;
import unid.monoServerMeta.api.UserRequest;
import unid.monoServerMeta.api.UserResponse;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "User")
@Slf4j
@Hidden
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final DbUser dbUser;
    private final ObjectMapper objectMapper;

    @GetMapping
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<PaginationResponse<UserResponse>> list(
            @Valid
            @ParameterObject
            PaginationRequest payload
    ) {
        var table = UserPagination.TABLE;
        var dslContext = dbUser.getDsl();
        var extraConditionOnFirstPage = DSL.noCondition();
        if (StringUtils.isBlank(payload.getPage())) {
            extraConditionOnFirstPage = dbUser.validateCondition(table);
        }
        var result = PaginatedQuery.init(
                        dslContext,
                        objectMapper,
                        payload,
                        RequestHolder.get().getAuthToken(),
                        Constant.PAGINATION_MIN_SIZE,
                        Constant.PAGINATION_MAX_SIZE
                )
                .select(dsl -> dbUser.getQuery(table))
                .conditions(UserPagination.conditionVisitor)
                .extraConditions(extraConditionOnFirstPage)
                .sortBy(
                        UserPagination.orderingVisitor,
                        null,
                        null,
                        null,
                        uniqueSort -> {
                            uniqueSort.add(
                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
                                            table.CREATED_ON,
                                            SortOrder.DESC,
                                            OrderingVisitor.Seeking.builder()
                                                    .whenSeeking(OffsetDateTime::parse)
                                                    .build()
                                    )
                            );
                            uniqueSort.add(
                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
                                            table.ID,
                                            SortOrder.DESC,
                                            OrderingVisitor.Seeking.builder()
                                                    .whenSeeking(UUID::fromString)
                                                    .build()
                                    )
                            );
                        }
                )
                .fetch();
        return UnifiedResponse.of(
                PaginationResponse.asResult(result, userMapper.toResponse(result.getResult().into(DbUser.Result.class)))
        );
    }

    @GetMapping("{id}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public @Valid UnifiedResponse<UserResponse> get(
            @PathVariable("id") UUID id
    ) {
        var result = userService.get(id);
        return UnifiedResponse.of(
                userMapper.toResponse(result)
        );
    }

    @PostMapping
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ROOT, UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create One"
    )
    public @Valid UnifiedResponse<UserResponse> create(
            @RequestBody @Valid
            UserRequest payload
    ) {
        var result = userService.get(
                userService.create(payload)
                        .getId()
        );
        var resp = userMapper.toResponse(result);
        return UnifiedResponse.of(
                resp
        );
    }

    @PutMapping("{id}")
    @Transactional
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    public @Valid UnifiedResponse<UserResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid
            UserRequest payload
    ) {
        var result = userService.get(
                userService.update(id, payload)
                        .getId()
        );
        return UnifiedResponse.of(
                userMapper.toResponse(result)
        );
    }
}
