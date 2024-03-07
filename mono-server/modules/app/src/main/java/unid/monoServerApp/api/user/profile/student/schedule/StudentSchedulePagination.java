package unid.monoServerApp.api.user.profile.student.schedule;

import org.apache.commons.lang3.EnumUtils;
import pwh.coreRsqlJooq.rsql.ConditionOperators;
import pwh.coreRsqlJooq.rsql.ConditionsVisitor;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.coreRsqlJooq.rsql.PaginationParser;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.tables.StudentPaymentTransactionTable;

import java.util.UUID;
import java.util.stream.Collectors;

public class StudentSchedulePagination {
    public static StudentPaymentTransactionTable TABLE = Public.PUBLIC.STUDENT_PAYMENT_TRANSACTION;

    /**
     * Conditions Visitor
     * Defining which condition fields (& operations) that API supports
     * Defining each supported condition & operation Java transformation
     */
    public static ConditionsVisitor conditionVisitor = PaginationParser.conditionVisitor(
            m -> {
                m.put(TABLE.ID, b ->
                        b.onCondition(x -> {
                            x.put(
                                    ConditionOperators.IN, input ->
                                            TABLE.ID.in(input.stream().map(UUID::fromString).collect(Collectors.toList()))
                            );
                        }));
                m.put(TABLE.PAYMENT_STATUS.as("status"), b ->
                        b.onCondition(x -> {
                            x.put(
                                    ConditionOperators.EQUALS, input -> TABLE.PAYMENT_STATUS.eq(EnumUtils.getEnum(PaymentStatusEnum.class, input.get(0)))
                            );
                        }));
            }
    );

    /**
     * Sorting Visitor
     * Defining which sorting fields (& operations) that API supports
     * Defining each supported condition & operation Java transformation
     */
    public static OrderingVisitor orderingVisitor = PaginationParser.orderingVisitor(
            m -> {
//                m.put(TABLE.CREATED_ON, b -> b
//                        .whenSeeking(OffsetDateTime::parse));
            }
    );
}
