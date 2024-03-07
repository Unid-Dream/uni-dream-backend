package unid.monoServerApp.api.meeting.comment;

import pwh.coreRsqlJooq.rsql.ConditionOperators;
import pwh.coreRsqlJooq.rsql.ConditionsVisitor;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.coreRsqlJooq.rsql.PaginationParser;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.EducatorSessionNoteTable;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

public class EducatorSessionNotePagination {
    public static EducatorSessionNoteTable TABLE = Public.PUBLIC.EDUCATOR_SESSION_NOTE;

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
                m.put(TABLE.OBSOLETED, b ->
                        b.onCondition(x -> {
                            x.put(
                                    ConditionOperators.EQUALS, input ->
                                            TABLE.OBSOLETED.eq(Boolean.valueOf(input.get(0)))
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
                m.put(TABLE.CREATED_ON, b -> b
                        .whenSeeking(OffsetDateTime::parse));
            }
    );
}
