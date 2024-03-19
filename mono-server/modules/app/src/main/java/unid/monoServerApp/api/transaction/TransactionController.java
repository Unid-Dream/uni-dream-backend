package unid.monoServerApp.api.transaction;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import io.swagger.v3.oas.annotations.Operation;
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
import unid.monoServerMeta.api.PaymentTransactionRequest;
import unid.monoServerMeta.api.PaymentTransactionResponse;
import unid.monoServerMeta.api.TransactionResponse;

import javax.validation.Valid;
import java.util.Map;
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



    @PostMapping("student/{profileId}/transaction/payment")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT,
            matchingSessionProfileId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Student Payment Confirm"
    )
    public @Valid UnifiedResponse<PaymentTransactionResponse> payment(
            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
            @RequestBody PaymentTransactionRequest request
    ) {
        return UnifiedResponse.of(
                transactionService.payment(profileId,request)
        );
    }


    @PostMapping("/student/transaction/payment/asiapay/callback")
    @ACL(
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Asia Pay Notify Payment Result"
    )
    public @Valid UnifiedResponse<Void> asiaPayNotify(
            @RequestBody Map<String,Object> paymentInfo
    ) {
        StaticLog.info(" AsiaPay Notify Param {}", JSONUtil.toJsonPrettyStr(paymentInfo));
        return UnifiedResponse.of(
                null
        );
    }



//    @GetMapping("student/session/history/{profileId}")
//    @ACL(
//            authed = true,
//            allowedRoles = UserRoleEnum.STUDENT,
//            matchingSessionProfileId = true
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Get One"
//    )
//
//    public @Valid UnifiedResponse<TransactionResponse> list(
//            @PathVariable("profileId") UUID profileId
//    ) {
//        transactionService.list(profileId);
//        return UnifiedResponse.of(
//                null
//        );
//    }
}
