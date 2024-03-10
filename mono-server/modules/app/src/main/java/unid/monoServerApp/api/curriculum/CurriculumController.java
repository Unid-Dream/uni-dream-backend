package unid.monoServerApp.api.curriculum;//package unid.monoServerApp.api.country;

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
import unid.monoServerApp.database.table.curriculum.DbCurriculum;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.CurriculumMapper;
import unid.monoServerMeta.api.CurriculumRequest;
import unid.monoServerMeta.api.CurriculumResponse;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/curriculum")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Curriculums")
@Slf4j
@Hidden
public class CurriculumController {
    private final CurriculumService curriculumService;
    private final CurriculumMapper curriculumMapper;
    private final DbCurriculum dbCurriculum;
    private final ObjectMapper objectMapper;

    @GetMapping
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<PaginationResponse<CurriculumResponse>> list(
            @Valid
            @ParameterObject
            PaginationRequest payload
    ) {
        var table = CurriculumPagination.TABLE;
        var dslContext = dbCurriculum.getDsl();
        var extraConditionOnFirstPage = DSL.noCondition();
        if (StringUtils.isBlank(payload.getPage())) {
            extraConditionOnFirstPage = dbCurriculum.validateCondition(table);
        }
        var result = PaginatedQuery.init(
                        dslContext,
                        objectMapper,
                        payload,
                        RequestHolder.get().getAuthToken(),
                        Constant.PAGINATION_MIN_SIZE,
                        Constant.PAGINATION_MAX_SIZE
                )
                .select(dsl -> dbCurriculum.getQuery(table))
                .conditions(CurriculumPagination.conditionVisitor)
//                .extraConditions(extraConditionOnFirstPage)
                .sortBy(
                        CurriculumPagination.orderingVisitor,
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
                PaginationResponse.asResult(result, curriculumMapper.toResponse(result.getResult().into(DbCurriculum.Result.class)))
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
    public @Valid UnifiedResponse<CurriculumResponse> get(
            @PathVariable("id") UUID id
    ) {
        var result = curriculumService.get(id);
        return UnifiedResponse.of(
                curriculumMapper.toResponse(result)
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
    public @Valid UnifiedResponse<CurriculumResponse> create(
            @RequestBody @Valid
            CurriculumRequest payload
    ) {
        var result = curriculumService.get(
                curriculumService.create(payload)
                        .getId()
        );
        return UnifiedResponse.of(
                curriculumMapper.toResponse(result)
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
    public @Valid UnifiedResponse<CurriculumResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid
            CurriculumRequest payload
    ) {
        var result = curriculumService.get(
                curriculumService.update(id, payload)
                        .getId()
        );
        return UnifiedResponse.of(
                curriculumMapper.toResponse(result)
        );
    }
}
