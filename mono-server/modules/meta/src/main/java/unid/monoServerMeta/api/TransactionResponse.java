package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.I18n;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
public class TransactionResponse {

    private String serviceType;
    private LocalDateTime requestSubmissionTime;
    private LocalDateTime requestTime;
    private I18n firstNameI18n;
    private I18n lastNameI18n;
    private String paymentMethod;
    private BigDecimal amount;
    private String unit;

    @JsonIgnore
    private UUID fistNameI18nId;
    @JsonIgnore
    private UUID lastNameI18nId;
}
