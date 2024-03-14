package unid.monoServerApp.api.user.profile.educator.calendar;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.jooq.SortOrder;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.coreRsqlJooq.jooq.PaginatedQuery;
import pwh.coreRsqlJooq.jooq.PaginatedQuerySorting;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreRsqlJooq.model.PaginationResponse;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Constant;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.EducatorCalendarMapper;
import unid.monoServerApp.service.EmailService;
import unid.monoServerMeta.Pattern;
import unid.monoServerMeta.api.EducatorAvailableScheduleResponse;
import unid.monoServerMeta.api.EducatorCalendarRequest;
import unid.monoServerMeta.api.EducatorCalendarRequestPayload;
import unid.monoServerMeta.api.EducatorCalendarResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Educator Calendar")
@Slf4j
public class EducatorCalendarController {
    private final EducatorCalendarService educatorCalendarService;
    private final EducatorCalendarMapper educatorCalendarMapper;
    private final DbEducatorCalendar dbEducatorCalendar;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;


    @GetMapping(value = {
            "educator/user/profile/educator/{profileId}/calendar/available",
            "student/user/profile/educator/{profileId}/calendar/available"
    })
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR,UserRoleEnum.STUDENT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get Educator All Available Calendar"
    )
    public @Valid UnifiedResponse<EducatorAvailableScheduleResponse> getAllAvailableFromNow(
            @PathVariable("profileId") UUID profileId,
            @RequestParam(required = false) String startDateTimeUtc,
            @RequestParam(required = false) String endDateTimeUtc
    ) {
        var result = educatorCalendarService.getAllAvailableFromNow(
                profileId,
                OffsetDateTime.parse(startDateTimeUtc),
                OffsetDateTime.parse(endDateTimeUtc)
        );
        return UnifiedResponse.of(result);
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
    public @Valid UnifiedResponse<Void> markAvailable(
            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
            @RequestBody @Valid EducatorCalendarRequest payload) {
        payload.getSlots().forEach(slot-> educatorCalendarService.markAvailable(profileId, slot));
        return UnifiedResponse.of(null);
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

    @PutMapping("educator/user/profile/educator/{profileId}/calendar/reserve/accept")
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
            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
            @RequestBody @Valid
            EducatorCalendarRequest payload) {
//        dbEducatorCalendar.validateMarking(payload.getDate(), payload.getHourStart(), payload.getHourEnd());
//        var result = educatorCalendarService.acceptOrDenyBooking(profileId, payload, true);
//        emailService.requestStudentBookingAccepted(result);
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
            EducatorCalendarRequest payload) {
//        dbEducatorCalendar.validateMarking(payload.getDate(), payload.getHourStart(), payload.getHourEnd());
//        educatorCalendarService.acceptOrDenyBooking(profileId, payload, false);
        return UnifiedResponse.of(null);
    }
}
