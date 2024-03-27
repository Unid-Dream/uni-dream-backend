package unid.monoServerApp.api.interviewSkills;


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
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.api.ACL;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.api.version2.InterviewSkillAssignPayload;
import unid.monoServerMeta.api.version2.request.InterviewSkillAssignRequest;
import unid.monoServerMeta.api.version2.request.InterviewSkillRemindRequest;
import unid.monoServerMeta.api.version2.request.InterviewSkillReviewRequest;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Access Interview Skills")
@Slf4j
public class InterviewSkillController {
    private final InterviewSkillService interviewSkillService;


    @GetMapping("student/interviewSkill")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<InterviewTopicResponse> query() {
        return UnifiedResponse.of(interviewSkillService.query());
    }


    @GetMapping("student/interviewSkill/{studentProfileId}")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT,
            matchingSessionProfileId = true
    )
    
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<InterviewSkillAssessmentResponse> query(
            @PathVariable @ACL.ProfileId UUID studentProfileId
    ) {
        return UnifiedResponse.of(interviewSkillService.query(studentProfileId));
    }


    @PostMapping("student/interviewSkill/{studentProfileId}")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT,
            matchingSessionProfileId = true
    )
    
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Save Writing-Skill's File And Create Transaction"
    )
    public @Valid UnifiedResponse<UUID> save(
            @PathVariable @ACL.ProfileId UUID studentProfileId,
            @Valid @RequestBody InterviewSkillRequest request) {
        return UnifiedResponse.of(
                interviewSkillService.save(studentProfileId,request)
        );
    }




    @GetMapping("admin/interviewSkill/page")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Student Writing Skill Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<InterviewSkillPayload>> page(
            @ParameterObject InterviewSkillPageRequest request
    ) {
        return UnifiedResponse.of(
                interviewSkillService.page(request)
        );
    }

    @GetMapping("admin/interviewSkill/{id}")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Student Writing Skill Page"
    )
    public @Valid UnifiedResponse<InterviewSkillPayload> get(
            @PathVariable("id") UUID id
    ) {
        return UnifiedResponse.of(
                interviewSkillService.get(id)
        );
    }


    @PutMapping("admin/interviewSkill")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update Student Writing Skill Review"
    )
    public @Valid UnifiedResponse<InterviewSkillPayload> update(
            @RequestBody InterviewSkillReviewRequest payload
    ) {
        return UnifiedResponse.of(
                interviewSkillService.get(
                        interviewSkillService.update(payload)
                )
        );
    }



    @PutMapping("admin/interviewSkill/assign")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = " Assign Educator"
    )
    public @Valid UnifiedResponse<InterviewSkillAssignPayload> assign(
            @RequestBody InterviewSkillAssignRequest payload
    ) {
        return UnifiedResponse.of(
                interviewSkillService.update(payload)
        );
    }

    @PutMapping("admin/interviewSkill/remind")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = " Remind Educator"
    )
    public @Valid UnifiedResponse<Void> remind(
            @RequestBody InterviewSkillRemindRequest payload
    ) {
        interviewSkillService.remind(payload);
        return UnifiedResponse.of(
                null
        );
    }


}
