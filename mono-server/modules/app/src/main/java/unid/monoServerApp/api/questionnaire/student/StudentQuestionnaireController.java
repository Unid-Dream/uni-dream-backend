package unid.monoServerApp.api.questionnaire.student;

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
import unid.monoServerMeta.api.StudentMilestoneRequest;
import unid.monoServerMeta.api.StudentMilestoneResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/studentMilestone/{studentProfileId}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Student Questionnaire")
@Slf4j
public class StudentQuestionnaireController {
    private final StudentQuestionnaireService studentQuestionnaireService;

    @GetMapping
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<StudentMilestoneResponse> query(
            @PathVariable UUID studentProfileId
    ){
        return UnifiedResponse.of(studentQuestionnaireService.query(studentProfileId));
    }


    @PostMapping
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Save Or Update"
    )
    public @Valid UnifiedResponse<Void> saveOrUpdate(
            @PathVariable UUID studentProfileId,
            @RequestBody StudentMilestoneRequest request
            ){
        studentQuestionnaireService.saveOrUpdate(studentProfileId,request);
        return UnifiedResponse.of(null);
    }


}
