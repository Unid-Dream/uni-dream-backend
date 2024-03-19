package unid.monoServerApp.api.school;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import unid.jooqMono.model.enums.SchoolLevelEnum;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Constant;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.SchoolMapper;
import unid.monoServerMeta.api.SchoolMapResponse;
import unid.monoServerMeta.api.SchoolRequest;
import unid.monoServerMeta.api.SchoolResponse;
import unid.monoServerMeta.api.TagResponse;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Schools")
@Slf4j
public class SchoolController {
    private final SchoolService schoolService;
    private final SchoolMapper schoolMapper;
    private final DbSchool dbSchool;
    private final ObjectMapper objectMapper;

    @GetMapping("student/school/")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    @Hidden
    public @Valid UnifiedResponse<PaginationResponse<SchoolResponse>> list(
            @Valid
            @ParameterObject
            PaginationRequest payload
    ) {
        var table = SchoolPagination.TABLE;
        var dslContext = dbSchool.getDsl();
        var extraConditionOnFirstPage = DSL.noCondition();
        if (StringUtils.isBlank(payload.getPage())) {
            extraConditionOnFirstPage = dbSchool.validateCondition(table);
        }
        var result = PaginatedQuery.init(
                        dslContext,
                        objectMapper,
                        payload,
                        RequestHolder.get().getAuthToken(),
                        Constant.PAGINATION_MIN_SIZE,
                        Constant.PAGINATION_MAX_SIZE
                )
                .select(dsl -> dbSchool.getQuery(table))
                .conditions(SchoolPagination.conditionVisitor)
                .extraConditions(extraConditionOnFirstPage)
                .sortBy(
                        SchoolPagination.orderingVisitor,
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
                PaginationResponse.asResult(result, schoolMapper.toResponse(result.getResult().into(DbSchool.Result.class)))
        );
    }

    @GetMapping("student/school/{id}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    @Hidden
    public @Valid UnifiedResponse<SchoolResponse> get(
            @PathVariable("id") UUID id
    ) {
        var result = schoolService.get(id);
        return UnifiedResponse.of(
                schoolMapper.toResponse(result)
        );
    }

    @PostMapping("student/school/")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create One"
    )
    @Hidden
    public @Valid UnifiedResponse<SchoolResponse> create(
            @RequestBody @Valid
            SchoolRequest payload
    ) {
        var result = schoolService.get(
                schoolService.create(payload)
                        .getId()
        );
        var resp = schoolMapper.toResponse(result);
        return UnifiedResponse.of(
                resp
        );
    }

    @PutMapping("student/school/{id}")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    @Hidden
    public @Valid UnifiedResponse<SchoolResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid
            SchoolRequest payload
    ) {
        var result = schoolService.get(
                schoolService.update(id, payload)
                        .getId()
        );
        return UnifiedResponse.of(
                schoolMapper.toResponse(result)
        );
    }

    @GetMapping(value = {"educator/school/tags","student/school/tags"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Tags"
    )
    public @Valid UnifiedResponse<List<TagResponse>> tags() {
        return UnifiedResponse.of(
                schoolService.tags()
        );
    }

    @GetMapping(value = {"educator/secondarySchool /tags","student/secondarySchool /tags"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Tags"
    )
    public @Valid UnifiedResponse<List<TagResponse>> secondarySchoolTags() {
        return UnifiedResponse.of(
                schoolService.tags()
        );
    }

    @GetMapping("student/school/list")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query School Map"
    )
    public @Valid UnifiedResponse<List<SchoolResponse>> getSchoolMap(
            @RequestParam(required = false) String searchKey,
            @RequestParam SchoolLevelEnum  schoolLevel
            ) {
        return UnifiedResponse.of(
                schoolService.list(searchKey,schoolLevel)
        );
    }
}
