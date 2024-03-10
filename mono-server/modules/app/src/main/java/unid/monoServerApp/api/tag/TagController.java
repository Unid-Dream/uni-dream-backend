package unid.monoServerApp.api.tag;//package unid.monoServerApp.api.country;

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
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.TagMapper;
import unid.monoServerMeta.api.TagRequest;
import unid.monoServerMeta.api.TagResponse;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/tag")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Tags")
@Slf4j
@Hidden
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;
    private final DbTag dbTag;
    private final ObjectMapper objectMapper;

    @GetMapping
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<PaginationResponse<TagResponse>> list(
            @Valid
            @ParameterObject
            PaginationRequest payload
    ) {
        var table = TagPagination.TABLE;
        var dslContext = dbTag.getDsl();
        var extraConditionOnFirstPage = DSL.noCondition();
        if (StringUtils.isBlank(payload.getPage())) {
            extraConditionOnFirstPage = dbTag.validateCondition(table);
        }
        var result = PaginatedQuery.init(
                        dslContext,
                        objectMapper,
                        payload,
                        RequestHolder.get().getAuthToken(),
                        Constant.PAGINATION_MIN_SIZE,
                        Constant.PAGINATION_MAX_SIZE
                )
                .select(dsl -> dbTag.getQuery(table))
                .conditions(TagPagination.conditionVisitor)
                .extraConditions(extraConditionOnFirstPage)
                .sortBy(
                        TagPagination.orderingVisitor,
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
                PaginationResponse.asResult(result, tagMapper.toResponse(result.getResult().into(DbTag.Result.class)))
        );
    }

    @GetMapping("{id}")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public @Valid UnifiedResponse<TagResponse> get(
            @PathVariable("id") UUID id
    ) {
        var result = tagService.get(id);
        return UnifiedResponse.of(
                tagMapper.toResponse(result)
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
    public @Valid UnifiedResponse<TagResponse> create(
            @RequestBody @Valid
            TagRequest payload
    ) {
        var result = tagService.get(
                tagService.create(payload)
                        .getId()
        );
        return UnifiedResponse.of(
                tagMapper.toResponse(result)
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
    public @Valid UnifiedResponse<TagResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid
            TagRequest payload
    ) {
        var result = tagService.get(
                tagService.update(id, payload)
                        .getId()
        );
        return UnifiedResponse.of(
                tagMapper.toResponse(result)
        );
    }
}
