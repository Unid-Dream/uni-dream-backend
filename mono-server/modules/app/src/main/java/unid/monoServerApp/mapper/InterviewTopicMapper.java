package unid.monoServerApp.mapper;


import org.apache.commons.compress.utils.Lists;
import org.mapstruct.Mapper;
import unid.jooqMono.model.enums.CurrencyEnum;
import unid.jooqMono.model.enums.PaymentMethodEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedInterviewPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedWritingPojo;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.skill.DbInterviewTopic;
import unid.monoServerApp.database.table.skill.DbWritingTopic;
import unid.monoServerApp.util.TimeZoneUtils;
import unid.monoServerMeta.api.InterviewSkillRequest;
import unid.monoServerMeta.api.InterviewTopicResponse;
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
public interface InterviewTopicMapper {


    default InterviewTopicResponse toResponse(DbInterviewTopic.Result result){
        InterviewTopicResponse response = new InterviewTopicResponse();

        InterviewTopicResponse.InterviewTopicPayload payload = new InterviewTopicResponse.InterviewTopicPayload();

        List<InterviewTopicResponse.InterviewTopicItem> items = Lists.newArrayList();
        for(DbI18N.Result i18n : result.getItems()){
            InterviewTopicResponse.InterviewTopicItem item = new InterviewTopicResponse.InterviewTopicItem();
            item.setId(i18n.getId());
            item.setValue(i18n.getEnglish());
            items.add(item);
        }

        InterviewTopicResponse.Price price = new InterviewTopicResponse.Price();
        price.setAmount(result.getPrice());
        price.setUnit(Currency.USD);

        payload.setList(items);
        response.setTopic(payload);
        response.setPrice(price);
        return response;
    }

    default StudentPaymentTransactionPojo merge(DbInterviewTopic.Result record, UUID studentProfileId){
       return new StudentPaymentTransactionPojo()
                .setStudentProfileId(studentProfileId)
                .setTransactionAmount(Objects.requireNonNull(record.getPrice()))
                .setTransactionCurrency(CurrencyEnum.USD)
                .setTransactionItem(StudentTransactionItemEnum.INTERVIEW)
                .setPaymentStatus(PaymentStatusEnum.PENDING)
                .setTransactionTimeAt(TimeZoneUtils.getHongKongLocalDateTime(LocalDateTime.now()))
                .setPaymentMethod(PaymentMethodEnum.COD_ALIPAY_CN);
    }


    default StudentUploadedInterviewPojo merge(InterviewSkillRequest request, UUID studentProfileId, UUID transactionId){
        return new StudentUploadedInterviewPojo()
                .setStudentProfileId(studentProfileId)
                .setInterviewTopicId(request.getTopicId())
                .setPaymentTransactionId(transactionId)
                .setUploadedFile(request.getFileUrl());
    }
}
