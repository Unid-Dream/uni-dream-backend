package unid.monoServerApp.api.transaction;

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
import unid.monoServerMeta.api.TransactionResponse;
import unid.monoServerMeta.api.UserResponse;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Transaction")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("student/transaction/{id}")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    
    public @Valid UnifiedResponse<TransactionResponse> get(
            @PathVariable("id") UUID id
    ) {
        var result = transactionService.get(id);
        return UnifiedResponse.of(
                result
        );
    }
}
