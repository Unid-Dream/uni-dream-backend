package unid.monoServerApp.api.interviewSkills;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.Range;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwh.springWebStarter.response.UniErrorCode;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.ReviewTypeEnum;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedInterviewPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedSupervisorReviewPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.skill.DbInterviewTopic;
import unid.monoServerApp.database.table.skill.DbStudentUploadedInterview;
import unid.monoServerApp.database.table.skill.DbStudentUploadedSupervisorReview;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.InterviewTopicMapper;
import unid.monoServerApp.mapper.StudentUploadedMapper;
import unid.monoServerApp.mapper.StudentUploadedSupervisorReviewMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerApp.util.SerialNumberUtils;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.api.version2.request.InterviewSkillUpdateRequest;
import unid.monoServerMeta.model.I18n;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private final DbStudentUploadedInterview dbStudentUploadedInterview;
    private final DbStudentUploadedSupervisorReview dbStudentUploadedSupervisorReview;
    private final StudentUploadedSupervisorReviewMapper studentUploadedSupervisorReviewMapper;

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


    public UniPageResponse<InterviewSkillPayload> page(InterviewSkillPageRequest request){
        var table = dbStudentUploadedInterview.getTable();
        List<InterviewSkillPayload> payload = dbStudentUploadedInterview.getDsl()
                .select(
                        table.asterisk(),
                        table.CREATED_ON.as(InterviewSkillPayload.Fields.submissionTime),
                        DSL.case_()
                                .when(table.REVIEW_TYPE.isNotNull(), table.REVIEW_TYPE)
                                .otherwise(ReviewTypeEnum.PENDING)
                                .as(InterviewSkillPayload.Fields.status),
                        DSL.multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(table.INTERVIEW_TOPIC_ID))
                        ).as(InterviewSkillPayload.Fields.topic).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.select(
                                                STUDENT_PROFILE.asterisk(),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                                ).as(InterviewSkillPayload.StudentProfile.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                                ).as(InterviewSkillPayload.StudentProfile.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                        )
                                        .from(STUDENT_PROFILE, USER)
                                        .where(STUDENT_PROFILE.USER_ID.eq(USER.ID).and(STUDENT_PROFILE.ID.eq(table.STUDENT_PROFILE_ID))
                                        )
                        ).as(InterviewSkillPayload.Fields.studentProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(InterviewSkillPayload.StudentProfile.class))
                )
                .select(count().over().as(InterviewSkillPayload.Fields.total))
                .from(table, STUDENT_PAYMENT_TRANSACTION)
                .where(table.PAYMENT_TRANSACTION_ID.eq(STUDENT_PAYMENT_TRANSACTION.ID))
                .orderBy(table.CREATED_ON.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(InterviewSkillPayload.class);

        int totalSize = payload.stream()
                .findFirst()
                .map(InterviewSkillPayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                payload
        );
    }



    public InterviewSkillPayload get(UUID id) {
        var table = dbStudentUploadedInterview.getTable();
        return dbStudentUploadedInterview.getDsl()
                .select(
                        table.asterisk(),
                        //查询对应的topic
                        DSL.multiset(
                            DSL.select()
                                    .from(I18N)
                                    .where(table.INTERVIEW_TOPIC_ID.eq(I18N.ID))
                        ).as(InterviewSkillPayload.Fields.topic).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                        table.CREATED_ON.as(InterviewSkillPayload.Fields.submissionTime),
                        DSL.multiset(
                                DSL.select(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID,
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_SCORE.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.score),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_STRENGTH.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.strength),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WEAKNESS.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.weakness),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_IMPROVEMENT.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.improvement),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WRAP_UP.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.warpUp)
                                ).from(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW
                                ).where(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(table.CONTENT_REVIEW_ID)
                                )
                        ).as(InterviewSkillPayload.Fields.content).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(InterviewSkillPayload.StudentUploadedSupervisorReview.class)),


                        DSL.multiset(
                                DSL.select(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID,
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_SCORE.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.score),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_STRENGTH.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.strength),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WEAKNESS.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.weakness),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_IMPROVEMENT.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.improvement),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WRAP_UP.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.warpUp)
                                ).from(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW
                                ).where(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(table.CHARISMA_REVIEW_ID)
                                )
                        ).as(InterviewSkillPayload.Fields.charisma).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(InterviewSkillPayload.StudentUploadedSupervisorReview.class)),


                        DSL.multiset(
                                DSL.select(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID,
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_SCORE.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.score),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_STRENGTH.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.strength),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WEAKNESS.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.weakness),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_IMPROVEMENT.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.improvement),
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.SUPERVISOR_COMMENTED_WRAP_UP.as(InterviewSkillPayload.StudentUploadedSupervisorReview.Fields.warpUp)
                                ).from(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW
                                ).where(
                                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(table.CLARITY_REVIEW_ID)
                                )
                        ).as(InterviewSkillPayload.Fields.clarity).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(InterviewSkillPayload.StudentUploadedSupervisorReview.class)),


                        DSL.case_()
                                .when(table.REVIEW_TYPE.isNotNull(), table.REVIEW_TYPE)
                                .otherwise(ReviewTypeEnum.PENDING)
                                .as(InterviewSkillPayload.Fields.status),
                        DSL.multiset(
                                DSL.select(
                                                STUDENT_PROFILE.asterisk(),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                                                ).as(InterviewSkillPayload.StudentProfile.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                                                DSL.multiset(
                                                        DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                                                ).as(InterviewSkillPayload.StudentProfile.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                        )
                                        .from(STUDENT_PROFILE, USER)
                                        .where(STUDENT_PROFILE.USER_ID.eq(USER.ID).and(STUDENT_PROFILE.ID.eq(table.STUDENT_PROFILE_ID))
                                        )
                        ).as(InterviewSkillPayload.Fields.studentProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(InterviewSkillPayload.StudentProfile.class))
                )
                .from(table)
                .where(table.ID.eq(id))
                .fetchOneInto(InterviewSkillPayload.class);
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


    @Transactional(rollbackFor = Exception.class)
    public InterviewSkillPayload update(InterviewSkillPayload payload) {
        var table = dbStudentUploadedInterview.getTable();
        Range<Integer> range = Range.between(1, 5);
        if(!range.contains(payload.getCharisma().getScore())||!range.contains(payload.getClarity().getScore())||!range.contains(payload.getContent().getScore())){
            throw Exceptions.business(UniErrorCode.INTERVIEW_SKILLS_SCORE_IS_ERROR);
        }

        StudentUploadedInterviewPojo interview = dbStudentUploadedInterview.getDsl()
                .select().from(table).where(dbStudentUploadedInterview.getTable().ID.eq(payload.getId()))
                .fetchOptionalInto(StudentUploadedInterviewPojo.class)
                .orElseThrow(()-> Exceptions.business(UniErrorCode.INTERVIEW_SKILLS_IS_NOT_EXIST));

        dbStudentUploadedSupervisorReview.getDsl()
                        .select().from(STUDENT_UPLOADED_SUPERVISOR_REVIEW)
                .where(
                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(interview.getContentReviewId())
                ).fetchOptionalInto(StudentUploadedSupervisorReviewPojo.class)
                        .ifPresentOrElse(
                                review -> {
                                    studentUploadedSupervisorReviewMapper.merge(review,payload.getContent());
                                    dbStudentUploadedSupervisorReview.getDao().update(review);
                                },
                                () -> {
                                    StudentUploadedSupervisorReviewPojo pojo = studentUploadedSupervisorReviewMapper.toPojo(payload.getContent());
                                    dbStudentUploadedSupervisorReview.getDao().insert(pojo);
                                    interview.setContentReviewId(pojo.getId());
                                }
                        );

        dbStudentUploadedSupervisorReview.getDsl()
                .select().from(STUDENT_UPLOADED_SUPERVISOR_REVIEW)
                .where(
                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(interview.getClarityReviewId())
                ).fetchOptionalInto(StudentUploadedSupervisorReviewPojo.class)
                .ifPresentOrElse(
                        review -> {
                            studentUploadedSupervisorReviewMapper.merge(review,payload.getClarity());
                            dbStudentUploadedSupervisorReview.getDao().update(review);
                        },
                        () -> {
                            StudentUploadedSupervisorReviewPojo review = studentUploadedSupervisorReviewMapper.toPojo(payload.getClarity());
                            dbStudentUploadedSupervisorReview.getDao().insert(review);
                            interview.setClarityReviewId(review.getId());
                        }
                );

        dbStudentUploadedSupervisorReview.getDsl()
                .select().from(STUDENT_UPLOADED_SUPERVISOR_REVIEW)
                .where(
                        STUDENT_UPLOADED_SUPERVISOR_REVIEW.ID.eq(interview.getCharismaReviewId())
                ).fetchOptionalInto(StudentUploadedSupervisorReviewPojo.class)
                .ifPresentOrElse(
                        review -> {
                            studentUploadedSupervisorReviewMapper.merge(review,payload.getCharisma());
                            dbStudentUploadedSupervisorReview.getDao().update(review);
                        },
                        () -> {
                            StudentUploadedSupervisorReviewPojo review = studentUploadedSupervisorReviewMapper.toPojo(payload.getCharisma());
                            dbStudentUploadedSupervisorReview.getDao().insert(review);
                            interview.setCharismaReviewId(review.getId());
                        }
                );

        dbStudentUploadedInterview.getDsl()
                .update(table)
                .set(table.CHARISMA_REVIEW_ID,interview.getCharismaReviewId())
                .set(table.CLARITY_REVIEW_ID,interview.getClarityReviewId())
                .set(table.CONTENT_REVIEW_ID,interview.getContentReviewId())
                .set(table.REVIEW_TYPE, ReviewTypeEnum.REVIEWED)
                .where(table.ID.eq(payload.getId()))
                .execute();

        return payload;
    }
}



