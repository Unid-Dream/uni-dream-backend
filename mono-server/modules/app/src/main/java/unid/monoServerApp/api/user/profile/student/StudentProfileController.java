package unid.monoServerApp.api.user.profile.student;//package unid.monoServerApp.api.country;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.database.service.TagAppendService;
import unid.monoServerApp.mapper.StudentProfileMapper;
import unid.monoServerMeta.api.StudentProfileRequest;
import unid.monoServerMeta.api.StudentProfileResponse;
import unid.monoServerMeta.api.StudentProfileSchoolReportPayload;
import unid.monoServerMeta.api.StudentProfileSchoolReportRequest;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Student Profile")
@Slf4j
public class StudentProfileController {
    private final StudentProfileService studentProfileService;
    private final StudentProfileMapper studentProfileMapper;
    private final TagAppendService tagAppendService;

    @GetMapping(value = {"student/user/{userId}/profile/student"})
    @ACL(
            authed = true,
            matchingSessionUserId = true,
            allowedRoles = {UserRoleEnum.STUDENT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    
    public @Valid UnifiedResponse<StudentProfileResponse> get(
            @PathVariable @ACL.UserId UUID userId
    ) {
        var result = studentProfileService.get(userId);
        StudentProfileResponse response = studentProfileMapper.toResponse(result);
        studentProfileService.fill(result,response);
        return UnifiedResponse.of(
                response
        );
    }

    @GetMapping(value = {"educator/user/{profileId}/profile/student"})
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.EDUCATOR
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public @Valid UnifiedResponse<StudentProfileResponse> getByProfileId(
            @PathVariable @ACL.UserId UUID profileId
    ) {
        var result = studentProfileService.getByProfileId(profileId);
        StudentProfileResponse response = studentProfileMapper.toResponse(result);
        studentProfileService.fill(result,response);
        return UnifiedResponse.of(
                response
        );
    }

    @PostMapping("student/user/{userId}/profile/student")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.STUDENT},
            matchingSessionUserId = true,
            skipMatchingForAdministrative = true
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create One"
    )
    @Hidden
    public @Valid UnifiedResponse<StudentProfileResponse> create(
            @PathVariable("userId") @ACL.UserId UUID userId,
            @RequestBody @Valid
            StudentProfileRequest payload
    ) {
        var result = studentProfileService.get(
                studentProfileService.create(userId, payload)
                        .getUserId()
        );
        var response = studentProfileMapper.toResponse(result);
        //tagAppendService.refresh(result);
        return UnifiedResponse.of(response);
    }

    @PutMapping("student/user/{userId}/profile/student")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.STUDENT, UserRoleEnum.ADMIN, UserRoleEnum.ROOT},
            matchingSessionUserId = true
    )
    
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    public @Valid UnifiedResponse<StudentProfileResponse> update(
            @PathVariable("userId") @ACL.UserId UUID userId,
            @RequestBody @Valid
            StudentProfileRequest payload
    ) {
        var result = studentProfileService.get(
                studentProfileService.update(userId, payload)
                        .getUserId()
        );
        var response = studentProfileMapper.toResponse(result);
        studentProfileService.fill(result,response);
//        tagAppendService.refresh(result);
        return UnifiedResponse.of(response);
    }



    @DeleteMapping("student/user/{userId}/profile/student/schoolReport/{schoolReportId}")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.STUDENT, UserRoleEnum.ADMIN, UserRoleEnum.ROOT},
            matchingSessionUserId = true,
            skipMatchingForAdministrative = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Delete One Student Report"
    )
    
    public @Valid UnifiedResponse<StudentProfileResponse> delete(
            @PathVariable("userId") @ACL.ProfileId UUID userId,
            @PathVariable("schoolReportId") UUID schoolReportId
    ) {
        var result = studentProfileService.get(
                studentProfileService.deleteSchoolReport(userId, schoolReportId)
                        .getUserId()
        );
        var response = studentProfileMapper.toResponse(result);
        studentProfileService.fill(result,response);
        //tagAppendService.refresh(result);
        return UnifiedResponse.of(response);
    }

}
