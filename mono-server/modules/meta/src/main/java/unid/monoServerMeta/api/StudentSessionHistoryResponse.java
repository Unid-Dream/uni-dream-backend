package unid.monoServerMeta.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.TransactionItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldNameConstants
public class StudentSessionHistoryResponse {

    private List<Item> payload;

    @Data
    @FieldNameConstants
    @Schema
    public static class Item{
        private TransactionResponse transaction;
        private EducatorProfileSimpleResponse educator;
        private I18n titleI18n;

    }


    @Data
    @FieldNameConstants
    @Schema
    public static class TransactionResponse{
        private String serviceType;
        private LocalDateTime requestSubmissionTime;
        private LocalDateTime requestTime;
        private I18n firstNameI18n;
        private I18n lastNameI18n;
        private String paymentMethod;
        private BigDecimal amount;
        private String unit;
        private EducatorProfileSimpleResponse educator;
    }



    @Data
    @FieldNameConstants
    @Schema
    public static class CourseEventResponse{
        private I18n titleI18n;
        private I18n descriptionI18n;
        private Integer enrollNumber;
        private BigDecimal price;
        private String posterImage;
        private I18n agendaI18n;
    }






}
