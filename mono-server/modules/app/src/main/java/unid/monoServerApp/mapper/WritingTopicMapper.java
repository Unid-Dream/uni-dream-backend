package unid.monoServerApp.mapper;


import org.apache.commons.compress.utils.Lists;
import org.mapstruct.Mapper;
import unid.jooqMono.model.enums.CurrencyEnum;
import unid.jooqMono.model.enums.PaymentMethodEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedWritingPojo;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.skill.DbStudentUploadedWriting;
import unid.monoServerApp.database.table.skill.DbWritingTopic;
import unid.monoServerMeta.api.WritingSkillAssessmentResponse;
import unid.monoServerMeta.api.WritingSkillRequest;
import unid.monoServerMeta.api.WritingTopicResponse;
import unid.monoServerMeta.model.Currency;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Mapper(
        componentModel = "spring"
)
public interface WritingTopicMapper {


    default WritingTopicResponse toResponse(DbWritingTopic.Result result){
        WritingTopicResponse response = new WritingTopicResponse();

        WritingTopicResponse.WritingTopicPayload payload = new WritingTopicResponse.WritingTopicPayload();

        List<WritingTopicResponse.WritingTopicItem> items = Lists.newArrayList();
        for(DbI18N.Result i18n : result.getItems()){
            WritingTopicResponse.WritingTopicItem item = new WritingTopicResponse.WritingTopicItem();
            item.setId(i18n.getId());
            item.setValue(i18n.getEnglish());
            items.add(item);
        }

        WritingTopicResponse.Price price = new WritingTopicResponse.Price();
        price.setAmount(result.getPrice());
        price.setUnit(Currency.USD);

        payload.setList(items);
        response.setTopic(payload);
        response.setPrice(price);
        return response;
    }

    default StudentPaymentTransactionPojo merge(DbWritingTopic.Result record, UUID studentProfileId){
       return new StudentPaymentTransactionPojo()
                .setStudentProfileId(studentProfileId)
                .setTransactionAmount(Objects.requireNonNull(record.getPrice()))
                .setTransactionCurrency(CurrencyEnum.USD)
                .setTransactionItem(StudentTransactionItemEnum.WRITING)
                .setPaymentStatus(PaymentStatusEnum.PENDING)
                .setTransactionTimeAt(LocalDateTime.now())
                .setPaymentMethod(PaymentMethodEnum.COD_ALIPAY_CN);
    }


    default StudentUploadedWritingPojo merge(WritingSkillRequest request, UUID studentProfileId, UUID transactionId){
        return new StudentUploadedWritingPojo()
                .setStudentProfileId(studentProfileId)
                .setWritingTopicId(request.getTopicId())
                .setPaymentTransactionId(transactionId)
                .setUploadedFile(request.getFileUrl());
    }
}
