package unid.monoServerApp.api.user.profile.educator.calendar;//package unid.monoServerApp.api.country;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.EventLoggable;
import unid.monoServerMeta.api.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Educator Calendar")
@Slf4j
public class EducatorCalendarController {
    private final EducatorCalendarService educatorCalendarService;


    @GetMapping(value = {
            "educator/user/profile/educator/{educatorProfileId}/calendar/available",
            "student/user/profile/educator/{educatorProfileId}/calendar/available"
    })
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR,UserRoleEnum.STUDENT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get Educator All Available Calendar"
    )
    public @Valid UnifiedResponse<EducatorCalendarSimpleResponse> getAllAvailableFromNow(
            @PathVariable("educatorProfileId") UUID profileId,
            @ParameterObject @Validated EducatorCalendarRequest.TimeSlotPayload request
    ) {
        var result = educatorCalendarService.getAvailableTimeSlots(
                profileId,request
        );
        return UnifiedResponse.of(result);
    }


    @GetMapping("educator/user/profile/educator/{educatorProfileId}/calendar")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR},
            matchingSessionProfileId = true,
            educatorProfileApproved = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Booking Calendar"
    )
    public @Valid UnifiedResponse<EducatorCalendarSimpleResponse> getAcceptTimeSlot(
            @PathVariable("educatorProfileId") @ACL.ProfileId UUID educatorProfileId,
            @ParameterObject @Valid EducatorCalendarRequest.TimeSlotPayload payload) {
        return UnifiedResponse.of(educatorCalendarService.getAcceptTimeSlot(educatorProfileId,payload));
    }


    @GetMapping("admin/calendar/session/page")
    @Transactional
    @ACL(
//            authed = true,
//            allowedRoles = {UserRoleEnum.ADMIN,UserRoleEnum.ROOT}
            noAuthed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query  Calendar Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<StudentSessionTransactionPayload>> getPage(
            @ParameterObject @Valid CalendarSessionPageRequest request) {
        return UnifiedResponse.of(educatorCalendarService.getPage(request));
    }


    @PutMapping("educator/user/profile/educator/{profileId}/calendar/available")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR},
            matchingSessionProfileId = true,
            educatorProfileApproved = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Mark Educator Available Calendar"
    )
    public @Valid UnifiedResponse<EducatorAvailableScheduleResponse> markAvailable(
            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
            @RequestBody @Valid EducatorCalendarRequest payload) {
        List<EducatorCalendarTimeSlotResponse> slots = Lists.newArrayList();
        payload.getSlots().forEach(slot -> slots.add(educatorCalendarService.markAvailable(profileId, slot)));
        //查询所有的时间槽
        EducatorAvailableScheduleResponse response = new EducatorAvailableScheduleResponse();
        response.setSlots(slots);
        return UnifiedResponse.of(response);
    }

    @PutMapping("educator/user/profile/educator/{profileId}/calendar/{educatorCalendarId}/unavailable")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR},
            matchingSessionProfileId = true,
            educatorProfileApproved = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Unmark Educator Available Calendar"
    )
    public @Valid UnifiedResponse<Void> unmarkAvailable(
            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
            @PathVariable("educatorCalendarId") UUID educatorCalendarId) {
        educatorCalendarService.unmarkAvailable(profileId, educatorCalendarId);
        return UnifiedResponse.of(null);
    }

    @PutMapping("educator/user/profile/educator/{educatorProfileId}/calendar/reserve/accept")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR},
            matchingSessionProfileId = true,
            educatorProfileApproved = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Educator Accept Calendar Reservation"
    )
    public @Valid UnifiedResponse<Void> accept(
            @PathVariable("educatorProfileId") @ACL.ProfileId UUID educatorProfileId,
            @RequestBody @Valid EducatorCalendarRejectRequest request) {
        educatorCalendarService.acceptOrDenyBooking(educatorProfileId,request,true);
        return UnifiedResponse.of(null);
    }


    @PutMapping("educator/user/profile/educator/{educatorProfileId}/calendar/{calendarId}/reserve/attend")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR},
            matchingSessionProfileId = true,
            educatorProfileApproved = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Educator Commit Student Attend Calendar"
    )
    public @Valid UnifiedResponse<Void> commitStudentAttend(
            @PathVariable("educatorProfileId") @ACL.ProfileId UUID educatorProfileId,
            @PathVariable("calendarId") @ACL.ProfileId UUID calendarId) {
        educatorCalendarService.commitStudentAttend(educatorProfileId,calendarId);
        return UnifiedResponse.of(null);
    }


    @PutMapping(value = {"educator/user/profile/{profileId}/calendar/reserve/reschedule","student/user/profile/{profileId}/calendar/reserve/reschedule"})
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR,UserRoleEnum.STUDENT},
            matchingSessionProfileId = true,
            educatorProfileApproved = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Educator Reschedule Calendar Reservation"
    )
    public @Valid UnifiedResponse<Void> reschedule(
            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
            @RequestBody @Valid SessionRescheduleRequest request) {
        educatorCalendarService.reschedule(profileId,request);
        return UnifiedResponse.of(null);
    }

    @PutMapping("educator/user/profile/educator/{profileId}/calendar/reserve/deny")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR},
            matchingSessionProfileId = true,
            educatorProfileApproved = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Educator Deny Calendar Reservation"
    )
    public @Valid UnifiedResponse<Void> deny(
            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
            @RequestBody @Valid
            EducatorCalendarRejectRequest request) {
//        dbEducatorCalendar.validateMarking(payload.getDate(), payload.getHourStart(), payload.getHourEnd());
        educatorCalendarService.acceptOrDenyBooking(profileId, request, false);
        return UnifiedResponse.of(null);
    }


    @GetMapping(value = {
            "educator/user/profile/educator/{profileId}/calendar/{calendarId}/session/status" ,
            "student/user/profile/educator/{profileId}/calendar/{calendarId}/session/status"})
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR,UserRoleEnum.STUDENT},
            matchingSessionProfileId = true,
            educatorProfileApproved = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Educator Query Consultation Session Status"
    )
    public @Valid UnifiedResponse<ConsultationSessionResponse> getConsultationSessionStatus(
            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
            @PathVariable("calendarId") UUID calendarId) {
        ConsultationSessionResponse response = educatorCalendarService.getConsultationSession(profileId, calendarId);
        return UnifiedResponse.of(response);
    }
}
