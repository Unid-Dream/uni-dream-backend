import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import unid.monoServerApp.SpringApp;
import unid.monoServerApp.api.user.profile.educator.EducatorProfileService;
import unid.monoServerApp.api.user.profile.student.schedule.StudentScheduleService;
import unid.monoServerApp.queue.model.EducatorMeetingRequestPayload;
import unid.monoServerApp.service.TeamsMeetingService;

import java.time.OffsetDateTime;
import java.util.UUID;

@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class StudentPaymentTransactionTest {
    @Autowired
    @InjectMocks
    private StudentScheduleService studentScheduleService;
    @Autowired
    @InjectMocks
    private TeamsMeetingService teamsMeetingService;


    @Test
    public void testCreateMeeting(){
        EducatorMeetingRequestPayload payload = new EducatorMeetingRequestPayload();
        payload.setCalendarId(UUID.fromString("7e841a93-d125-4147-aa14-53348f2655cd"));
        payload.setEducatorProfileId(UUID.fromString("b927721e-d2bf-4bf2-ac59-ab1d9aa5cbb2"));

        teamsMeetingService.createMeetingWithStudent(payload);
    }


    @Test
    public void testQuery(){
//        studentScheduleService.page(
//                UUID.fromString("94a4e43b-b4c3-4b93-ae8a-27fdd69012cc"),
//                OffsetDateTime.parse("2022-03-10T10:00:00Z"),
//                OffsetDateTime.parse("2025-03-10T10:00:00Z"),
//                1,
//                10
//        );
    }
}
