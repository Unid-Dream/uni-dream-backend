package unid.monoServerApp.database.table.learningHub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record11;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.monoServerMeta.api.LearningHubResponse;
import unid.monoServerMeta.model.I18n;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static org.jooq.impl.DSL.*;
import static unid.jooqMono.model.Tables.*;
import static unid.jooqMono.model.Tables.EVENT;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DbLearningHub {
    private final DSLContext dslContext;

    public @NotNull SelectConditionStep<?> getQuery(){
        return dslContext.select(
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(EVENT.TITLE_I18N_ID))
                        ).as(LearningHubResponse.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(EVENT_EXTENSION.AGENDA_I18N_ID))
                        ).as(LearningHubResponse.Fields.agendaI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(EVENT.DESCRIPTION_I18N_ID))
                        ).as(LearningHubResponse.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),

                        multiset(
                                selectCount()
                                        .from(STUDENT_PAYMENT_TRANSACTION)
                                        .where(STUDENT_PAYMENT_TRANSACTION.TRANSACTION_ITEM_REF_ID.eq(EVENT.ID))
                        ).as(LearningHubResponse.Fields.enrollNumber).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(Integer.class)),
                        multiset(
                                select()
                                        .from(EVENT_SCHEDULE_TIME)
                                        .where(EVENT_SCHEDULE_TIME.REF_EVENT_ID.eq(EVENT.ID))
                        ).as(LearningHubResponse.Fields.schedules).convertFrom(r -> r.isEmpty() ? null : r.into(LearningHubResponse.EventTime.class)),
//                        EVENT.END_DATE,
//                        EVENT.START_DATE,
//                        EVENT.START_TIME,
//                        EVENT.END_TIME,
                        EVENT.MAX_NUMBER_OF_STUDENT,
                        EVENT.PRICE,
                        EVENT.POSTER_IMAGE,
                        EVENT_EXTENSION.EDUCATOR_PROFILE_ID,
                        EVENT.ID,
                        EVENT.EVENT_TYPE,
                        COURSE.ACADEMIC_MAJOR_ID,
                        EVENT_EXTENSION.EVENT_STATUS,
                        EVENT_EXTENSION.AGENDA
                )
                .from(COURSE, COURSE_EVENT, EVENT,EVENT_EXTENSION)
                .where(COURSE.ID.eq(COURSE_EVENT.COURSE_ID).and(COURSE_EVENT.EVENT_ID.eq(EVENT.ID)).and(EVENT.ID.eq(EVENT_EXTENSION.EVENT_ID)));

    }

    public @NotNull SelectConditionStep<?> getQueryCnt(){
        return dslContext.selectCount()
                .from(COURSE, COURSE_EVENT, EVENT,EVENT_EXTENSION)
                .where(COURSE.ID.eq(COURSE_EVENT.COURSE_ID).and(COURSE_EVENT.EVENT_ID.eq(EVENT.ID)).and(EVENT.ID.eq(EVENT_EXTENSION.EVENT_ID)));

    }
}
