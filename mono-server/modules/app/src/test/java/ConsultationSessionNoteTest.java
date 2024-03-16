import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import unid.monoServerApp.SpringApp;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarService;
import unid.monoServerApp.api.user.profile.educator.calendar.comment.EducatorSessionCommentService;

import java.util.UUID;

@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class ConsultationSessionNoteTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    @InjectMocks
    private EducatorSessionCommentService educatorSessionCommentService;


    @Test
    public void testGetSessionComment(){
        UUID calendarId = UUID.fromString("f43ff822-e164-4073-9112-d41a80d9609d");
        educatorSessionCommentService.getComments(calendarId);
    }
}
