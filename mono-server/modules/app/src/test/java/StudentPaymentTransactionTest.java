import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import unid.monoServerApp.SpringApp;
import unid.monoServerApp.api.user.profile.educator.EducatorProfileService;
import unid.monoServerApp.api.user.profile.student.schedule.StudentScheduleService;

import java.time.OffsetDateTime;
import java.util.UUID;

@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class StudentPaymentTransactionTest {
    @Autowired
    @InjectMocks
    private StudentScheduleService studentScheduleService;


    @Test
    public void testQuery(){
        studentScheduleService.page(
                UUID.fromString("94a4e43b-b4c3-4b93-ae8a-27fdd69012cc"),
                OffsetDateTime.parse("2022-03-10T10:00:00Z"),
                OffsetDateTime.parse("2025-03-10T10:00:00Z"),
                1,
                10
        );
    }
}
