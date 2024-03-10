package unid.monoServerApp.api.schoolIdentity;//package unid.monoServerApp.api.country;

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
import unid.monoServerApp.database.table.schoolIdentity.DbSchoolIdentity;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.SchoolIdentityMapper;
import unid.monoServerMeta.api.SchoolIdentityRequest;
import unid.monoServerMeta.api.SchoolIdentityResponse;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/schoolIdentity")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "School Identities")
@Slf4j
@Hidden
public class SchoolIdentityController {
    private final SchoolIdentityService schoolIdentityService;
    private final SchoolIdentityMapper schoolIdentityMapper;
    private final DbSchoolIdentity dbSchoolIdentity;
    private final ObjectMapper objectMapper;

    @GetMapping
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<PaginationResponse<SchoolIdentityResponse>> list(
            @Valid
            @ParameterObject
            PaginationRequest payload
    ) {
        var table = SchoolIdentityPagination.TABLE;
        var dslContext = dbSchoolIdentity.getDsl();
        var extraConditionOnFirstPage = DSL.noCondition();
        if (StringUtils.isBlank(payload.getPage())) {
            extraConditionOnFirstPage = dbSchoolIdentity.validateCondition(table);
        }
        var result = PaginatedQuery.init(
                        dslContext,
                        objectMapper,
                        payload,
                        RequestHolder.get().getAuthToken(),
                        Constant.PAGINATION_MIN_SIZE,
                        Constant.PAGINATION_MAX_SIZE
                )
                .select(dsl -> dbSchoolIdentity.getQuery(table))
                .conditions(SchoolIdentityPagination.conditionVisitor)
                .extraConditions(extraConditionOnFirstPage)
                .sortBy(
                        SchoolIdentityPagination.orderingVisitor,
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
                PaginationResponse.asResult(result, schoolIdentityMapper.toResponse(result.getResult().into(DbSchoolIdentity.Result.class)))
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
    public @Valid UnifiedResponse<SchoolIdentityResponse> get(
            @PathVariable("id") UUID id
    ) {
        var result = schoolIdentityService.get(id);
        return UnifiedResponse.of(
                schoolIdentityMapper.toResponse(result)
        );
    }

    @PostMapping
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create One"
    )
    public @Valid UnifiedResponse<SchoolIdentityResponse> create(
            @RequestBody @Valid
            SchoolIdentityRequest payload
    ) {
        var result = schoolIdentityService.get(
                schoolIdentityService.create(payload)
                        .getId()
        );
        return UnifiedResponse.of(
                schoolIdentityMapper.toResponse(result)
        );
    }

    @PutMapping("{id}")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    public @Valid UnifiedResponse<SchoolIdentityResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid
            SchoolIdentityRequest payload
    ) {
        var result = schoolIdentityService.get(
                schoolIdentityService.update(id, payload)
                        .getId()
        );
        return UnifiedResponse.of(
                schoolIdentityMapper.toResponse(result)
        );
    }
}
