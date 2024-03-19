import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import unid.monoServerApp.SpringApp;
import unid.monoServerApp.util.asiapay.PaydollarSecureException;
import unid.monoServerApp.util.asiapay.PaydollarSecureUtil;

@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class AsiaPayTest {
    //secureHash=PaydollarSecureUtil.generatePaymentSecureHash(merchantId,orderRef,currCode,amount,payType);

    @Test
    public void testGeneratePaymentSecureHash() throws PaydollarSecureException {
        String secureHash = PaydollarSecureUtil.generatePaymentSecureHash(
                "88163035",
                "000000000018","156","3000","N");
        System.out.println(secureHash);
    }


}
