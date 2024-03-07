package unid.monoServerApp.api.user;

import org.apache.commons.lang3.EnumUtils;
import pwh.coreRsqlJooq.rsql.ConditionOperators;
import pwh.coreRsqlJooq.rsql.ConditionsVisitor;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.coreRsqlJooq.rsql.PaginationParser;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.jooqMono.model.tables.UserTable;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserPagination {
    public static UserTable TABLE = Public.PUBLIC.USER;

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
                m.put(TABLE.USER_ROLE.as("userRole"), b ->
                        b.onCondition(x -> {
                            x.put(
                                    ConditionOperators.IN, input ->
                                            TABLE.USER_ROLE.in(input.stream().map(r -> EnumUtils.getEnum(UserRoleEnum.class, r)).collect(Collectors.toList()))
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
