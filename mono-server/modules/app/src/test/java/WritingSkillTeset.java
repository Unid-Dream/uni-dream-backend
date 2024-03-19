import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import unid.monoServerApp.SpringApp;
import unid.monoServerApp.api.user.profile.student.schedule.StudentScheduleService;
import unid.monoServerApp.api.writingSkills.WritingSkillService;
import unid.monoServerMeta.api.WritingSkillRequest;

import java.util.UUID;

@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class WritingSkillTeset {

    @Autowired
    @InjectMocks
    private WritingSkillService writingSkillService;
    @Test
    public void testSubmit(){
        WritingSkillRequest request = new WritingSkillRequest();
        request.setFileUrl("/xxxx/xxxx.pdf");
        request.setTopicId(UUID.fromString("1c65c6e5-bed6-413d-b854-95de94178d79"));

        writingSkillService.save(
                UUID.fromString("94a4e43b-b4c3-4b93-ae8a-27fdd69012cc"),
                request
        );
    }

}
