package unid.monoServerMeta.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.annotation.Validated;
import unid.monoServerMeta.model.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@FieldNameConstants
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterviewTopicResponse {

    private InterviewTopicPayload topic;
    private String description;
    private Price price;

    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @Validated
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class InterviewTopicPayload{
        private List<InterviewTopicItem> list;
    }

    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @Validated
    public static class InterviewTopicItem {
        private UUID id;
        private String value;
    }

    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @Validated
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Price{
        private BigDecimal amount;
        private Currency unit;
    }



}


