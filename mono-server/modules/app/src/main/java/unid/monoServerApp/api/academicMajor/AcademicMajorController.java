package unid.monoServerApp.api.academicMajor;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.academicSubject.AcademicSubjectService;
import unid.monoServerApp.api.tag.TagService;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerApp.mapper.AcademicMajorMapper;
import unid.monoServerMeta.api.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Academic Majors")
@Slf4j
public class AcademicMajorController {
    private final AcademicMajorService academicMajorService;
    private final AcademicSubjectService academicSubjectService;

    private final AcademicMajorMapper academicMajorMapper;
    private final DbAcademicMajor dbAcademicMajor;
    private final ObjectMapper objectMapper;
    private final TagService tagService;


    @GetMapping("admin/{userId}/academicMajor/page")
    @Transactional
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Page"
    )
    public UnifiedResponse<UniPageResponse<AcademicMajorPayload>> page(
            @PathVariable("userId") UUID userId,
            @ParameterObject AcademicMajorPageRequest request
    ){
        return UnifiedResponse.of(
                academicMajorService.page(request)
        );
    }

    @PostMapping("admin/{userId}/academicMajor")
    @Transactional
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Create"
    )
    public UnifiedResponse<AcademicMajorPayload> create(
            @PathVariable("userId") UUID userId,
            @RequestBody AcademicMajorPayload payload
    ){
        return UnifiedResponse.of(
                academicMajorService.get(academicMajorService.create(payload).getId())
        );
    }


    @PutMapping("admin/{userId}/academicMajor")
    @Transactional
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update"
    )
    public UnifiedResponse<AcademicMajorPayload> update(
            @PathVariable("userId") UUID userId,
            @ParameterObject AcademicMajorPayload payload
    ){
        return UnifiedResponse.of(
                academicMajorService.get(academicMajorService.update(payload).getId())
        );
    }


    @GetMapping("admin/{userId}/academicMajor/{id}")
    @Transactional
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public UnifiedResponse<AcademicMajorPayload> get(
            @PathVariable("userId") UUID userId,
            @PathVariable("id") UUID id
    ){
        return UnifiedResponse.of(
                academicMajorService.get(id)
        );
    }



    @GetMapping("student/academicMajor")
    @Transactional
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "List"
    )
    public UnifiedResponse<List<AcademicMajorResponse>> list(){
        List<AcademicMajorResponse> list = academicMajorService.list();
        return UnifiedResponse.of(list);
    }


    @GetMapping(value={"educator/academicMajor/tags","admin/academicMajor/tags"})
    @Transactional
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Academic Major Tags"
    )
    public UnifiedResponse<List<TagResponse>> tags(){
        List<TagResponse> list = tagService.list(TagCategoryEnum.ACADEMIC_MAJOR);
        return UnifiedResponse.of(
                list
        );
    }

    @GetMapping("student/academicMajor/{academicMajorId}")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public UnifiedResponse<AcademicMajorResponse> getOne(
            @PathVariable UUID academicMajorId
    ){
        return UnifiedResponse.of(
                academicMajorService.getOne(academicMajorId,null)
        );
    }


    @GetMapping("student/academicMajor/{academicMajorId}/subject/{academicSubjectId}")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get Subject"
    )
    public UnifiedResponse<AcademicMajorResponse> getOne(
            @PathVariable UUID academicMajorId,
            @PathVariable UUID academicSubjectId
    ){
        return UnifiedResponse.of(
                academicMajorService.getOne(academicMajorId,academicSubjectId)
        );
    }




//    @GetMapping("page")
//    @ACL(
//            authed = true
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Query"
//    )
//    public @Valid UnifiedResponse<PaginationResponse<AcademicMajorResponse>> list(
//            @Valid
//            @ParameterObject
//            PaginationRequest payload
//    ) {
//        var table = AcademicMajorPagination.TABLE;
//        var dslContext = dbAcademicMajor.getDsl();
//        var extraConditionOnFirstPage = DSL.noCondition();
//        if (StringUtils.isBlank(payload.getPage())) {
//            extraConditionOnFirstPage = dbAcademicMajor.validateCondition(table);
//        }
//        var result = PaginatedQuery.init(
//                        dslContext,
//                        objectMapper,
//                        payload,
//                        RequestHolder.get().getAuthToken(),
//                        Constant.PAGINATION_MIN_SIZE,
//                        Constant.PAGINATION_MAX_SIZE
//                )
//                .select(dsl -> dbAcademicMajor.getQuery(table))
//                .conditions(AcademicMajorPagination.conditionVisitor)
//                .extraConditions(extraConditionOnFirstPage)
//                .sortBy(
//                        AcademicMajorPagination.orderingVisitor,
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
//                PaginationResponse.asResult(result, academicMajorMapper.toResponse(result.getResult().into(DbAcademicMajor.Result.class)))
//        );
//    }
//
//
//    @GetMapping("{id}")
//    @ACL(
//            authed = true
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Get One"
//    )
//    public @Valid UnifiedResponse<AcademicMajorResponse> get(
//            @PathVariable("id") UUID id
//    ) {
//        var result = academicMajorService.get(id);
//        return UnifiedResponse.of(
//                academicMajorMapper.toResponse(result)
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
//    public @Valid UnifiedResponse<AcademicMajorResponse> create(
//            @RequestBody @Valid
//            AcademicMajorRequest payload
//    ) {
//        var result = academicMajorService.get(
//                academicMajorService.create(payload)
//                        .getId()
//        );
//        return UnifiedResponse.of(
//                academicMajorMapper.toResponse(result)
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
//    public @Valid UnifiedResponse<AcademicMajorResponse> update(
//            @PathVariable("id") UUID id,
//            @RequestBody @Valid
//            AcademicMajorRequest payload
//    ) {
//        var result = academicMajorService.get(
//                academicMajorService.update(id, payload)
//                        .getId()
//        );
//        return UnifiedResponse.of(
//                academicMajorMapper.toResponse(result)
//        );
//    }
//
//    @PostMapping("byList")
//    @Transactional
////    @ACL(
////            authed = true,
////            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
////    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Create By List"
//    )
//    public @Valid UnifiedResponse<Void> createByList(
//            @RequestBody List<AcademicMajorBatchRequest> req) {
//        academicMajorService.createByList(req);
//        return UnifiedResponse.of(
//                null
//        );
//    }
//
//
//    @PostMapping("list")
//    @Transactional
//    @ACL(
//            authed = true,
//            allowedRoles = {UserRoleEnum.STUDENT}
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Query List"
//    )
//    public @Valid UnifiedResponse<SchoolResponse> list(
//            @RequestParam List<AcademicMajorBatchRequest> req) {
//        academicMajorService.createByList(req);
//        return UnifiedResponse.of(
//                null
//        );
//    }

}
