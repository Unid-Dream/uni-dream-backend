package unid.monoServerApp.api.user.profile.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarService;
import unid.monoServerMeta.api.CalendarSessionPageRequest;
import unid.monoServerMeta.api.StudentSessionTransactionPayload;
import unid.monoServerMeta.api.UniPageResponse;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Common Operation")
@Slf4j
public class CommonOperationController {
    private final CommonOperationService commonOperationService;

    @GetMapping("admin/consultationSession/page")
    @ACL(
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Consultation Session Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<StudentSessionTransactionPayload>> getAllSessionPage(
            @ParameterObject @Valid CalendarSessionPageRequest request
    ) {
        return UnifiedResponse.of(commonOperationService.getSessionPage(request,null));
    }


    @GetMapping("admin/consultationSession/{id}")
    @ACL(
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "GEt Consultation Session Detail"
    )
    public @Valid UnifiedResponse<StudentSessionTransactionPayload> getSessionDetail(
            @PathVariable("id") UUID id
    ) {
        return UnifiedResponse.of(commonOperationService.getSessionDetail(id));
    }

    @GetMapping("admin/consultationSession/pending/page")
    @ACL(
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Pending Consultation Session Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<StudentSessionTransactionPayload>> getPendingSessionPage(
            @ParameterObject @Valid CalendarSessionPageRequest request
    ) {
        return UnifiedResponse.of(commonOperationService.getSessionPage(request, BookingStatusEnum.PENDING));
    }


    @PutMapping("admin/{userId}/consultationSession/{sessionId}/cancel")
    @ACL(
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Cancel Consultation Session"
    )
    public @Valid UnifiedResponse<Void> cancel(
            @PathVariable("userId") UUID userId,
            @PathVariable("sessionId") UUID sessionId
    ) {
        commonOperationService.cancel(userId,sessionId);
        return UnifiedResponse.of(null);
    }
}
