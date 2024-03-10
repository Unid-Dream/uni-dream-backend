package unid.monoServerApp.api.language;//package unid.monoServerApp.api.country;

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
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Constant;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.tag.TagService;
import unid.monoServerApp.database.table.language.DbLanguage;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.LanguageMapper;
import unid.monoServerMeta.api.LanguageRequest;
import unid.monoServerMeta.api.LanguageResponse;
import unid.monoServerMeta.api.TagResponse;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Languages")
@Slf4j
public class LanguageController {
    private final LanguageService languageService;
    private final LanguageMapper languageMapper;
    private final DbLanguage dbLanguage;
    private final ObjectMapper objectMapper;
    private final TagService tagService;

    @GetMapping("/student/language")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    @Hidden
    public @Valid UnifiedResponse<PaginationResponse<LanguageResponse>> list(
            @Valid
            @ParameterObject
            PaginationRequest payload
    ) {
        var table = LanguagePagination.TABLE;
        var dslContext = dbLanguage.getDsl();
        var extraConditionOnFirstPage = DSL.noCondition();
        if (StringUtils.isBlank(payload.getPage())) {
            extraConditionOnFirstPage = dbLanguage.validateCondition(table);
        }
        var result = PaginatedQuery.init(
                        dslContext,
                        objectMapper,
                        payload,
                        RequestHolder.get().getAuthToken(),
                        Constant.PAGINATION_MIN_SIZE,
                        Constant.PAGINATION_MAX_SIZE
                )
                .select(dsl -> dbLanguage.getQuery(table))
                .conditions(LanguagePagination.conditionVisitor)
                .extraConditions(extraConditionOnFirstPage)
                .sortBy(
                        LanguagePagination.orderingVisitor,
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
                PaginationResponse.asResult(result, languageMapper.toResponse(result.getResult().into(DbLanguage.Result.class)))
        );
    }

    @GetMapping("/student/language/list")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get List"
    )
    public @Valid UnifiedResponse<List<I18n>> list() {
        var result = languageService.list();
        return UnifiedResponse.of(
                result
        );
    }

    @GetMapping("/educator/language/tags")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Tags"
    )
    public @Valid UnifiedResponse<List<TagResponse>> tags() {
        var result = tagService.list(TagCategoryEnum.LANGUAGE);
        return UnifiedResponse.of(
                result
        );
    }


    @GetMapping("/student/language/{id}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    @Hidden
    public @Valid UnifiedResponse<LanguageResponse> get(
            @PathVariable("id") UUID id
    ) {
        var result = languageService.get(id);
        return UnifiedResponse.of(
                languageMapper.toResponse(result)
        );
    }

    @PostMapping("/student/language/")
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
    public @Valid UnifiedResponse<LanguageResponse> create(
            @RequestBody @Valid
            LanguageRequest payload
    ) {
        var result = languageService.get(
                languageService.create(payload)
                        .getId()
        );
        return UnifiedResponse.of(
                languageMapper.toResponse(result)
        );
    }

    @PutMapping("/student/language/{id}")
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
    public @Valid UnifiedResponse<LanguageResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid
            LanguageRequest payload
    ) {
        var result = languageService.get(
                languageService.update(id, payload)
                        .getId()
        );
        return UnifiedResponse.of(
                languageMapper.toResponse(result)
        );
    }
}
