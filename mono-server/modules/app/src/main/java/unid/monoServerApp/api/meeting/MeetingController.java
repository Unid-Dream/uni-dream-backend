package unid.monoServerApp.api.meeting;//package unid.monoServerApp.api.country;

import io.swagger.v3.oas.annotations.Operation;
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
import unid.monoServerApp.service.EmailService;
import unid.monoServerMeta.api.MeetingReservationPaymentRequest;
import unid.monoServerMeta.api.MeetingReservationPaymentResponse;
import unid.monoServerMeta.api.MeetingReservationRequest;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/meeting/{studentProfileId}/{educatorProfileId}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Meeting")
@Slf4j
public class MeetingController {
    private final MeetingService meetingService;
    private final EmailService emailService;

    @PostMapping("reserve")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.STUDENT},
            matchingSessionProfileId = true
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Reserve Educator Available Calendar"
    )
    public @Valid UnifiedResponse<Void> reserve(
            @PathVariable("studentProfileId") @ACL.ProfileId UUID studentProfileId,
            @PathVariable("educatorProfileId") UUID educatorProfileId,
            @RequestBody @Valid
            MeetingReservationRequest payload) {
        var result = meetingService.reserve(studentProfileId, educatorProfileId, payload);
        emailService.requestStudentBookingNotification(result);
        return UnifiedResponse.of(null);
    }

    @PutMapping("reserve/cancel")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.STUDENT},
            matchingSessionProfileId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Cancel Reserve Educator Calendar"
    )
    public @Valid UnifiedResponse<Void> cancelReserve(
            @PathVariable("studentProfileId") @ACL.ProfileId UUID studentProfileId,
            @PathVariable("educatorProfileId") UUID educatorProfileId,
            @RequestBody @Valid
            MeetingReservationRequest payload) {
        meetingService.cancelReserve(studentProfileId, educatorProfileId, payload);
        return UnifiedResponse.of(null);
    }

    @PutMapping("reserve/payment")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.STUDENT},
            matchingSessionProfileId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Paid For Accepted Reservation"
    )
    public @Valid UnifiedResponse<MeetingReservationPaymentResponse> payReserved(
            @PathVariable("studentProfileId") @ACL.ProfileId UUID studentProfileId,
            @PathVariable("educatorProfileId") UUID educatorProfileId,
            @RequestBody @Valid
            MeetingReservationPaymentRequest payload) {
        var result = meetingService.payReserved(studentProfileId, educatorProfileId, payload);
        return UnifiedResponse.of(
                new MeetingReservationPaymentResponse()
                        .setPaymentUrl(result)
        );
    }
}
