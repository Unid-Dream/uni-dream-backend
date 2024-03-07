package unid.monoServerApp.api.user.profile.educator;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
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
import pwh.coreRsqlJooq.jooq.PaginationCursor;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreRsqlJooq.model.PaginationResponse;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.springWebStarter.response.UnifiedResponse;
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
@RequestMapping("api/user")
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


    @GetMapping("/profile/educator/page")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<EducatorResponse>> page(
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
                        languages)
        );
    }


    @GetMapping("/{userId}/profile/educator")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public @Valid UnifiedResponse<EducatorResponse> get(
            @PathVariable("userId") UUID userId
    ) {
        var result = educatorProfileService.getCourseEducator(userId);
        return UnifiedResponse.of(
                result
        );
    }

    @PostMapping("/{userId}/profile/educator")
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
    public @Valid UnifiedResponse<EducatorProfileResponse> create(
            @PathVariable("userId") @ACL.UserId UUID userId,
            @RequestBody @Valid
            EducatorProfileRequest payload
    ) {
        var result = educatorProfileService.get(
                educatorProfileService.create(userId, payload)
                        .getUserId()
        );
        var response = educatorProfileMapper.toResponse(result);
        tagAppendService.refresh(result);
        return UnifiedResponse.of(response);
    }

    @PutMapping("/{userId}/profile/educator")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR, UserRoleEnum.ADMIN, UserRoleEnum.ROOT},
            matchingSessionUserId = true,
            skipMatchingForAdministrative = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    public @Valid UnifiedResponse<EducatorProfileResponse> update(
            @PathVariable("userId") @ACL.UserId UUID userId,
            @RequestBody @Valid
            EducatorProfileRequest payload
    ) {
        var result = educatorProfileService.get(
                educatorProfileService.update(userId, payload)
                        .getUserId()
        );
        var response = educatorProfileMapper.toResponse(result);
        tagAppendService.refresh(result);
        return UnifiedResponse.of(response);
    }
}
