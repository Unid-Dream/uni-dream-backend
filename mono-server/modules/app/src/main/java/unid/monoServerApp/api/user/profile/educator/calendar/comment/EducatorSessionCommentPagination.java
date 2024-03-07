package unid.monoServerApp.api.user.profile.educator.calendar.comment;

import pwh.coreRsqlJooq.rsql.ConditionOperators;
import pwh.coreRsqlJooq.rsql.ConditionsVisitor;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.coreRsqlJooq.rsql.PaginationParser;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.EducatorCalendarTable;
import unid.monoServerApp.Constant;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

public class EducatorSessionCommentPagination {
    public static EducatorCalendarTable TABLE = Public.PUBLIC.EDUCATOR_CALENDAR;

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
                m.put(TABLE.DATE, b ->
                        b.onCondition(x -> {
                            x.put(
                                    ConditionOperators.GREATER_THAN_EQUALS, input -> {
                                        var theDate = LocalDate.parse(input.get(0));
                                        var maxFuture = theDate.plusDays(Constant.PAGINATION_MAX_SIZE_EDUCATOR_CALENDAR);
                                        return TABLE.DATE.ge(theDate)
                                                .and(
                                                        TABLE.DATE.le(maxFuture)
                                                );
                                    }
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
