package unid.monoServerApp.api.writingSkills;

import io.lettuce.core.support.caching.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.ReviewTypeEnum;
import unid.jooqMono.model.tables.WritingTopicTable;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedInterviewPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedSupervisorReviewPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedWritingPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.api.user.profile.educator.EducatorProfileService;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.skill.DbStudentUploadedInterview;
import unid.monoServerApp.database.table.skill.DbStudentUploadedSupervisorReview;
import unid.monoServerApp.database.table.skill.DbStudentUploadedWriting;
import unid.monoServerApp.database.table.skill.DbWritingTopic;
import unid.monoServerApp.mapper.*;
import unid.monoServerApp.service.SessionService;
import unid.monoServerApp.util.TypeSerialNumberUtils;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.api.version2.InterviewSkillAssignPayload;
import unid.monoServerMeta.api.version2.WritingSkillAssignPayload;
import unid.monoServerMeta.api.version2.request.InterviewSkillAssignRequest;
import unid.monoServerMeta.api.version2.request.WritingSkillAssignRequest;
import unid.monoServerMeta.api.version2.request.WritingSkillReviewRequest;
import unid.monoServerMeta.model.Currency;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.ReviewStatus;

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
    private final RedisTemplate<String, String> redisTemplateRefCache;
    private final DbStudentUploadedWriting dbStudentUploadedWriting;
    private final DbStudentUploadedSupervisorReview dbStudentUploadedSupervisorReview;
    private final StudentUploadedSupervisorReviewMapper studentUploadedSupervisorReviewMapper;
    private final EducatorProfileService educatorProfileService;

    public WritingSkillAssessmentResponse query(UUID studentProfileId) {
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
                .from(STUDENT_UPLOADED_WRITING, STUDENT_PAYMENT_TRANSACTION)
                .where(
                        STUDENT_UPLOADED_WRITING.PAYMENT_TRANSACTION_ID.eq(STUDENT_PAYMENT_TRANSACTION.ID)
                )
                .and(STUDENT_UPLOADED_WRITING.STUDENT_PROFILE_ID.eq(studentProfileId))
                .fetchInto(DbStudentUploadedWriting.Result.class);

        List<WritingSkillAssessmentResponse.Item> list = Lists.newArrayList();
        for (DbStudentUploadedWriting.Result result : results) {
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

    public UniPageResponse<WritingSkillPayload> page(WritingSkillPageRequest request) {
        var table = dbStudentUploadedWriting.getTable();
        List<WritingSkillPayload> payload = dbStudentUploadedWriting.getDsl()
                .select(
                        table.asterisk(),
                        table.CREATED_ON.as(WritingSkillPayload.Fields.submissionTime),
                        DSL.case_()
                                .when(table.REVIEW_TYPE.eq(ReviewTypeEnum.REVIEWED), ReviewTypeEnum.REVIEWED)
                                .otherwise(ReviewTypeEnum.PENDING)
                                .as(WritingSkillPayload.Fields.status),
                        DSL.multiset(
                                DSL.select(
                                                STUDENT_PROFILE.asterisk(),
                                                USER.EMAIL,
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                                ).as(WritingSkillPayload.StudentProfile.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                                ).as(WritingSkillPayload.StudentProfile.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                        )
                                        .from(STUDENT_PROFILE, USER)
                                        .where(STUDENT_PROFILE.USER_ID.eq(USER.ID).and(STUDENT_PROFILE.ID.eq(table.STUDENT_PROFILE_ID))
                                        )
                        ).as(WritingSkillPayload.Fields.studentProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(WritingSkillPayload.StudentProfile.class))
                )
                .select(count().over().as(WritingSkillPayload.Fields.total))
                .from(table, STUDENT_PAYMENT_TRANSACTION)
                .where(table.PAYMENT_TRANSACTION_ID.eq(STUDENT_PAYMENT_TRANSACTION.ID))
                .orderBy(table.CREATED_ON.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(WritingSkillPayload.class);

        int totalSize = payload.stream()
                .findFirst()
                .map(WritingSkillPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                payload
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public UUID update(WritingSkillReviewRequest payload) {
        var table = dbStudentUploadedWriting.getTable();

        StudentUploadedSupervisorReviewPojo grammarAndExpression = studentUploadedSupervisorReviewMapper.toPojo(payload.getGrammarAndExpression());
        StudentUploadedSupervisorReviewPojo composition = studentUploadedSupervisorReviewMapper.toPojo(payload.getComposition());
        StudentUploadedSupervisorReviewPojo content = studentUploadedSupervisorReviewMapper.toPojo(payload.getContent());

        dbStudentUploadedSupervisorReview.getDao().insert(grammarAndExpression);
        dbStudentUploadedSupervisorReview.getDao().insert(composition);
        dbStudentUploadedSupervisorReview.getDao().insert(content);

        dbStudentUploadedWriting.getDsl()
                .update(table)
                .set(table.COMPOSITION_REVIEW_ID,composition.getId())
                .set(table.GRAMMAR_AND_EXPRESSION_REVIEW_ID,grammarAndExpression.getId())
                .set(table.CONTENT_REVIEW_ID,content.getId())
                .set(table.REVIEW_TYPE, ReviewTypeEnum.REVIEWED)
                .where(table.ID.eq(payload.getId()))
                .execute();

        return payload.getId();
    }


    public WritingSkillPayload get(UUID id) {
        var table = dbStudentUploadedWriting.getTable();
        return dbStudentUploadedWriting.getDsl()
                .select(
                        table.asterisk(),
                        table.CREATED_ON.as(WritingSkillPayload.Fields.submissionTime),
                        DSL.multiset(
                                DSL.select(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID,
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_SCORE.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.score),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_STRENGTH.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.strength),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WEAKNESS.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.weakness),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_IMPROVEMENT.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.improvement),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WRAP_UP.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.wrapUp)
                                ).from(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW
                                ).where(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(table.GRAMMAR_AND_EXPRESSION_REVIEW_ID)
                                )
                        ).as(WritingSkillPayload.Fields.grammarAndExpression).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(WritingSkillPayload.StudentUploadedSupervisorReview.class)),


                        DSL.multiset(
                                DSL.select(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID,
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_SCORE.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.score),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_STRENGTH.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.strength),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WEAKNESS.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.weakness),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_IMPROVEMENT.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.improvement),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WRAP_UP.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.wrapUp)
                                ).from(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW
                                ).where(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(table.COMPOSITION_REVIEW_ID)
                                )
                        ).as(WritingSkillPayload.Fields.composition).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(WritingSkillPayload.StudentUploadedSupervisorReview.class)),


                        DSL.multiset(
                                DSL.select(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID,
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_SCORE.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.score),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_STRENGTH.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.strength),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WEAKNESS.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.weakness),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_IMPROVEMENT.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.improvement),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WRAP_UP.as(WritingSkillPayload.StudentUploadedSupervisorReview.Fields.wrapUp)
                                ).from(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW
                                ).where(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(table.CONTENT_REVIEW_ID)
                                )
                        ).as(WritingSkillPayload.Fields.content).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(WritingSkillPayload.StudentUploadedSupervisorReview.class)),


                        DSL.case_()
                                .when(table.REVIEW_TYPE.isNotNull(), table.REVIEW_TYPE)
                                .otherwise(ReviewTypeEnum.PENDING)
                                .as(WritingSkillPayload.Fields.status),
                        DSL.multiset(
                                DSL.select(
                                                STUDENT_PROFILE.asterisk(),
                                                USER.EMAIL,
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                                ).as(WritingSkillPayload.StudentProfile.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                                ).as(WritingSkillPayload.StudentProfile.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                        )
                                        .from(STUDENT_PROFILE, USER)
                                        .where(STUDENT_PROFILE.USER_ID.eq(USER.ID).and(STUDENT_PROFILE.ID.eq(table.STUDENT_PROFILE_ID))
                                        )
                        ).as(WritingSkillPayload.Fields.studentProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(WritingSkillPayload.StudentProfile.class))
                )
                .from(table)
                .where(table.ID.eq(id))
                .fetchOneInto(WritingSkillPayload.class);
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
                .orElseThrow(() -> Exceptions.notFound("Writing Topic Not Found")).into(DbWritingTopic.Result.class);
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
        var transaction = writingTopicMapper.merge(record, studentProfileId);
        //创建订单流水号
        transaction.setTransactionSerialNumber(TypeSerialNumberUtils.generateOrderNumber("IS", redisTemplateRefCache));
        UUID transactionId = dslContext
                .insertInto(STUDENT_PAYMENT_TRANSACTION)
                .set(dslContext.newRecord(STUDENT_PAYMENT_TRANSACTION, transaction))
                .returning(STUDENT_PAYMENT_TRANSACTION.ID) // 指定要返回的字段，这里是ID字段
                .fetchOptionalInto(StudentPaymentTransactionPojo.class)
                .orElseThrow(() -> Exceptions.notFound("STUDENT_PAYMENT_TRANSACTION INSERT FAILURE"))
                .getId();
        //2. 保存writing_skill记录
        UUID uploadId = dslContext
                .insertInto(STUDENT_UPLOADED_WRITING)
                .set(dslContext.newRecord(STUDENT_UPLOADED_WRITING, writingTopicMapper.merge(request, studentProfileId, transactionId)))
                .returning(STUDENT_UPLOADED_WRITING.ID) // 指定要返回的字段，这里是ID字段
                .fetchOptionalInto(StudentUploadedWritingPojo.class)
                .orElseThrow(() -> Exceptions.notFound("Student Uploaded Writing INSERT FAILURE"))
                .getId();
        transaction.setTransactionItemRefId(uploadId);
        dslContext.newRecord(STUDENT_PAYMENT_TRANSACTION, transaction).update();
        return transactionId;
    }


    @Transactional(rollbackFor = Exception.class)
    public WritingSkillAssignPayload update(WritingSkillAssignRequest request) {
        var table = dbStudentUploadedWriting.getTable();
        List<StudentUploadedWritingPojo> writings = dbStudentUploadedWriting.getDsl()
                .select().from(table).where(dbStudentUploadedWriting.getTable().ID.in(request.getWritingIds()))
                .fetchInto(StudentUploadedWritingPojo.class);
        writings.forEach(writing -> writing.setEducatorProfileId(request.getEducatorProfileId()));
        dbStudentUploadedWriting.getDao().update(writings);

        //查询educator
        WritingSkillAssignPayload payload = new WritingSkillAssignPayload();
        EducatorProfileSimpleResponse educator = educatorProfileService.getSimpleCache(request.getEducatorProfileId());

        List<WritingSkillPayload> list = dbStudentUploadedWriting.getDsl()
                .select(
                        table.asterisk(),
                        table.CREATED_ON.as(WritingSkillPayload.Fields.submissionTime),
                        DSL.case_()
                                .when(table.REVIEW_TYPE.isNotNull(), table.REVIEW_TYPE)
                                .otherwise(ReviewTypeEnum.PENDING)
                                .as(WritingSkillPayload.Fields.status),
                        DSL.multiset(
                                DSL.select(
                                                STUDENT_PROFILE.asterisk(),
                                                USER.EMAIL,
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                                ).as(WritingSkillPayload.StudentProfile.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                                ).as(WritingSkillPayload.StudentProfile.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                        )
                                        .from(STUDENT_PROFILE, USER)
                                        .where(STUDENT_PROFILE.USER_ID.eq(USER.ID).and(STUDENT_PROFILE.ID.eq(table.STUDENT_PROFILE_ID))
                                        )
                        ).as(WritingSkillPayload.Fields.studentProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(WritingSkillPayload.StudentProfile.class))
                )
                .from(table, STUDENT_PAYMENT_TRANSACTION)
                .where(table.PAYMENT_TRANSACTION_ID.eq(STUDENT_PAYMENT_TRANSACTION.ID))
                .fetchInto(WritingSkillPayload.class);

        payload.setWritings(list);
        payload.setEducator(educator);
        return payload;
    }
}



