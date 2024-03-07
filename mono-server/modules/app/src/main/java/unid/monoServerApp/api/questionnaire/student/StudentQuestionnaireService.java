package unid.monoServerApp.api.questionnaire.student;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.milestone.DbMilestoneQuestionnaire;
import unid.monoServerMeta.api.EcaCourseResponse;
import unid.monoServerMeta.api.StudentMilestonePayload;
import unid.monoServerMeta.api.StudentMilestoneRequest;
import unid.monoServerMeta.api.StudentMilestoneResponse;
import unid.monoServerMeta.model.I18n;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.multiset;
import static unid.jooqMono.model.Tables.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentQuestionnaireService {
    private final DSLContext dslContext;

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


    public StudentMilestoneResponse query(UUID studentProfileId) {
        //查询学生是否已经提交过
        Optional<StudentMilestone> milestoneOptional = dslContext.select()
                .from(STUDENT_MILESTONE_MAP)
                .where(STUDENT_MILESTONE_MAP.STUDENT_PROFILE_ID.eq(studentProfileId))
                .fetchOptionalInto(StudentMilestone.class);
        List<StudentMilestonePayload> payload;
        if(milestoneOptional.isPresent()){
            payload = JSONUtil.toList(milestoneOptional.get().questionnaire.data(),StudentMilestonePayload.class);
        }else{
            payload = getDefaultQuery();
        }
        StudentMilestoneResponse response = new StudentMilestoneResponse();
        response.setQuestionnaire(payload);
        return response;
    }


    private List<StudentMilestonePayload> getDefaultQuery(){
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

    @Data
    @FieldNameConstants
    public static class StudentMilestone{
        private JSONB questionnaire;
    }



}
