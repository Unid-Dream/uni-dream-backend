import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import unid.monoServerApp.SpringApp;
import unid.monoServerApp.api.academicMajor.AcademicMajorService;
import unid.monoServerMeta.api.AcademicMajorPayload;
import unid.monoServerMeta.api.version2.request.AcademicMajorCreateRequest;

@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class AcademicMajorTest {
    @MockBean
    private AcademicMajorService academicMajorService;

    @Test
    public void create(){
        AcademicMajorPayload payload = JSONUtil.toBean(
                "",AcademicMajorPayload.class
        );
//        academicMajorService.create(payload);
    }
}
