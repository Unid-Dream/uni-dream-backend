package unid.monoServerApp.api.writingSkills;


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
@Tag(name = "Access Writing Skills")
@Slf4j
public class WritingSkillController {
    private final WritingSkillService writingSkillService;


    @GetMapping("student/writingSkill")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<WritingTopicResponse> query() {
        return UnifiedResponse.of(writingSkillService.query());
    }


    @GetMapping("student/writingSkill/{studentProfileId}")
    @ACL(
            authed = true,
            matchingSessionProfileId = true,
            allowedRoles = {UserRoleEnum.STUDENT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<WritingSkillAssessmentResponse> query(
            @PathVariable @ACL.ProfileId UUID studentProfileId
    ) {
        return UnifiedResponse.of(writingSkillService.query(studentProfileId));
    }


    @GetMapping("admin/{userId}/writingSkill/page")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Student Writing Skill Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<WritingSkillPayload>> query(
            @PathVariable UUID userId,
            @ParameterObject WritingSkillPageRequest request
    ) {
        return UnifiedResponse.of(
                writingSkillService.page(request)
        );
    }

    @GetMapping("admin/{userId}/writingSkill/{id}")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Student Writing Skill Page"
    )
    public @Valid UnifiedResponse<WritingSkillPayload> get(
            @PathVariable("userId") UUID userId,
            @PathVariable("id") UUID id
    ) {
        return UnifiedResponse.of(
                writingSkillService.get(id)
        );
    }


    @PutMapping("admin/{userId}/writingSkill")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update Student Writing Skill Review"
    )
    public @Valid UnifiedResponse<WritingSkillPayload> update(
            @PathVariable("userId") UUID userId,
            @RequestBody WritingSkillPayload payload
    ) {
        return UnifiedResponse.of(
                writingSkillService.get(
                        writingSkillService.update(payload).getId()
                )
        );
    }



    @PostMapping("student/writingSkill/{studentProfileId}")
    @ACL(
            authed = true,
            matchingSessionProfileId = true,
            allowedRoles = {UserRoleEnum.STUDENT}
    )
    
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Save Writing-Skill's File And Create Transaction"
    )
    public @Valid UnifiedResponse<UUID> save(
            @PathVariable @ACL.ProfileId UUID studentProfileId,
            @Valid @RequestBody WritingSkillRequest request) {
        return UnifiedResponse.of(
                writingSkillService.save(studentProfileId,request)
        );
    }


}
