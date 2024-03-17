package unid.monoServerApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.Properties;
import unid.monoServerApp.cache.auth.register.NewRegisterUser;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.queue.MessageProducer;
import unid.monoServerApp.queue.model.EmailRequestPayload;
import unid.monoServerMeta.Pattern;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EmailService {
    private final FreeMarkerConfigurer freeMarkerConfigurer;
    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;
    private final Properties properties;
    private final MessageProducer messageProducer;
    private final DbStudentProfile dbStudentProfile;
    private final DbEducatorProfile dbEducatorProfile;
    private final ObjectMapper objectMapper;

    private String getTemplatePath(String templateFileName) {
        return String.format("%s.ftl", templateFileName);
    }

    @SneakyThrows
    public void sendEmail(String subject, String content, String... recipients) {
        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.toString());
        helper.setFrom(mailProperties.getUsername(), properties.getEmailPersonal());
        helper.setTo(recipients);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
        log.info("Sent E-mail: {} | {}", subject, Arrays.asList(recipients));
    }

    @SneakyThrows
    @Async
    public void requestNewUserRegistrationOtp(
            NewRegisterUser payload
    ) {
        var template = freeMarkerConfigurer
                .getConfiguration()
                .getTemplate(getTemplatePath("NewUserRegistrationOtp_en"));
        var html = FreeMarkerTemplateUtils.processTemplateIntoString(
                template,
                Map.of(
                        "otp", payload.getOtp()
                )
        );
        sendEmail(
                "Verify Your E-mail",
                html,
                Collections.singletonList(payload.getEmail()).toArray(new String[]{})
        );
//        messageProducer.sendEmailRequest(EmailRequestPayload.builder()
//                .category(EmailRequestPayload.Category.VERIFY_EMAIL)
//                .subject("Verify Your E-mail")
//                .content(html)
//                .recipients(Arrays.asList(payload.getEmail()))
//                .build());
    }

//    @SneakyThrows
//    @Async
//    public void requestNewUserWelcome(
//            String recipientFirstName,
//            String recipientLastName,
//            String... recipients
//    ) {
//        var template = freeMarkerConfigurer
//                .getConfiguration()
//                .getTemplate(getTemplatePath("NewUserWelcome_en"));
//        var html = FreeMarkerTemplateUtils.processTemplateIntoString(
//                template,
//                Map.of(
//                        "recipientFirstName", recipientFirstName,
//                        "recipientLastName", recipientLastName
//                )
//        );
//        messageProducer.sendEmailRequest(EmailRequestPayload.builder()
//                .subject("Welcome To UNI-D")
//                .content(html)
//                .recipients(Arrays.asList(recipients))
//                .build());
//    }

    @SneakyThrows
    @Async
    public void requestStudentBookingNotification(
            DbEducatorCalendar.Result payload
    ) {
        var sTable = dbStudentProfile.getTable();
        var student = dbStudentProfile.getQuery(sTable)
                .where(sTable.ID.eq(payload.getPaymentTransaction().getStudentProfileId()))
                .fetchOptionalInto(DbStudentProfile.Result.class)
                .orElseThrow();
        var eTable = dbEducatorProfile.getTable();
        var educator = dbEducatorProfile.getQuery(eTable)
                .where(eTable.ID.eq(payload.getEducatorProfileId()))
                .fetchOptionalInto(DbEducatorProfile.Result.class)
                .orElseThrow();
        var template = freeMarkerConfigurer
                .getConfiguration()
                .getTemplate(getTemplatePath("StudentBookingNotification_en"));
        var html = FreeMarkerTemplateUtils.processTemplateIntoString(
                template,
                Map.of(
                        "recipientFirstName", educator.getUser().getFirstNameI18n().getEnglish(),
                        "studentFirstName", student.getUser().getFirstNameI18n().getEnglish(),
                        "studentLastName", student.getUser().getLastNameI18n().getEnglish(),
                        "date", payload.getDate().format(DateTimeFormatter.ofPattern(Pattern.DATE)),
                        "start", payload.getHourStart().format(DateTimeFormatter.ofPattern(Pattern.TIME_CALENDAR)),
                        "end", payload.getHourEnd().format(DateTimeFormatter.ofPattern(Pattern.TIME_CALENDAR))
                )
        );
        messageProducer.sendEmailRequest(EmailRequestPayload.builder()
                .category(EmailRequestPayload.Category.SCHEDULE_CONFIRMATION)
                .subject("Schedule Reservation")
                .content(html)
                .recipients(Arrays.asList(educator.getMicrosoftEmail()))
                .build());
    }

    @SneakyThrows
    @Async
    public void requestStudentBookingAccepted(
            DbEducatorCalendar.Result payload
    ) {
        var sTable = dbStudentProfile.getTable();
        var student = dbStudentProfile.getQuery(sTable)
                .where(sTable.ID.eq(payload.getPaymentTransaction().getStudentProfileId()))
                .fetchOptionalInto(DbStudentProfile.Result.class)
                .orElseThrow();
        var eTable = dbEducatorProfile.getTable();
        var educator = dbEducatorProfile.getQuery(eTable)
                .where(eTable.ID.eq(payload.getEducatorProfileId()))
                .fetchOptionalInto(DbEducatorProfile.Result.class)
                .orElseThrow();
        var template = freeMarkerConfigurer
                .getConfiguration()
                .getTemplate(getTemplatePath("StudentBookingAccepted_en"));
        var html = FreeMarkerTemplateUtils.processTemplateIntoString(
                template,
                Map.of(
                        "recipientFirstName", student.getUser().getFirstNameI18n().getEnglish(),
                        "educatorFirstName", educator.getUser().getFirstNameI18n().getEnglish(),
                        "educatorLastName", educator.getUser().getLastNameI18n().getEnglish(),
                        "date", payload.getDate().format(DateTimeFormatter.ofPattern(Pattern.DATE)),
                        "start", payload.getHourStart().format(DateTimeFormatter.ofPattern(Pattern.TIME_CALENDAR)),
                        "end", payload.getHourEnd().format(DateTimeFormatter.ofPattern(Pattern.TIME_CALENDAR))
                )
        );
        messageProducer.sendEmailRequest(EmailRequestPayload.builder()
                .category(EmailRequestPayload.Category.SCHEDULE_CONFIRMATION)
                .subject("Schedule Confirmed")
                .content(html)
                .recipients(Arrays.asList(student.getUser().getEmail()))
                .build());
    }

    @SneakyThrows
    @Async
    public void requestEducatorSessionNote(
            EducatorCalendarPojo calendar,
            StudentPaymentTransactionPojo transaction
    ) {
        var sTable = dbStudentProfile.getTable();
        var student = dbStudentProfile.getQuery(sTable)
                .where(sTable.ID.eq(transaction.getStudentProfileId()))
                .fetchOptionalInto(DbStudentProfile.Result.class)
                .orElseThrow();
        var eTable = dbEducatorProfile.getTable();
        var educator = dbEducatorProfile.getQuery(eTable)
                .where(eTable.ID.eq(calendar.getEducatorProfileId()))
                .fetchOptionalInto(DbEducatorProfile.Result.class)
                .orElseThrow();
        var template = freeMarkerConfigurer
                .getConfiguration()
                .getTemplate(getTemplatePath("EducatorSessionNoteNotification_en"));
        var html = FreeMarkerTemplateUtils.processTemplateIntoString(
                template,
                Map.of(
                        "recipientFirstName", educator.getUser().getFirstNameI18n().getEnglish(),
                        "studentFirstName", student.getUser().getFirstNameI18n().getEnglish(),
                        "studentLastName", student.getUser().getLastNameI18n().getEnglish(),
                        "date", calendar.getDate().format(DateTimeFormatter.ofPattern(Pattern.DATE)),
                        "start", calendar.getHourStart().format(DateTimeFormatter.ofPattern(Pattern.TIME_CALENDAR)),
                        "end", calendar.getHourEnd().format(DateTimeFormatter.ofPattern(Pattern.TIME_CALENDAR))
                )
        );
        messageProducer.sendEmailRequest(EmailRequestPayload.builder()
                .category(EmailRequestPayload.Category.SCHEDULE_CONFIRMATION)
                .subject("Student Session Comments")
                .content(html)
                .recipients(Arrays.asList(educator.getMicrosoftEmail()))
                .build());
    }

    @SneakyThrows
    @Async
    public void requestStudentSessionNote(
            EducatorCalendarPojo calendar,
            StudentPaymentTransactionPojo transaction
    ) {
        var sTable = dbStudentProfile.getTable();
        var student = dbStudentProfile.getQuery(sTable)
                .where(sTable.ID.eq(transaction.getStudentProfileId()))
                .fetchOptionalInto(DbStudentProfile.Result.class)
                .orElseThrow();
        var eTable = dbEducatorProfile.getTable();
        var educator = dbEducatorProfile.getQuery(eTable)
                .where(eTable.ID.eq(calendar.getEducatorProfileId()))
                .fetchOptionalInto(DbEducatorProfile.Result.class)
                .orElseThrow();
        var template = freeMarkerConfigurer
                .getConfiguration()
                .getTemplate(getTemplatePath("StudentSessionNoteNotification_en"));
        var html = FreeMarkerTemplateUtils.processTemplateIntoString(
                template,
                Map.of(
                        "recipientFirstName", student.getUser().getFirstNameI18n().getEnglish(),
                        "educatorFirstName", educator.getUser().getFirstNameI18n().getEnglish(),
                        "educatorLastName", educator.getUser().getLastNameI18n().getEnglish(),
                        "date", calendar.getDate().format(DateTimeFormatter.ofPattern(Pattern.DATE)),
                        "start", calendar.getHourStart().format(DateTimeFormatter.ofPattern(Pattern.TIME_CALENDAR)),
                        "end", calendar.getHourEnd().format(DateTimeFormatter.ofPattern(Pattern.TIME_CALENDAR))
                )
        );
        messageProducer.sendEmailRequest(EmailRequestPayload.builder()
                .category(EmailRequestPayload.Category.SCHEDULE_CONFIRMATION)
                .subject("Educator Session Comments")
                .content(html)
                .recipients(Arrays.asList(student.getUser().getEmail()))
                .build());
    }
}
