package unid.monoServerApp.database.table.studentPaymentTransaction;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.StudentPaymentTransactionTable;
import unid.jooqMono.model.tables.daos.StudentPaymentTransactionDao;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.Properties;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.course.DbEvent;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.user.DbUser;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DbStudentPaymentTransaction extends Db<StudentPaymentTransactionTable, StudentPaymentTransactionDao> {
    private final DbStudentProfile dbStudentProfile;

    @Autowired
    public DbStudentPaymentTransaction(
            DSLContext dslContext,
            DbStudentProfile dbStudentProfile
    ) {
        super(dslContext, Public.PUBLIC.STUDENT_PAYMENT_TRANSACTION, new StudentPaymentTransactionDao(dslContext.configuration()));
        this.dbStudentProfile = dbStudentProfile;
    }

    @Override
    public SelectJoinStep<Record> getQuery(StudentPaymentTransactionTable alias) {
        var sProf = dbStudentProfile.getTable().as(combineAlias(alias, dbStudentProfile.getTable()));
        var sProfQ = dbStudentProfile.getQuery(sProf);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                sProfQ.where(alias.STUDENT_PROFILE_ID.eq(sProf.ID))
                        ).as(Result.Fields.studentProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    public SelectJoinStep<Record> getSimpleQuery(StudentPaymentTransactionTable alias) {
        @Cleanup var q = dsl
                .select(
                        alias.asterisk()
                );
        return q.from(alias);
    }

    public SelectJoinStep<Record> getQueryForApiList(
            StudentPaymentTransactionTable alias,
            DbEducatorCalendar dbEducatorCalendar,
            DbUser dbUser
    ) {
        var user = dbUser.getTable().as(combineAlias(alias, dbUser.getTable()));
        var userQ = dbUser.getQuery(user);
        var calendar = dbEducatorCalendar.getTable().as(combineAlias(alias, dbEducatorCalendar.getTable()));
        var calendarQ = dbEducatorCalendar.getQuerySimple(calendar);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                userQ.where(alias.TRANSACTION_PERSONNEL_REF_ID.eq(user.ID))
                        ).as(ResultForList.Fields.educator).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                calendarQ.where(alias.TRANSACTION_ITEM_REF_ID.eq(calendar.ID))
                        ).as(ResultForList.Fields.calendar).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(StudentPaymentTransactionTable table) {
        return DSL.noCondition();
    }

    public Condition isValidToPay(StudentPaymentTransactionTable table) {
        var now = OffsetDateTime.now(ZoneOffset.UTC);
        return table.COD_PAYMENT_URL.isNull()
                .or(table.COD_EXPIRY.gt(now));
    }

    public boolean isValidToPay(StudentPaymentTransactionPojo transaction) {
        var now = OffsetDateTime.now(ZoneOffset.UTC);
        // additional 2 minutes for marginal payment process (i.e. pay when almost expired) & time lag of COD's api
        return transaction.getCodExpiry() != null && transaction.getCodExpiry().plusMinutes(2L).isAfter(now);
    }

    @EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends StudentPaymentTransactionPojo implements Serializable {
        private DbStudentProfile.SimpleResult studentProfile;
        private DbEducatorCalendar.SimpleResult session;
        private DbEvent.SimpleResult event;


    }

    @EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class ResultForList extends StudentPaymentTransactionPojo implements Serializable {
        private DbUser.Result educator;
        private DbEducatorCalendar.Result calendar;
    }
}
