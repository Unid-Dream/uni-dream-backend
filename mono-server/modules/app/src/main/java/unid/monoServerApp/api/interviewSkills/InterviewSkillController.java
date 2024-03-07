package unid.monoServerApp.api.interviewSkills;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.monoServerApp.api.ACL;
import unid.monoServerMeta.api.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/interviewSkill")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Access Interview Skills")
@Slf4j
public class InterviewSkillController {
    private final InterviewSkillService interviewSkillService;


    @GetMapping
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<InterviewTopicResponse> query() {
        return UnifiedResponse.of(interviewSkillService.query());
    }


    @GetMapping("{studentProfileId}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<InterviewSkillAssessmentResponse> query(
            @PathVariable UUID studentProfileId
    ) {
        return UnifiedResponse.of(interviewSkillService.query(studentProfileId));
    }


    @PostMapping("/{studentProfileId}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Save Writing-Skill's File And Create Transaction"
    )
    public @Valid UnifiedResponse<UUID> save(
            @PathVariable UUID studentProfileId,
            @Valid @RequestBody InterviewSkillRequest request) {
        return UnifiedResponse.of(
                interviewSkillService.save(studentProfileId,request)
        );
    }


}
