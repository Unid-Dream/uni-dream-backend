package unid.monoServerApp.api.ecaCourse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.api.ACL;
import unid.monoServerMeta.api.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Eca Profile")
@Slf4j
public class EcaProfileController {
    private final EcaProfileService ecaProfileService;

    @GetMapping("/student/ecaProfile")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<List<EcaProfileSectionResponse>> list() {
         return UnifiedResponse.of(ecaProfileService.query());
    }


    @PostMapping(value = {"/student/ecaProfile/{studentProfileId}"})
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.STUDENT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Save"
    )
    public @Valid UnifiedResponse<List<StudentEcaProfileSectionResponse>> save(
            @PathVariable @ACL.ProfileId UUID studentProfileId,
            @RequestBody EcaProfileRequest request
    ) {
        ecaProfileService.saveOrUpdate(studentProfileId,request);
        return UnifiedResponse.of(
                ecaProfileService.get(studentProfileId)
        );
    }


    @GetMapping(value= { "/student/ecaProfile/{studentProfileId}","/admin/ecaProfile/{studentProfileId}"})
    @ACL(
            authed = true,
            allowedRoles =  { UserRoleEnum.STUDENT, UserRoleEnum.ADMIN }
    )
    
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get Submit Score"
    )
    public @Valid UnifiedResponse<List<StudentEcaProfileSectionResponse>> get(
            @PathVariable @ACL.ProfileId UUID studentProfileId
    ) {
        return UnifiedResponse.of(
                ecaProfileService.get(studentProfileId).isEmpty()?null:ecaProfileService.get(studentProfileId)
        );
    }
}
