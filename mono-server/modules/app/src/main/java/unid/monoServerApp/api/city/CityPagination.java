package unid.monoServerApp.api.city;

import pwh.coreRsqlJooq.rsql.ConditionOperators;
import pwh.coreRsqlJooq.rsql.ConditionsVisitor;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.coreRsqlJooq.rsql.PaginationParser;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.CityTable;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

public class CityPagination {
    public static CityTable TABLE = Public.PUBLIC.CITY;

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
