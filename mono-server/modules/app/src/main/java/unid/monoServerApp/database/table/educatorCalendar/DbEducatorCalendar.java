package unid.monoServerApp.database.table.educatorCalendar;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.EducatorCalendarTable;
import unid.jooqMono.model.tables.daos.EducatorCalendarDao;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.monoServerApp.Constant;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.Properties;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.*;

@Component
@Slf4j
public class DbEducatorCalendar extends Db<EducatorCalendarTable, EducatorCalendarDao> {
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final Properties properties;

    @Autowired
    public DbEducatorCalendar(
            DSLContext dslContext,
            DbStudentPaymentTransaction dbStudentPaymentTransaction,
            Properties properties
    ) {
        super(dslContext, Public.PUBLIC.EDUCATOR_CALENDAR, new EducatorCalendarDao(dslContext.configuration()));
        this.dbStudentPaymentTransaction = dbStudentPaymentTransaction;
        this.properties = properties;
    }

    @Override
    public SelectJoinStep<Record> getQuery(EducatorCalendarTable alias) {
        var payTran = dbStudentPaymentTransaction.getTable().as(combineAlias(alias, dbStudentPaymentTransaction.getTable()));
        var payTranQ = dbStudentPaymentTransaction.getQuery(payTran);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                payTranQ.where(alias.PAYMENT_TRANSACTION_ID.eq(payTran.ID))
                        ).as(Result.Fields.paymentTransaction).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        //由预定,则查询学生信息
                        DSL.multiset(
                                DSL.select(

                                ).from(

                                )
                        ).as(Result.Fields.studentProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    public SelectJoinStep<Record> getQuerySimple(EducatorCalendarTable alias) {
        @Cleanup var q = dsl
                .select(
                        alias.asterisk()
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(EducatorCalendarTable table) {
        return DSL.noCondition();
    }

    public Condition timeSlotIsValidToBook(EducatorCalendarTable table) {
        var now = LocalDateTime.now(ZoneOffset.UTC).plusMinutes(properties.getMeetingAllowedToBookBeforeStartMinutes());
        var timeslot = DSL.concat(table.DATE, DSL.val(" "), table.HOUR_START).cast(LocalDateTime.class);
        return timeslot.gt(now);
    }

    public Condition timeSlotIsValidToAccept(EducatorCalendarTable table) {
        var now = LocalDateTime.now(ZoneOffset.UTC).plusMinutes(properties.getMeetingAllowedToAcceptBeforeStartMinutes());
        var timeslot = DSL.concat(table.DATE, DSL.val(" "), table.HOUR_START).cast(LocalDateTime.class);
        return timeslot.gt(now);
    }

    public Condition timeSlotIsValidToPay(EducatorCalendarTable table) {
        var now = LocalDateTime.now(ZoneOffset.UTC).plusMinutes(properties.getMeetingAllowedToPayBeforeStartMinutes());
        var timeslot = DSL.concat(table.DATE, DSL.val(" "), table.HOUR_START).cast(LocalDateTime.class);
        return timeslot.gt(now);
    }

    public Condition scheduleEnded(EducatorCalendarTable table) {
        var now = LocalDateTime.now(ZoneOffset.UTC);
        var timeslot = DSL.concat(table.DATE, DSL.val(" "), table.HOUR_END).cast(LocalDateTime.class);
        return timeslot.lt(now);
    }

    @Deprecated
    public void validateMarking(
            @NotNull LocalDate date,
            @NotNull LocalTime start,
            @NotNull LocalTime end
    ) {
        var now = LocalDate.now(ZoneOffset.UTC);
        var span = start.getHour() - end.getHour();
        if (
                date.isBefore(now)
                        || date.isAfter(date.plusDays(Constant.PAGINATION_MAX_SIZE_EDUCATOR_CALENDAR))
                        || !start.isBefore(end)
                        || span != -1
        ) {
            throw Exceptions.invalidTimeslot();
        }
    }


    public void validateMarking(
            @NotNull OffsetDateTime dateHourStart,
            @NotNull OffsetDateTime dateHourEnd
    ) {
        var now = LocalDate.now(ZoneOffset.UTC);
        var span = dateHourStart.getHour() - dateHourEnd.getHour();
        //默认为同一天
        if (
                dateHourStart.toLocalDate().isBefore(now)
                        || dateHourStart.toLocalDate().isAfter(dateHourStart.toLocalDate().plusDays(Constant.PAGINATION_MAX_SIZE_EDUCATOR_CALENDAR))
                        || !dateHourStart.toLocalTime().isBefore(dateHourEnd.toLocalTime())
                        || span != -1
        ) {
            throw Exceptions.invalidTimeslot();
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends EducatorCalendarPojo implements Serializable {
        private DbStudentPaymentTransaction.Result paymentTransaction;
        private DbStudentProfile.Result studentProfile;
    }
}
