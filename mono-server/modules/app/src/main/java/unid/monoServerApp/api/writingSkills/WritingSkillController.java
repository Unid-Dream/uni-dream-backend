package unid.monoServerApp.api.writingSkills;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.api.ACL;
import unid.monoServerMeta.api.WritingSkillAssessmentResponse;
import unid.monoServerMeta.api.WritingSkillRequest;
import unid.monoServerMeta.api.WritingTopicResponse;

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
