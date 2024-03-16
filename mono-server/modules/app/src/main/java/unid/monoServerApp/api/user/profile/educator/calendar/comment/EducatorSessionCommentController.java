package unid.monoServerApp.api.user.profile.educator.calendar.comment;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteMap;
import unid.monoServerApp.mapper.EducatorCalendarMapper;
import unid.monoServerApp.mapper.EducatorSessionNoteMapper;
import unid.monoServerApp.service.EmailService;
import unid.monoServerMeta.api.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Educator Session Comment")
@Slf4j
public class EducatorSessionCommentController {
    private final EducatorSessionCommentService educatorSessionCommentService;
    private final EducatorCalendarMapper educatorCalendarMapper;
    private final DbEducatorCalendar dbEducatorCalendar;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final DbEducatorSessionNoteMap dbEducatorSessionNoteMap;
    private final EducatorSessionNoteMapper educatorSessionNoteMapper;

    @GetMapping("educator/user/profile/educator/calendar/comment")
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT, UserRoleEnum.EDUCATOR}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "List Comments"
    )
    @SneakyThrows
    public @Valid UnifiedResponse<List<EducatorSessionNoteCommentResponse>> listComments(
            @RequestParam UUID calendarId
    ) {
        var list = educatorSessionCommentService.getComments(calendarId);
        return UnifiedResponse.of(list);
    }

    @PutMapping("educator/user/profile/educator/{profileId}/calendar/comment/{calendarId}")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.EDUCATOR},
            matchingSessionProfileId = true,
            educatorProfileApproved = true
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Make Comments"
    )
    public @Valid UnifiedResponse<List<EducatorSessionNoteCommentResponse>> markAvailable(
            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
            @PathVariable("calendarId") UUID calendarId,
            @RequestBody @Valid
            EducatorSessionCommentRequest request
    ) {
        educatorSessionCommentService.makeComments(calendarId,profileId, request);
        return UnifiedResponse.of(educatorSessionCommentService.getComments(calendarId));
    }
}
