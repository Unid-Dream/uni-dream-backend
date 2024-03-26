package unid.monoServerApp.api.user.profile.educator;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
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
import pwh.coreRsqlJooq.jooq.PaginationCursor;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreRsqlJooq.model.PaginationResponse;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.ApplicationApprovalEnum;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.jooqMono.model.tables.EducatorProfileTable;
import unid.monoServerApp.Constant;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.tag.TagPagination;
import unid.monoServerApp.database.service.TagAppendService;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.EducatorProfileMapper;
import unid.monoServerMeta.api.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Educator Profile")
@Slf4j
public class EducatorProfileController {
    private final EducatorProfileService educatorProfileService;
    private final EducatorProfileMapper educatorProfileMapper;
    private final TagAppendService tagAppendService;
    private final DbEducatorProfile dbEducatorProfile;
    private final ObjectMapper objectMapper;


    @GetMapping(value = {"student/user/profile/educator/page" } )
    @ACL(
            authed = true,
            allowedRoles = { UserRoleEnum.STUDENT,UserRoleEnum.ADMIN }
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<EducatorProfileSimpleResponse>> page(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) List<String> schools,
            @RequestParam(required = false) List<String> subjects,
            @RequestParam(required = false) List<String> expertises,
            @RequestParam(required = false) List<String> languages) {
        if(pageSize == null || pageNumber == null){
            throw Exceptions.badRequest(" Page Parameter Must Not Empty");
        }
        return UnifiedResponse.of(
                educatorProfileService.page(
                        pageNumber,
                        pageSize,
                        schools,
                        subjects,
                        expertises,
                        languages,null)
        );
    }


    @GetMapping(value = {"admin/user/profile/educator/application/page" } )
    @ACL(
            authed = true,
            allowedRoles = { UserRoleEnum.ADMIN }
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<EducatorProfileSimpleResponse>> getApplicationPage(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        if(pageSize == null || pageNumber == null){
            throw Exceptions.badRequest(" Page Parameter Must Not Empty");
        }
        return UnifiedResponse.of(
                educatorProfileService.page(
                        pageNumber,
                        pageSize,
                        null,null,null,null,
                        ApplicationApprovalEnum.PENDING)
        );
    }


    @GetMapping(value = {"admin/user/profile/educator/page" } )
    @ACL(
            authed = true,
            allowedRoles = { UserRoleEnum.ADMIN }
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<EducatorProfileSimpleResponse>> page(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        if(pageSize == null || pageNumber == null){
            throw Exceptions.badRequest(" Page Parameter Must Not Empty");
        }
        return UnifiedResponse.of(
                educatorProfileService.page(
                        pageNumber,
                        pageSize,
                        null,
                        null,
                        null,
                        null,null)
        );
    }


    @GetMapping(value = { "student/user/{educatorProfileId}/profile/educator","educator/user/{educatorProfileId}/profile/educator","admin/user/{educatorProfileId}/profile/educator"})
    @ACL(
            authed = true,
            allowedRoles = { UserRoleEnum.STUDENT, UserRoleEnum.EDUCATOR,UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One Educator"
    )
    public @Valid UnifiedResponse<EducatorProfileSimpleResponse> get(
            @PathVariable("educatorProfileId") UUID educatorProfileId
    ) {
        var result = educatorProfileService.getSimpleCache(educatorProfileId);
        return UnifiedResponse.of(
                result
        );
    }

    @PostMapping("student/user/{educatorProfileId}/profile/educator")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR},
            matchingSessionUserId = true,
            skipMatchingForAdministrative = true
    )
    
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create One"
    )
    @Hidden
    public @Valid UnifiedResponse<EducatorProfileResponse> create(
            @PathVariable("educatorProfileId") @ACL.UserId UUID educatorProfileId,
            @RequestBody @Valid
            EducatorProfileRequest payload
    ) {
        var result = educatorProfileService.get(
                educatorProfileService.create(educatorProfileId, payload)
                        .getUserId()
        );
        var response = educatorProfileMapper.toResponse(result);
        //tagAppendService.refresh(result);
        return UnifiedResponse.of(response);
    }

    @PutMapping(value={"educator/user/{educatorProfileId}/profile/educator","admin/user/{educatorProfileId}/profile/educator"})
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR, UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    public @Valid UnifiedResponse<EducatorProfileSimpleResponse> update(
            @PathVariable("educatorProfileId") UUID educatorProfileId,
            @RequestBody @Valid
            EducatorProfileSimpleRequest payload
    ) {
        var result = educatorProfileService.getSimpleCache(
                educatorProfileService.update(educatorProfileId, payload)
                        .getId()
        );
        return UnifiedResponse.of(result);
    }


    @PutMapping(value={"admin/user/{educatorProfileId}/profile/educator/accept"})
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = { UserRoleEnum.ADMIN }
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Accept One"
    )
    public @Valid UnifiedResponse<EducatorProfileSimpleResponse> accept(
            @PathVariable("educatorProfileId") UUID educatorProfileId
    ) {
        var result = educatorProfileService.getSimpleCache(
                educatorProfileService.acceptOrReject(educatorProfileId, true)
                        .getId()
        );
        return UnifiedResponse.of(result);
    }


    @PutMapping(value={"admin/user/{educatorProfileId}/profile/educator/reject"})
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = { UserRoleEnum.ADMIN }
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Reject One"
    )
    public @Valid UnifiedResponse<EducatorProfileSimpleResponse> reject(
            @PathVariable("educatorProfileId") UUID educatorProfileId
    ) {
        var result = educatorProfileService.getSimpleCache(
                educatorProfileService.acceptOrReject(educatorProfileId, false)
                        .getId()
        );
        return UnifiedResponse.of(result);
    }



}
