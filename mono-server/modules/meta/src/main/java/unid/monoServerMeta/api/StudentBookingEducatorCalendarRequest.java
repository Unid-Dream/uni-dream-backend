package unid.monoServerMeta.api;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.PaymentMethod;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@FieldNameConstants
@Validated
public class StudentBookingEducatorCalendarRequest {
    @NotNull
    private UUID educatorProfileId;
    @NotNull
    private UUID educatorCalendarId;
}
