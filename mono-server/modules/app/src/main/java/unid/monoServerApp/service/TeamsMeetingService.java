package unid.monoServerApp.service;

import com.microsoft.graph.http.BaseCollectionPage;
import com.microsoft.graph.models.*;
import com.microsoft.graph.requests.GraphServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.Properties;
import unid.monoServerApp.api.microsoft.MsGraphBuilder;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.queue.model.EducatorMeetingRequestPayload;
import unid.monoServerMeta.Pattern;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TeamsMeetingService {
    private final GraphServiceClient graphServiceClient;
    private final Properties properties;
    private final DbEducatorProfile dbEducatorProfile;
    private final DbStudentProfile dbStudentProfile;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final DbEducatorCalendar dbEducatorCalendar;

    public void createMeetingWithStudent(EducatorMeetingRequestPayload payload) {
        var educator = getEducator(payload);
        var calendar = getCalendar(payload);
        var transaction = getTransaction(calendar);
        var student = getStudent(calendar, transaction);
        var start = OffsetDateTime.of(calendar.getDate(), calendar.getHourStart(), ZoneOffset.UTC);
        var end = OffsetDateTime.of(calendar.getDate(), calendar.getHourEnd(), ZoneOffset.UTC);
        var meeting = createMeeting(educator, student, calendar, start, end);
        var meetingEvent = MsGraphBuilder.build(new Event(), event -> {
            var format = DateTimeFormatter.ofPattern(Pattern.TEAM_BOOKING_DATE);
            event.categories = List.of("Educator Student One To One Session");
            event.transactionId = transaction.getId().toString();
            event.type = EventType.SINGLE_INSTANCE;
            event.sensitivity = Sensitivity.PRIVATE;
            event.importance = Importance.NORMAL;
            event.allowNewTimeProposals = false;
            event.isReminderOn = true;
            event.body = MsGraphBuilder.build(new ItemBody(), body -> {
                body.contentType = BodyType.HTML;
                body.content = meeting.joinWebUrl;
            });
            event.start = MsGraphBuilder.build(new DateTimeTimeZone(), t -> {
                t.dateTime = format.format(start);
                t.timeZone = "UTC";
            });
            event.end = MsGraphBuilder.build(new DateTimeTimeZone(), t -> {
                t.dateTime = format.format(end);
                t.timeZone = "UTC";
            });
            var attendees = List.of(
                    MsGraphBuilder.build(new Attendee(), e -> {
                        e.type = AttendeeType.REQUIRED;
                        e.emailAddress = MsGraphBuilder.build(new EmailAddress(), email -> {
                            email.address = educator.getMicrosoftEmail();
                        });
                    }),
                    MsGraphBuilder.build(new Attendee(), s -> {
                        s.type = AttendeeType.REQUIRED;
                        s.emailAddress = MsGraphBuilder.build(new EmailAddress(), email -> {
                            email.address = student.getUser().getEmail();
                        });
                    })
            );
            event.attendees = attendees;
            event.subject = String.format(
                    "[Student Session] Confirmed Meeting [%s]",
                    calendar.getPaymentTransactionId()
            );
        });

        var event = graphServiceClient
                .users(properties.getMsOrganizerId())
                .calendar()
                .events()
                .buildRequest()
                .post(meetingEvent);

        log.info("Created Event: {}", event);
        log.info("URL: {}", event.onlineMeeting.joinUrl);
        dbEducatorCalendar.getDao().update(
                calendar
                        .setMeetingId(meeting.id)
                        .setMeetingUrl(meeting.joinWebUrl)
        );
    }

    public List<MeetingAttendanceReport> getMeetingAttendance(String meetingId) {
        var event = graphServiceClient
                .users(properties.getMsOrganizerId())
                .onlineMeetings(meetingId)
                .attendanceReports()
                .buildRequest()
                .get();
        return Optional.ofNullable(event).map(BaseCollectionPage::getCurrentPage).orElse(new ArrayList<>());
    }

    private EducatorCalendarPojo getCalendar(EducatorMeetingRequestPayload payload) {
        return Optional.ofNullable(dbEducatorCalendar.getDao().fetchOneById(payload.getCalendarId()))
                .orElseThrow(() -> Exceptions.notFound("Calendar Not Found"));
    }

    private StudentPaymentTransactionPojo getTransaction(EducatorCalendarPojo calendar) {
        return Optional.ofNullable(dbStudentPaymentTransaction.getDao().fetchOneById(calendar.getPaymentTransactionId()))
                .orElseThrow(() -> Exceptions.notFound("Transaction Not Found"));
    }

    private DbEducatorProfile.Result getEducator(EducatorMeetingRequestPayload payload) {
        var table = dbEducatorProfile.getTable();
        return dbEducatorProfile.getQuery(table)
                .where(table.ID.eq(payload.getEducatorProfileId()))
                .fetchOptionalInto(DbEducatorProfile.Result.class)
                .orElseThrow(() -> Exceptions.notFound("Educator Not Found"));
    }

    private DbStudentProfile.Result getStudent(EducatorCalendarPojo calendar, StudentPaymentTransactionPojo transaction) {
        var table = dbStudentProfile.getTable();
        return dbStudentProfile.getQuery(table)
                .where(table.ID.eq(transaction.getStudentProfileId()))
                .fetchOptionalInto(DbStudentProfile.Result.class)
                .orElseThrow(() -> Exceptions.notFound("Student Not Found"));
    }

    @SneakyThrows
    public OnlineMeeting createMeeting(
            DbEducatorProfile.Result educatorResult,
            DbStudentProfile.Result studentResult,
            EducatorCalendarPojo educatorCalendar,
            OffsetDateTime start,
            OffsetDateTime end
    ) {
        var educatorName = String.format(
                "%s %s",
                educatorResult.getUser().getFirstNameI18n().getEnglish(),
                educatorResult.getUser().getLastNameI18n().getEnglish()
        );
        var educator = MsGraphBuilder.build(new MeetingParticipantInfo(), b -> {
                    b.role = OnlineMeetingRole.COORGANIZER;
                    b.upn = educatorName;
                    b.identity = MsGraphBuilder.build(
                            new IdentitySet(),
                            s -> {
                                s.user = MsGraphBuilder.build(new Identity(), u -> {
                                    u.displayName = educatorName;
                                    u.id = educatorResult.getMicrosoftId();
                                });
                            }
                    );
                }
        );
        var studentName = String.format(
                "%s %s",
                studentResult.getUser().getFirstNameI18n().getEnglish(),
                studentResult.getUser().getLastNameI18n().getEnglish()
        );
        var student = MsGraphBuilder.build(new MeetingParticipantInfo(), b -> {
            b.role = OnlineMeetingRole.ATTENDEE;
            b.upn = studentName;
        });
        var onlineMeeting = MsGraphBuilder.build(new OnlineMeeting(), meeting -> {
                    meeting.recordAutomatically = true;
                    meeting.allowAttendeeToEnableCamera = true;
                    meeting.allowAttendeeToEnableMic = true;
                    meeting.allowTeamworkReactions = false;
                    meeting.allowParticipantsToChangeName = false;
                    meeting.allowMeetingChat = MeetingChatMode.ENABLED;
                    meeting.allowedPresenters = OnlineMeetingPresenters.ORGANIZER;
                    meeting.isEntryExitAnnounced = true;

                    meeting.startDateTime = start;
                    meeting.endDateTime = end;

                    meeting.participants = MsGraphBuilder.build(new MeetingParticipants(), participants -> {
                        participants.attendees = List.of(educator, student);
                    });

                    meeting.subject = String.format("Meeting: %s & %s", educator.upn, student.upn);
                    meeting.lobbyBypassSettings = MsGraphBuilder.build(new LobbyBypassSettings(), bypass -> {
                        bypass.isDialInBypassEnabled = true;
                        bypass.scope = LobbyBypassScope.ORGANIZER;
                    });
                    meeting.joinMeetingIdSettings = MsGraphBuilder.build(new JoinMeetingIdSettings(), join -> {
                        join.isPasscodeRequired = true;
                    });
                }
        );
        var meeting = graphServiceClient
                .users(properties.getMsOrganizerId())
                .onlineMeetings()
                .buildRequest()
                .post(onlineMeeting);

        log.info("Meeting: {}", meeting);
        return meeting;
    }
}
