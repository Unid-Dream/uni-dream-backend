import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.junit.jupiter.api.BeforeEach;

import java.util.UUID;

public abstract class BaseTest {

    protected final Educator profile = new Educator(
            "dicky.lau@powhouse.tech",
            "Aa1234567890",
            UUID.fromString("b927721e-d2bf-4bf2-ac59-ab1d9aa5cbb2"),
            UUID.fromString("9acc835d-c3ef-4c60-88fc-31f843f94205"),
            ""
    );

    @Data
    @FieldNameConstants
    @AllArgsConstructor
    static class Educator{
        private String email;
        private String loginPassword;
        private UUID educatorProfileId;
        private UUID userId;
        private String unidtoken;
    }

}
