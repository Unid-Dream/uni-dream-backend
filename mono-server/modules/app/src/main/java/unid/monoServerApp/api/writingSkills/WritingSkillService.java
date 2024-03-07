package unid.monoServerApp.api.writingSkills;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.WritingTopicTable;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedWritingPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.skill.DbStudentUploadedInterview;
import unid.monoServerApp.database.table.skill.DbStudentUploadedSupervisorReview;
import unid.monoServerApp.database.table.skill.DbStudentUploadedWriting;
import unid.monoServerApp.database.table.skill.DbWritingTopic;
import unid.monoServerApp.mapper.CommonMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.StudentUploadedMapper;
import unid.monoServerApp.mapper.WritingTopicMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.Currency;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.jooq.impl.DSL.*;
import static unid.jooqMono.model.Tables.*;
import static unid.jooqMono.model.tables.EcaCourseTable.ECA_COURSE;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class WritingSkillService {
    private final DSLContext dslContext;
    private final SessionService sessionService;
    private final WritingTopicMapper writingTopicMapper;
    private final I18nMapper i18nMapper;
    private final StudentUploadedMapper studentUploadedMapper;



    public  WritingSkillAssessmentResponse query(UUID studentProfileId) {
        List<DbStudentUploadedWriting.Result> results = dslContext.select(
                        multiset(
                                dslContext.select()
                                        .from(I18N)
                                        .where(
                                                I18N.ID.eq(any(STUDENT_UPLOADED_WRITING.WRITING_TOPIC_ID))
                                        )
                        ).as(DbStudentUploadedWriting.Result.Fields.topicI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                        STUDENT_PAYMENT_TRANSACTION.TRANSACTION_AMOUNT.as(DbStudentUploadedWriting.Result.Fields.price),
                        STUDENT_UPLOADED_WRITING.UPLOADED_FILE,
                        STUDENT_UPLOADED_WRITING.UPDATED_ON,
                        STUDENT_UPLOADED_WRITING.ID,
                        STUDENT_UPLOADED_WRITING.RECOMMENDATION,
                        STUDENT_UPLOADED_WRITING.RECOMMENDED_ACTIVITY,
                        STUDENT_UPLOADED_WRITING.UPDATED_ON,
                        multiset(
                                dslContext.select()
                                        .from(STUDENT_UPLOADED_SUPERVISOR_REVIEW)
                                        .where(
                                                STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(STUDENT_UPLOADED_WRITING.CONTENT_REVIEW_ID)
                                        )
                        ).as(DbStudentUploadedWriting.Result.Fields.content).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbStudentUploadedSupervisorReview.Result.class)),
                        multiset(
                                dslContext.select()
                                        .from(STUDENT_UPLOADED_SUPERVISOR_REVIEW)
                                        .where(
                                                STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(STUDENT_UPLOADED_WRITING.COMPOSITION_REVIEW_ID)
                                        )
                        ).as(DbStudentUploadedWriting.Result.Fields.composition).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbStudentUploadedSupervisorReview.Result.class)),
                        multiset(
                                dslContext.select()
                                        .from(STUDENT_UPLOADED_SUPERVISOR_REVIEW)
                                        .where(
                                                STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(STUDENT_UPLOADED_WRITING.GRAMMAR_AND_EXPRESSION_REVIEW_ID)
                                        )
                        ).as(DbStudentUploadedWriting.Result.Fields.grammarAndExpression).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbStudentUploadedSupervisorReview.Result.class))
                )
                .from(STUDENT_UPLOADED_WRITING,STUDENT_PAYMENT_TRANSACTION)
                .where(
                        STUDENT_UPLOADED_WRITING.PAYMENT_TRANSACTION_ID.eq(STUDENT_PAYMENT_TRANSACTION.ID)
                )
                .and(STUDENT_UPLOADED_WRITING.STUDENT_PROFILE_ID.eq(studentProfileId))
                .fetchInto(DbStudentUploadedWriting.Result.class);

        List<WritingSkillAssessmentResponse.Item> list = Lists.newArrayList();
        for(DbStudentUploadedWriting.Result result : results){
            WritingSkillAssessmentResponse.Item response = new WritingSkillAssessmentResponse.Item();
            response.setTopicI18n(i18nMapper.toModel(result.getTopicI18n()));
            response.setFileUrl(result.getUploadedFile());
            response.setPrice(result.getPrice());
            response.setCurrency(Currency.USD);
            response.setSubmitTime(Objects.requireNonNull(result.getUpdatedOn()).toLocalDateTime());
            response.setId(result.getId());
            response.setResponseTime(result.getUpdatedOn().toLocalDateTime());
            //查询feedback;
            UploadedSupervisorReviewResponse composition = studentUploadedMapper.toResponse(result.getComposition());
            UploadedSupervisorReviewResponse grammarAndExpression = studentUploadedMapper.toResponse(result.getGrammarAndExpression());
            UploadedSupervisorReviewResponse content = studentUploadedMapper.toResponse(result.getContent());
            response.setComposition(composition);
            response.setGrammarAndExpression(grammarAndExpression);
            response.setContent(content);
            response.setRecommendedActivity(result.getRecommendedActivity());
            response.setRecommendation(result.getRecommendation());
            list.add(response);
        }
        WritingSkillAssessmentResponse response = new WritingSkillAssessmentResponse();
        response.setList(list);
        return response;
    }


    public WritingTopicResponse query() {
        DbWritingTopic.Result record = dslContext.select(
                        WRITING_TOPIC.ID,
                        WRITING_TOPIC.PRICE,
                        multiset(
                                select().from(I18N).where(I18N.ID.eq(any(WRITING_TOPIC.TOPIC_I18N_ID)))
                        ).as(DbWritingTopic.Result.Fields.items).convertFrom(r -> r.isEmpty() ? null : r.into(DbI18N.Result.class))
                )
                .from(WRITING_TOPIC)
                .fetchOptional()
                .orElseThrow(()->Exceptions.notFound("Writing Topic Not Found")).into(DbWritingTopic.Result.class);
        return writingTopicMapper.toResponse(record);
    }

    public UUID save(UUID studentProfileId, WritingSkillRequest request) {
        sessionService.initDatabaseSession();

        //0. 查询单价
        DbWritingTopic.Result record = dslContext.select()
                .from(WritingTopicTable.WRITING_TOPIC)
                .where(WritingTopicTable.WRITING_TOPIC.PRICE.isNotNull())
                .fetchOptionalInto(DbWritingTopic.Result.class)
                .orElseThrow(() -> Exceptions.notFound("Writing Skill Interview Not Found"));
        //1. 创建交易记录(student_payment_transaction)
        var transaction = writingTopicMapper.merge(record,studentProfileId);
        UUID transactionId =  dslContext
                .insertInto(STUDENT_PAYMENT_TRANSACTION)
                .set(dslContext.newRecord(STUDENT_PAYMENT_TRANSACTION, transaction))
                .returning(STUDENT_PAYMENT_TRANSACTION.ID) // 指定要返回的字段，这里是ID字段
                .fetchOptionalInto(StudentPaymentTransactionPojo.class)
                .orElseThrow(()-> Exceptions.notFound("STUDENT_PAYMENT_TRANSACTION INSERT FAILURE"))
                .getId();
        //2. 保存writing_skill记录
        UUID uploadId =  dslContext
                .insertInto(STUDENT_UPLOADED_WRITING)
                .set(dslContext.newRecord(STUDENT_UPLOADED_WRITING, writingTopicMapper.merge(request,studentProfileId,transactionId)))
                .returning(STUDENT_UPLOADED_WRITING.ID) // 指定要返回的字段，这里是ID字段
                .fetchOptionalInto(StudentUploadedWritingPojo.class)
                .orElseThrow(()-> Exceptions.notFound("Student Uploaded Writing INSERT FAILURE"))
                .getId();
        transaction.setTransactionItemRefId(uploadId);
        dslContext.newRecord(STUDENT_PAYMENT_TRANSACTION, transaction).update();
        return transactionId;
    }



}



