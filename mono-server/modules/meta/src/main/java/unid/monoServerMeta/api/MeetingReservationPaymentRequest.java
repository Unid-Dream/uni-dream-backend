package unid.monoServerMeta.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class MeetingReservationPaymentRequest {
    @NotNull
    private UUID transactionId;
    @NotNull
    private CodWallet codWallet;
    @Nullable
    private String returnUrl;
}
