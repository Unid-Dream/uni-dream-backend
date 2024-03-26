package unid.monoServerApp.api.educationLevel;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.pojos.TagPojo;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.tag.TagService;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.mapper.EducationLevelMapper;
import unid.monoServerApp.mapper.TagMapper;
import unid.monoServerMeta.api.TagRequest;
import unid.monoServerMeta.api.TagResponse;
import unid.monoServerMeta.api.UniPageResponse;
import unid.monoServerMeta.api.version2.request.TagCategoryCreateRequest;
import unid.monoServerMeta.api.version2.request.TagCategoryUpdateRequest;
import unid.monoServerMeta.api.version2.request.TagPagePayload;
import unid.monoServerMeta.model.TagCategory;

import javax.validation.Valid;
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
    private final TagMapper tagMapper;

    @GetMapping(value = {"/educator/educationLevel/tags","/student/educationLevel/tags","/admin/educationLevel/tags"})
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
    }


    @GetMapping(value = {"/admin/educationLevel/page"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<TagResponse>> page(
            @ParameterObject TagPagePayload payload
    ) {
        return UnifiedResponse.of(
                tagService.page(payload,TagCategoryEnum.EDUCATION_LEVEL)
        );
    }


    @PostMapping(value = {"/admin/educationLevel"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Create"
    )
    public @Valid UnifiedResponse<TagResponse> create(
            @RequestBody TagCategoryCreateRequest payload
    ) {
        TagRequest request = new TagRequest();
        request.setDescriptionI18n(payload.getDescriptionI18n());
        request.setTagCategory(TagCategory.EDUCATION_LEVEL);
        var id = tagService.create(request).getId();
        return UnifiedResponse.of(
                tagMapper.toResponse(tagService.get(id))
        );
    }

    @PutMapping(value = {"/admin/educationLevel"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update"
    )
    public @Valid UnifiedResponse<TagResponse> update(
            @RequestBody TagCategoryUpdateRequest payload
    ) {
        TagRequest request = new TagRequest();
        request.setDescriptionI18n(payload.getDescriptionI18n());
        request.setTagCategory(TagCategory.EDUCATION_LEVEL);
        var id = tagService.update(payload.getId(),request).getId();
        return UnifiedResponse.of(
                tagMapper.toResponse(tagService.get(id))
        );
    }


    @DeleteMapping(value = {"/admin/educationLevel/{id}"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Delete"
    )
    public @Valid UnifiedResponse<Void> delete(
            @PathVariable UUID id
    ) {
        tagService.delete(id);
        return UnifiedResponse.of(
               null
        );
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
