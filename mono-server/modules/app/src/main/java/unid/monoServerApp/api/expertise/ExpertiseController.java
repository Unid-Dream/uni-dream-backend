package unid.monoServerApp.api.expertise;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import unid.monoServerApp.database.table.expertise.DbExpertise;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.ExpertiseMapper;
import unid.monoServerMeta.api.ExpertiseRequest;
import unid.monoServerMeta.api.ExpertiseResponse;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/expertise")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Expertises")
@Slf4j
public class ExpertiseController {
    private final ExpertiseService expertiseService;
    private final ExpertiseMapper expertiseMapper;
    private final DbExpertise dbExpertise;
    private final ObjectMapper objectMapper;

    @GetMapping
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<PaginationResponse<ExpertiseResponse>> list(
            @Valid
            @ParameterObject
            PaginationRequest payload
    ) {
        var table = ExpertisePagination.TABLE;
        var dslContext = dbExpertise.getDsl();
        var extraConditionOnFirstPage = DSL.noCondition();
        if (StringUtils.isBlank(payload.getPage())) {
            extraConditionOnFirstPage = dbExpertise.validateCondition(table);
        }
        var result = PaginatedQuery.init(
                        dslContext,
                        objectMapper,
                        payload,
                        RequestHolder.get().getAuthToken(),
                        Constant.PAGINATION_MIN_SIZE,
                        Constant.PAGINATION_MAX_SIZE
                )
                .select(dsl -> dbExpertise.getQuery(table))
                .conditions(ExpertisePagination.conditionVisitor)
                .extraConditions(extraConditionOnFirstPage)
                .sortBy(
                        ExpertisePagination.orderingVisitor,
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
                PaginationResponse.asResult(result, expertiseMapper.toResponse(result.getResult().into(DbExpertise.Result.class)))
        );
    }

    @GetMapping("list")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get List"
    )
    public @Valid UnifiedResponse<List<I18n>> list() {
        var result = expertiseService.list();
        return UnifiedResponse.of(
                result
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
    public @Valid UnifiedResponse<ExpertiseResponse> get(
            @PathVariable("id") UUID id
    ) {
        var result = expertiseService.get(id);
        return UnifiedResponse.of(
                expertiseMapper.toResponse(result)
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
    public @Valid UnifiedResponse<ExpertiseResponse> create(
            @RequestBody @Valid
            ExpertiseRequest payload
    ) {
        var result = expertiseService.get(
                expertiseService.create(payload)
                        .getId()
        );
        return UnifiedResponse.of(
                expertiseMapper.toResponse(result)
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
    public @Valid UnifiedResponse<ExpertiseResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid
            ExpertiseRequest payload
    ) {
        var result = expertiseService.get(
                expertiseService.update(id, payload)
                        .getId()
        );
        return UnifiedResponse.of(
                expertiseMapper.toResponse(result)
        );
    }
}
