package unid.monoServerApp.api.ecaCourse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springdoc.api.annotations.ParameterObject;
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
@RequestMapping("api/student/ecaProfile")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Eca Profile")
@Slf4j
public class EcaProfileController {
    private final EcaProfileService ecaProfileService;

    @GetMapping
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<List<EcaProfileSectionResponse>> list() {
         return UnifiedResponse.of(ecaProfileService.query());
    }


    @PostMapping("/{studentProfileId}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Save"
    )
    public @Valid UnifiedResponse<List<StudentEcaProfileSectionResponse>> save(
            @RequestBody EcaProfileRequest request,
            @PathVariable UUID studentProfileId
    ) {
        ecaProfileService.saveOrUpdate(studentProfileId,request);
        return UnifiedResponse.of(
                ecaProfileService.get(studentProfileId)
        );
    }


    @GetMapping("/{studentProfileId}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get Submit Score"
    )
    public @Valid UnifiedResponse<List<StudentEcaProfileSectionResponse>> get(
            @PathVariable UUID studentProfileId
    ) {
        return UnifiedResponse.of(
                ecaProfileService.get(studentProfileId).isEmpty()?null:ecaProfileService.get(studentProfileId)
        );
    }
}
