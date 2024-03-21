import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import unid.monoServerApp.SpringApp;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarService;
import unid.monoServerMeta.api.ConsultationSessionPageRequest;

@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class AdminConsultationSessionTest {

    @Autowired
    @InjectMocks
    private EducatorCalendarService educatorCalendarService;

    @Test
    public void testPage(){
        ConsultationSessionPageRequest request = new ConsultationSessionPageRequest();
        request.setPageSize(10);
        request.setPageNumber(1);
        request.setSearchKey("");
        educatorCalendarService.getPage(request);
    }
}
