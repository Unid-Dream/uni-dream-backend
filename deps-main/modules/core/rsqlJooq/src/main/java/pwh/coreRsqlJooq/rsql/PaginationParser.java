package pwh.coreRsqlJooq.rsql;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.UnknownOperatorException;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.Node;
import org.jooq.Field;
import pwh.coreRsqlJooq.Constant;
import pwh.coreStarter.exception.UnifiedException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PaginationParser {

    private static Node parse(RSQLParser parser, String ql) {
        try {
            return parser.parse(ql);
        } catch (RSQLParserException rpe) {
            var error = rpe.getCause();
            if (error instanceof UnknownOperatorException) {
                throw new UnifiedException(
                        Constant.ERROR_UNSUPPORTED_OPERATION,
                        String.format("Unsupported Operator: %s", ((UnknownOperatorException) error).getOperator()),
                        400,
                        rpe
                );
            } else {
                throw new UnifiedException(
                        Constant.ERROR_INVALID_SYNTAX,
                        "INVALID SYNTAX",
                        400,
                        rpe
                );
            }
        }
    }

    public static Node parseQuery(String query) {
        var operators = new HashSet<ComparisonOperator>();
        for (var operator : ConditionOperators.class.getEnumConstants()) {
            operators.add(new ComparisonOperator(operator.toNamedString(), true));
        }
        return parse(new RSQLParser(operators), query);
    }

    public static Node parseOrder(String ordering) {
        var operators = new HashSet<ComparisonOperator>();
        for (var operator : OrderingOperators.class.getEnumConstants()) {
            operators.add(new ComparisonOperator(operator.toNamedString(), true));
        }
        return parse(new RSQLParser(operators), ordering);
    }

    public static ConditionsVisitor conditionVisitor(
            Consumer<HashMap<Field<?>, Function<ConditionsVisitor.ConditionX.ConditionXBuilder, ConditionsVisitor.ConditionX.ConditionXBuilder>>> operations
    ) {
        var operationMap = new HashMap<Field<?>, Function<ConditionsVisitor.ConditionX.ConditionXBuilder, ConditionsVisitor.ConditionX.ConditionXBuilder>>();
        operations.accept(operationMap);
        var map = operationMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> {
                    var builder = ConditionsVisitor.ConditionX.builder();
                    e.getValue().apply(builder);
                    return builder.build();
                }));
        return new ConditionsVisitor(new HashMap<>(map));
    }

    public static OrderingVisitor orderingVisitor(
            Consumer<HashMap<Field<?>, Function<OrderingVisitor.Seeking.SeekingBuilder, OrderingVisitor.Seeking.SeekingBuilder>>> operations
    ) {
        var operationMap = new HashMap<Field<?>, Function<OrderingVisitor.Seeking.SeekingBuilder, OrderingVisitor.Seeking.SeekingBuilder>>();
        operations.accept(operationMap);
        var map = operationMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().apply(OrderingVisitor.Seeking.builder()).build()
        ));
        return new OrderingVisitor(new HashMap<>(map));
    }
}
