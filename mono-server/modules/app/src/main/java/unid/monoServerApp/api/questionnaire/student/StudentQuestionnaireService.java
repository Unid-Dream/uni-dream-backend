package unid.monoServerApp.api.questionnaire.student;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwh.springWebStarter.response.UniErrorCode;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.jooqMono.model.tables.pojos.StudentMilestoneOptionsPojo;
import unid.jooqMono.model.tables.pojos.StudentMilestoneQuestionnairePojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.milestone.DbMilestoneOption;
import unid.monoServerApp.database.table.milestone.DbMilestoneQuestionnaire;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.StudentMilestoneOptionMapper;
import unid.monoServerApp.mapper.StudentMilestoneQuestionnaireMapper;
import unid.monoServerMeta.api.EcaCourseResponse;
import unid.monoServerMeta.api.StudentMilestonePayload;
import unid.monoServerMeta.api.StudentMilestoneRequest;
import unid.monoServerMeta.api.StudentMilestoneResponse;
import unid.monoServerMeta.api.version2.StudentMilestoneQuestionnairePayload;
import unid.monoServerMeta.api.version2.request.StudentMilestoneQuestionnaireRequest;
import unid.monoServerMeta.model.I18n;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.multiset;
import static unid.jooqMono.model.Tables.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentQuestionnaireService {
    private final DSLContext dslContext;
    private final StudentMilestoneQuestionnaireMapper studentMilestoneQuestionnaireMapper;
    private final StudentMilestoneOptionMapper studentMilestoneOptionMapper;

    private final DbMilestoneQuestionnaire dbMilestoneQuestionnaire;
    private final DbMilestoneOption dbMilestoneOption;
    private final I18nMapper i18nMapper;
    private final DbI18N dbI18N;

    public void saveOrUpdate(UUID studentProfileId, StudentMilestoneRequest request) {
        //查询学生是否提交过, 如果没有提交过,则insert, 如果提交过,则update
        dslContext.insertInto(STUDENT_MILESTONE_MAP,
                        STUDENT_MILESTONE_MAP.STUDENT_PROFILE_ID, STUDENT_MILESTONE_MAP.QUESTIONNAIRE)
                .values(studentProfileId, JSONB.valueOf(JSONUtil.toJsonStr(request.getPayload())))
                .onConflict(STUDENT_MILESTONE_MAP.STUDENT_PROFILE_ID) // 指定发生冲突时应检查的列
                .doUpdate()
                .set(STUDENT_MILESTONE_MAP.QUESTIONNAIRE, JSONB.valueOf(JSONUtil.toJsonStr(request.getPayload())))
                .execute();
    }

    public List<StudentMilestoneQuestionnairePayload> query() {
        return dslContext.select(
                        STUDENT_MILESTONE_QUESTIONNAIRE.asterisk(),
                        DSL.multiset(
                                DSL.select()
                                        .from(I18N)
                                        .where(I18N.ID.eq(STUDENT_MILESTONE_QUESTIONNAIRE.QUESTION_I18N_ID))
                        ).as(StudentMilestonePayload.Fields.questionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.select(
                                                STUDENT_MILESTONE_OPTIONS.asterisk(),
                                                DSL.multiset(
                                                        DSL.select().from(I18N).where(I18N.ID.eq(STUDENT_MILESTONE_OPTIONS.ANSWER_I18N_ID))
                                                ).as(StudentMilestoneQuestionnairePayload.StudentMilestoneAnswerPayload.Fields.answerI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                        )
                                        .from(STUDENT_MILESTONE_OPTIONS)
                                        .where(STUDENT_MILESTONE_OPTIONS.QUESTIONNAIRE_ID.eq(STUDENT_MILESTONE_QUESTIONNAIRE.ID))
                                        .orderBy(STUDENT_MILESTONE_OPTIONS.SORT_ID.asc())
                        ).as(StudentMilestoneQuestionnairePayload.Fields.answers).convertFrom(r -> r.isEmpty() ? null : r.into(StudentMilestoneQuestionnairePayload.StudentMilestoneAnswerPayload.class))
                )
                .from(STUDENT_MILESTONE_QUESTIONNAIRE)
                .orderBy(STUDENT_MILESTONE_QUESTIONNAIRE.SORT_ID.asc())
                .fetchInto(StudentMilestoneQuestionnairePayload.class);
    }

    public StudentMilestoneResponse query(UUID studentProfileId) {
        //查询学生是否已经提交过
        Optional<StudentMilestone> milestoneOptional = dslContext.select()
                .from(STUDENT_MILESTONE_MAP)
                .where(STUDENT_MILESTONE_MAP.STUDENT_PROFILE_ID.eq(studentProfileId))
                .fetchOptionalInto(StudentMilestone.class);
        List<StudentMilestonePayload> payload;
        if (milestoneOptional.isPresent()) {
            payload = JSONUtil.toList(milestoneOptional.get().questionnaire.data(), StudentMilestonePayload.class);
        } else {
            payload = getDefaultQuery();
        }
        StudentMilestoneResponse response = new StudentMilestoneResponse();
        response.setQuestionnaire(payload);
        return response;
    }


    private List<StudentMilestonePayload> getDefaultQuery() {
        return dslContext.select(
                        STUDENT_MILESTONE_QUESTIONNAIRE.asterisk(),
                        multiset(
                                DSL.select().from(I18N).where(I18N.ID.eq(STUDENT_MILESTONE_QUESTIONNAIRE.QUESTION_I18N_ID))
                        ).as(StudentMilestonePayload.Fields.questionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        multiset(
                                DSL.select(
                                                STUDENT_MILESTONE_OPTIONS.ID.as(StudentMilestonePayload.Answer.Fields.id),
                                                STUDENT_MILESTONE_OPTIONS.OPTION_TYPE.as(StudentMilestonePayload.Answer.Fields.type),
                                                multiset(
                                                        DSL.select()
                                                                .from(I18N).where(I18N.ID.eq(STUDENT_MILESTONE_OPTIONS.ANSWER_I18N_ID))
                                                ).as(StudentMilestonePayload.Answer.Fields.answerI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class))
                                        )
                                        .from(STUDENT_MILESTONE_OPTIONS)
                                        .where(STUDENT_MILESTONE_OPTIONS.QUESTIONNAIRE_ID.eq(STUDENT_MILESTONE_QUESTIONNAIRE.ID))
                        ).as(StudentMilestonePayload.Fields.answerItems).convertFrom(r -> r.isEmpty() ? null : r.into(StudentMilestonePayload.Answer.class))
                )
                .from(STUDENT_MILESTONE_QUESTIONNAIRE)
                .fetchInto(StudentMilestonePayload.class);
    }


    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID questionId, UUID answerId) {
        if(questionId != null){
            dbMilestoneQuestionnaire.getDao().deleteById(questionId);
        }
        if(answerId != null){
            dbMilestoneOption.getDao().deleteById(answerId);
        }
    }



    @Transactional(rollbackFor = Exception.class)
    public void update(StudentMilestoneQuestionnaireRequest request) {
        request.getPayload().forEach(
                question -> {
                    StudentMilestoneQuestionnairePojo pojo = dbMilestoneQuestionnaire.getDsl()
                            .select().from(STUDENT_MILESTONE_QUESTIONNAIRE)
                            .where(STUDENT_MILESTONE_QUESTIONNAIRE.ID.eq(question.getId()))
                            .fetchOptionalInto(StudentMilestoneQuestionnairePojo.class)
                            .map((update)->{
                                //如果存在,则更新
                                studentMilestoneQuestionnaireMapper.merge(update, question);
                                I18nPojo i18n = dbI18N.getDao().fetchOneById(update.getQuestionI18nId());
                                i18nMapper.merge(i18n,question.getQuestionI18n());
                                dbI18N.getDao().update(i18n);
                                dbMilestoneQuestionnaire.getDao().update(update);
                                return update;
                            })
                            .orElseGet(()->{
                                //新增
                                StudentMilestoneQuestionnairePojo create = studentMilestoneQuestionnaireMapper.toPojo(question);
                                I18nPojo i18n = i18nMapper.toPojo(question.getQuestionI18n());
                                dbI18N.getDao().insert(i18n);
                                create.setQuestionI18nId(i18n.getId());
                                dbMilestoneQuestionnaire.getDao().insert(create);
                                return create;
                            });

                    question.getAnswers().forEach((answer)-> dbMilestoneOption.getDsl()
                            .select().from(STUDENT_MILESTONE_OPTIONS)
                            .where(STUDENT_MILESTONE_OPTIONS.ID.eq(answer.getId()))
                            .fetchOptionalInto(StudentMilestoneOptionsPojo.class)
                            .map((update)->{
                                //如果存在,则更新
                                studentMilestoneOptionMapper.merge(update, answer);
                                I18nPojo i18n = dbI18N.getDao().fetchOneById(update.getAnswerI18nId());
                                i18nMapper.merge(i18n,answer.getAnswerI18n());
                                dbI18N.getDao().update(i18n);
                                dbMilestoneOption.getDao().update(update);
                                return update;
                            })
                            .orElseGet(()->{
                                StudentMilestoneOptionsPojo create = studentMilestoneOptionMapper.toPojo(answer);
                                I18nPojo i18n = i18nMapper.toPojo(answer.getAnswerI18n());
                                dbI18N.getDao().insert(i18n);
                                create.setAnswerI18nId(i18n.getId());
                                create.setQuestionnaireId(pojo.getId());
                                dbMilestoneOption.getDao().insert(create);
                                return create;
                            }));
                }
        );
    }




    @Data
    @FieldNameConstants
    public static class StudentMilestone {
        private JSONB questionnaire;
    }


}
