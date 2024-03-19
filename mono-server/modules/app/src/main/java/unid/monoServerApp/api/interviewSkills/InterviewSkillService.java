package unid.monoServerApp.api.interviewSkills;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedInterviewPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.skill.DbInterviewTopic;
import unid.monoServerApp.database.table.skill.DbStudentUploadedInterview;
import unid.monoServerApp.database.table.skill.DbStudentUploadedSupervisorReview;
import unid.monoServerApp.database.table.skill.DbStudentUploadedWriting;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.InterviewTopicMapper;
import unid.monoServerApp.mapper.StudentUploadedMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerApp.util.SerialNumberUtils;
import unid.monoServerMeta.api.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.jooq.impl.DSL.*;
import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class InterviewSkillService {
    private final DSLContext dslContext;
    private final SessionService sessionService;

    private final InterviewTopicMapper interviewTopicMapper;
    private final I18nMapper i18nMapper;
    private final StudentUploadedMapper studentUploadedMapper;
    private final RedisTemplate<String, String> redisTemplateRefCache;


    public InterviewTopicResponse query() {
        DbInterviewTopic.Result record = dslContext.select(
                        INTERVIEW_TOPIC.ID,
                        INTERVIEW_TOPIC.PRICE,
                        multiset(
                                select().from(I18N).where(I18N.ID.eq(any(INTERVIEW_TOPIC.TOPIC_I18N_ID)))
                        ).as(DbInterviewTopic.Result.Fields.items).convertFrom(r -> r.isEmpty() ? null : r.into(DbI18N.Result.class))
                )
                .from(INTERVIEW_TOPIC)
                .fetchOptional()
                .orElseThrow(()->Exceptions.notFound("Interview Topic Not Found"))
                .into(DbInterviewTopic.Result.class);
        return interviewTopicMapper.toResponse(record);
    }

    public InterviewSkillAssessmentResponse query(UUID studentProfileId) {
        List<DbStudentUploadedInterview.Result> results = dslContext.select(
                        multiset(
                                dslContext.select()
                                        .from(I18N)
                                        .where(
                                                I18N.ID.eq(any(STUDENT_UPLOADED_INTERVIEW.INTERVIEW_TOPIC_ID))
                                        )
                        ).as(DbStudentUploadedInterview.Result.Fields.topicI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_AMOUNT.as(DbStudentUploadedInterview.Result.Fields.price),
                        STUDENT_UPLOADED_INTERVIEW.UPLOADED_FILE,
                        STUDENT_UPLOADED_INTERVIEW.UPDATED_ON,
                        STUDENT_UPLOADED_INTERVIEW.ID,
                        STUDENT_UPLOADED_INTERVIEW.RECOMMENDED_ACTIVITY,
                        STUDENT_UPLOADED_INTERVIEW.RECOMMENDATION,
                        multiset(
                                dslContext.select()
                                        .from(STUDENT_UPLOADED_SUPERVISOR_REVIEW)
                                        .where(
                                                STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(STUDENT_UPLOADED_INTERVIEW.CHARISMA_REVIEW_ID)
                                        )
                        ).as(DbStudentUploadedInterview.Result.Fields.charisma).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbStudentUploadedSupervisorReview.Result.class)),
                        multiset(
                                dslContext.select()
                                        .from(STUDENT_UPLOADED_SUPERVISOR_REVIEW)
                                        .where(
                                                STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(STUDENT_UPLOADED_INTERVIEW.CLARITY_REVIEW_ID)
                                        )
                        ).as(DbStudentUploadedInterview.Result.Fields.clarity).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbStudentUploadedSupervisorReview.Result.class)),
                        multiset(
                                dslContext.select()
                                        .from(STUDENT_UPLOADED_SUPERVISOR_REVIEW)
                                        .where(
                                                STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(STUDENT_UPLOADED_INTERVIEW.CONTENT_REVIEW_ID)
                                        )
                        ).as(DbStudentUploadedInterview.Result.Fields.content).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbStudentUploadedSupervisorReview.Result.class))
                        )
                .from(STUDENT_UPLOADED_INTERVIEW,STUDENT_PAYMENT_TRANSACTION)
                .where(
                        STUDENT_UPLOADED_INTERVIEW.PAYMENT_TRANSACTION_ID.eq(STUDENT_PAYMENT_TRANSACTION.ID)
                )
                .and(STUDENT_UPLOADED_INTERVIEW.STUDENT_PROFILE_ID.eq(studentProfileId))
                .fetchInto(DbStudentUploadedInterview.Result.class);

        List<InterviewSkillAssessmentResponse.Item> list = Lists.newArrayList();
        for(DbStudentUploadedInterview.Result result : results){
            InterviewSkillAssessmentResponse.Item item = new InterviewSkillAssessmentResponse.Item();
            item.setTopicI18n(i18nMapper.toModel(result.getTopicI18n()));
            item.setFileUrl(result.getUploadedFile());
            item.setPrice(result.getPrice());
            item.setSubmitTime(Objects.requireNonNull(result.getUpdatedOn()).toLocalDateTime());
            item.setId(result.getId());
            item.setResponseTime(result.getUpdatedOn().toLocalDateTime());
            //查询feedback;
            UploadedSupervisorReviewResponse charisma = studentUploadedMapper.toResponse(result.getCharisma());
            UploadedSupervisorReviewResponse clarity = studentUploadedMapper.toResponse(result.getClarity());
            UploadedSupervisorReviewResponse content = studentUploadedMapper.toResponse(result.getContent());
            item.setCharisma(charisma);
            item.setClarity(clarity);
            item.setContent(content);

            item.setRecommendedActivity(result.getRecommendedActivity());
            item.setRecommendation(result.getRecommendation());
            list.add(item);
        }
        InterviewSkillAssessmentResponse response = new InterviewSkillAssessmentResponse();
        response.setList(list);
        return response;
    }

    public UUID save(UUID studentProfileId, InterviewSkillRequest request) {
        sessionService.initDatabaseSession();

        //0. 查询单价
        DbInterviewTopic.Result record = dslContext.select()
                .from(INTERVIEW_TOPIC)
                .where(INTERVIEW_TOPIC.PRICE.isNotNull())
                .fetchOptionalInto(DbInterviewTopic.Result.class)
                .orElseThrow(() -> Exceptions.notFound("Interview Skill Interview Not Found"));
        //1. 创建交易记录(student_payment_transaction)
        var transaction = interviewTopicMapper.merge(record,studentProfileId);
        transaction.setTransactionSerialNumber(SerialNumberUtils.generateOrderNumber("WS",redisTemplateRefCache));

        UUID transactionId =  dslContext
                .insertInto(STUDENT_PAYMENT_TRANSACTION)
                .set(dslContext.newRecord(STUDENT_PAYMENT_TRANSACTION, transaction))
                .returning(STUDENT_PAYMENT_TRANSACTION.ID) // 指定要返回的字段，这里是ID字段
                .fetchOptionalInto(StudentPaymentTransactionPojo.class)
                .orElseThrow(()-> Exceptions.notFound("STUDENT_PAYMENT_TRANSACTION INSERT FAILURE"))
                .getId();
        UUID uploadId =  dslContext
                .insertInto(STUDENT_UPLOADED_INTERVIEW)
                .set(dslContext.newRecord(STUDENT_UPLOADED_INTERVIEW, interviewTopicMapper.merge(request,studentProfileId,transactionId)))
                .returning(STUDENT_UPLOADED_INTERVIEW.ID) // 指定要返回的字段，这里是ID字段
                .fetchOptionalInto(StudentUploadedInterviewPojo.class)
                .orElseThrow(()-> Exceptions.notFound("Student Uploaded Interview INSERT FAILURE"))
                .getId();
        transaction.setTransactionItemRefId(uploadId);
        dslContext.newRecord(STUDENT_PAYMENT_TRANSACTION, transaction).update();
        return transactionId;
    }



}



