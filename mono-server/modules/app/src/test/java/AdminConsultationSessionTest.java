import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.monoServerApp.SpringApp;
import unid.monoServerApp.api.user.profile.admin.CommonOperationService;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarService;
import unid.monoServerMeta.api.CalendarSessionPageRequest;

@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class AdminConsultationSessionTest {

    @Autowired
    @InjectMocks
    private CommonOperationService commonOperationService;

    @Test
    public void createCourseEvent(){
        
    }
}
