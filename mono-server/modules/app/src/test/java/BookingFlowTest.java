import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import unid.monoServerApp.SpringApp;

@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class BookingFlowTest {
    @Autowired
    private MockMvc mockMvc;


    //正常流程
    @Test
    public void success(){
        //1.

    }
}
