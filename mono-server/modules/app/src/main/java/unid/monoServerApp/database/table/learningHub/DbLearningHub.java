package unid.monoServerApp.database.table.learningHub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record11;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.LanguageTable;
import unid.jooqMono.model.tables.daos.LanguageDao;
import unid.monoServerApp.database.Db;
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
                        EVENT.asterisk(),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(EVENT.TITLE_I18N_ID))
                        ).as(LearningHubResponse.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(EVENT.AGENDA_I18N_ID))
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
                                        .where(EVENT_SCHEDULE_TIME.EVENT_ID.eq(EVENT.ID))
                        ).as(LearningHubResponse.Fields.schedules).convertFrom(r -> r.isEmpty() ? null : r.into(LearningHubResponse.EventTime.class))
                )
                .from(EVENT)
                .where(EVENT.EVENT_STATUS.isNotNull());

    }

    public @NotNull SelectConditionStep<?> getQueryCnt(){
        return dslContext.selectCount()
                .from(EVENT)
                .where(EVENT.EVENT_STATUS.isNotNull());
    }
}
