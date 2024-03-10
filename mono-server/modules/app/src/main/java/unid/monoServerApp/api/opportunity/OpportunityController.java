package unid.monoServerApp.api.opportunity;

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
import unid.monoServerMeta.api.OpportunityResponse;
import unid.monoServerMeta.api.PassionMajorRequest;
import unid.monoServerMeta.api.PassionMajorResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Opportunity")
@Slf4j
public class OpportunityController {
    private final OpportunityService opportunityService;

    @GetMapping("/student/opportunity/list")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    @Parameters({
        @Parameter(name = "unidtoken", in = ParameterIn.HEADER, required = true),
    })
    @Operation(
            summary = "List"
    )
    public @Valid UnifiedResponse<List<OpportunityResponse>> list() {
        return UnifiedResponse.of(
                opportunityService.list()
        );
    }
}
