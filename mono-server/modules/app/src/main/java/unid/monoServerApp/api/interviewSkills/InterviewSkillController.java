package unid.monoServerApp.api.interviewSkills;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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

import javax.validation.Valid;
import java.util.List;
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




    @GetMapping("admin/{userId}/interviewSkill/page")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Student Writing Skill Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<InterviewSkillPayload>> query(
            @PathVariable UUID userId,
            @ParameterObject InterviewSkillPageRequest request
    ) {
        return UnifiedResponse.of(
                interviewSkillService.query(request)
        );
    }

    @GetMapping("admin/{userId}/interviewSkill/{id}")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Student Writing Skill Page"
    )
    public @Valid UnifiedResponse<InterviewSkillPayload> get(
            @PathVariable("userId") UUID userId,
            @PathVariable("id") UUID id
    ) {
        return UnifiedResponse.of(
                interviewSkillService.get(id)
        );
    }


    @PutMapping("admin/{userId}/interviewSkill")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update Student Writing Skill Review"
    )
    public @Valid UnifiedResponse<InterviewSkillPayload> update(
            @PathVariable("userId") UUID userId,
            @RequestBody InterviewSkillPayload payload
    ) {
        return UnifiedResponse.of(
                interviewSkillService.get(
                        interviewSkillService.update(payload).getId()
                )
        );
    }


}
