package unid.monoServerApp.api.user.profile.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarService;
import unid.monoServerApp.api.user.profile.educator.calendar.comment.EducatorSessionCommentService;
import unid.monoServerMeta.api.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Common Operation")
@Slf4j
public class CalendarOperationController {
    private final CalendarOperationService calendarOperationService;


    @GetMapping("admin/{userId}/consultation-session/page")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN,
            matchingSessionUserId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Consultation Session Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<StudentSessionTransactionPayload>> getAllSessionPage(
            @PathVariable("userId")  @ACL.UserId UUID userId,
            @ParameterObject @Valid CalendarSessionPageRequest request
    ) {
        return UnifiedResponse.of(calendarOperationService.getSessionPage(request,null));
    }


    @GetMapping("admin/{userId}/consultation-session/{id}")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN,
            matchingSessionUserId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get Consultation Session Detail"
    )
    public @Valid UnifiedResponse<StudentSessionTransactionPayload> getSessionDetail(
            @PathVariable("userId")  @ACL.UserId UUID userId,
            @PathVariable("id") UUID transactionId
    ) {
        return UnifiedResponse.of(calendarOperationService.getSessionDetail(transactionId));
    }

    @GetMapping("admin/{userId}/consultation-session/pending/page")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN,
            matchingSessionUserId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Pending Consultation Session Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<StudentSessionTransactionPayload>> getPendingSessionPage(
            @PathVariable("userId")  @ACL.UserId UUID userId,
            @ParameterObject @Valid CalendarSessionPageRequest request
    ) {
        return UnifiedResponse.of(calendarOperationService.getSessionPage(request, BookingStatusEnum.PENDING));
    }


    @PutMapping("admin/{userId}/consultation-session/{id}/cancel")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN,
            matchingSessionUserId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Cancel Consultation Session"
    )
    public @Valid UnifiedResponse<Void> cancel(
            @PathVariable("userId")  @ACL.UserId UUID userId,
            @PathVariable("id") UUID transactionId
    ) {
        calendarOperationService.cancel(transactionId);
        return UnifiedResponse.of(null);
    }

    @GetMapping("admin/{userId}/consultation-session/{id}/eventLog")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN,
            matchingSessionUserId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Calendar Page"
    )
    public @Valid UnifiedResponse<SessionEventLogResponse> getSessionEventLogs(
            @PathVariable("userId")  @ACL.UserId UUID userId,
            @PathVariable("id") UUID transactionId) {
        return UnifiedResponse.of(calendarOperationService.getSessionEventLogs(transactionId));
    }


    @GetMapping("admin/{userId}/consultation-session/{id}/comments")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN,
            matchingSessionUserId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "List Comments"
    )
    @SneakyThrows
    public @Valid UnifiedResponse<List<EducatorSessionNoteCommentResponse>> listComments(
            @PathVariable("userId")  @ACL.UserId UUID userId,
            @PathVariable("id") UUID transactionId
    ) {
        var list = calendarOperationService.getSessionComments(transactionId);
        return UnifiedResponse.of(list);
    }


    @GetMapping("admin/{userId}/promotion-event/page")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN,
            matchingSessionUserId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Promotion Event Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<CourseEventPayload>> getPromotionEventPage(
            @PathVariable("userId")  @ACL.UserId UUID userId,
            @ParameterObject CourseEventPageRequest request
    ) {
        return UnifiedResponse.of(
                calendarOperationService.page(request)
        );
    }

    @GetMapping("admin/{userId}/promotion-event/{id}")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN,
            matchingSessionUserId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get Promotion Event Detail"
    )
    public @Valid UnifiedResponse<CourseEventPayload> getPromotionEventDetail(
            @PathVariable("userId")  @ACL.UserId UUID userId,
            @PathVariable("id") UUID eventId
    ) {
        return UnifiedResponse.of(
                calendarOperationService.get(eventId)
        );
    }


    @PostMapping("admin/{userId}/promotion-event")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN,
            matchingSessionUserId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Create Promotion Event"
    )
    public @Valid UnifiedResponse<CourseEventPayload> createPromotionEvent(
            @PathVariable("userId") @ACL.UserId UUID userId,
            @RequestBody CourseEventPayload payload
    ) {
        return UnifiedResponse.of(
                calendarOperationService.get(
                        calendarOperationService.create(payload).getId()
                )
        );
    }

    @PutMapping("admin/{userId}/promotion-event")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN,
            matchingSessionUserId = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update Promotion Event"
    )
    public @Valid UnifiedResponse<CourseEventPayload> update(
            @PathVariable("userId") @ACL.UserId UUID userId,
            @RequestBody CourseEventPayload payload
    ) {
        return UnifiedResponse.of(
                calendarOperationService.get(
                        calendarOperationService.update(payload).getId()
                )
        );
    }

}