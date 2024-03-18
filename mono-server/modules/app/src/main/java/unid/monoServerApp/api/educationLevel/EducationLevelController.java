package unid.monoServerApp.api.educationLevel;//package unid.monoServerApp.api.country;

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
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Constant;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.tag.TagService;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.EducationLevelMapper;
import unid.monoServerMeta.api.EducationLevelRequest;
import unid.monoServerMeta.api.EducationLevelResponse;
import unid.monoServerMeta.api.TagResponse;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Education Levels")
@Slf4j
public class EducationLevelController {
    private final EducationLevelService educationLevelService;
    private final EducationLevelMapper educationLevelMapper;
    private final DbEducationLevel dbEducationLevel;
    private final ObjectMapper objectMapper;
    private final TagService tagService;

    @GetMapping(value = {"/educator/educationLevel/tags","/student/educationLevel/tags"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<List<TagResponse>> tags() {
        var list = tagService.list(TagCategoryEnum.EDUCATION_LEVEL);
        return UnifiedResponse.of(list);
//        var table = EducationLevelPagination.TABLE;
//        var dslContext = dbEducationLevel.getDsl();
//        var extraConditionOnFirstPage = DSL.noCondition();
//        if (StringUtils.isBlank(payload.getPage())) {
//            extraConditionOnFirstPage = dbEducationLevel.validateCondition(table);
//        }
//        var result = PaginatedQuery.init(
//                        dslContext,
//                        objectMapper,
//                        payload,
//                        RequestHolder.get().getAuthToken(),
//                        Constant.PAGINATION_MIN_SIZE,
//                        Constant.PAGINATION_MAX_SIZE
//                )
//                .select(dsl -> dbEducationLevel.getQuery(table))
//                .conditions(EducationLevelPagination.conditionVisitor)
//                .extraConditions(extraConditionOnFirstPage)
//                .sortBy(
//                        EducationLevelPagination.orderingVisitor,
//                        null,
//                        null,
//                        null,
//                        uniqueSort -> {
//                            uniqueSort.add(
//                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
//                                            table.CREATED_ON,
//                                            SortOrder.DESC,
//                                            OrderingVisitor.Seeking.builder()
//                                                    .whenSeeking(OffsetDateTime::parse)
//                                                    .build()
//                                    )
//                            );
//                            uniqueSort.add(
//                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
//                                            table.ID,
//                                            SortOrder.DESC,
//                                            OrderingVisitor.Seeking.builder()
//                                                    .whenSeeking(UUID::fromString)
//                                                    .build()
//                                    )
//                            );
//                        }
//                )
//                .fetch();
//        return UnifiedResponse.of(
//                PaginationResponse.asResult(result, educationLevelMapper.toResponse(result.getResult().into(DbEducationLevel.Result.class)))
//        );
    }







//    @GetMapping
//    @ACL(
//            authed = true
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Query"
//    )
//    public @Valid UnifiedResponse<PaginationResponse<EducationLevelResponse>> list(
//            @Valid
//            @ParameterObject
//            PaginationRequest payload
//    ) {
//        var table = EducationLevelPagination.TABLE;
//        var dslContext = dbEducationLevel.getDsl();
//        var extraConditionOnFirstPage = DSL.noCondition();
//        if (StringUtils.isBlank(payload.getPage())) {
//            extraConditionOnFirstPage = dbEducationLevel.validateCondition(table);
//        }
//        var result = PaginatedQuery.init(
//                        dslContext,
//                        objectMapper,
//                        payload,
//                        RequestHolder.get().getAuthToken(),
//                        Constant.PAGINATION_MIN_SIZE,
//                        Constant.PAGINATION_MAX_SIZE
//                )
//                .select(dsl -> dbEducationLevel.getQuery(table))
//                .conditions(EducationLevelPagination.conditionVisitor)
//                .extraConditions(extraConditionOnFirstPage)
//                .sortBy(
//                        EducationLevelPagination.orderingVisitor,
//                        null,
//                        null,
//                        null,
//                        uniqueSort -> {
//                            uniqueSort.add(
//                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
//                                            table.CREATED_ON,
//                                            SortOrder.DESC,
//                                            OrderingVisitor.Seeking.builder()
//                                                    .whenSeeking(OffsetDateTime::parse)
//                                                    .build()
//                                    )
//                            );
//                            uniqueSort.add(
//                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
//                                            table.ID,
//                                            SortOrder.DESC,
//                                            OrderingVisitor.Seeking.builder()
//                                                    .whenSeeking(UUID::fromString)
//                                                    .build()
//                                    )
//                            );
//                        }
//                )
//                .fetch();
//        return UnifiedResponse.of(
//                PaginationResponse.asResult(result, educationLevelMapper.toResponse(result.getResult().into(DbEducationLevel.Result.class)))
//        );
//    }
//
//    @GetMapping("{id}")
//    @ACL(
//            authed = true
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Get One"
//    )
//    public @Valid UnifiedResponse<EducationLevelResponse> get(
//            @PathVariable("id") UUID id
//    ) {
//        var result = educationLevelService.get(id);
//        return UnifiedResponse.of(
//                educationLevelMapper.toResponse(result)
//        );
//    }
//
//    @PostMapping
//    @Transactional
//    @ACL(
//            authed = true,
//            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
//    )
//    @ResponseStatus(HttpStatus.CREATED)
//    @Operation(
//            summary = "Create One"
//    )
//    public @Valid UnifiedResponse<EducationLevelResponse> create(
//            @RequestBody @Valid
//            EducationLevelRequest payload
//    ) {
//        var result = educationLevelService.get(
//                educationLevelService.create(payload)
//                        .getId()
//        );
//        return UnifiedResponse.of(
//                educationLevelMapper.toResponse(result)
//        );
//    }
//
//    @PutMapping("{id}")
//    @Transactional
//    @ACL(
//            authed = true,
//            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Update One"
//    )
//    public @Valid UnifiedResponse<EducationLevelResponse> update(
//            @PathVariable("id") UUID id,
//            @RequestBody @Valid
//            EducationLevelRequest payload
//    ) {
//        var result = educationLevelService.get(
//                educationLevelService.update(id, payload)
//                        .getId()
//        );
//        return UnifiedResponse.of(
//                educationLevelMapper.toResponse(result)
//        );
//    }
}
