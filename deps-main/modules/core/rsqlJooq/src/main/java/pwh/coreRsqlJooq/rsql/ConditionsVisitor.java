package pwh.coreRsqlJooq.rsql;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.DSL;
import pwh.coreRsqlJooq.Constant;
import pwh.coreStarter.exception.UnifiedException;
import pwh.coreStarter.type.NamedEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class ConditionsVisitor implements RSQLVisitor<Condition, Void> {
    private final HashMap<Field<?>, ConditionX> operations;
    private Condition condition = DSL.noCondition();

    private Optional<Field<?>> getOperationField(String name) {
        var fields = operations.entrySet().stream()
                .filter(k -> {
                    if (StringUtils.isNotBlank(k.getValue().queryAlias)) {
                        return k.getValue().queryAlias.equals(name);
                    }
                    return k.getKey().getName().equals(name);
                })
                .collect(Collectors.toList());
        return fields.isEmpty() ? Optional.empty() : Optional.of(fields.get(0).getKey());
    }

    private HashMap<ConditionOperators, Function<List<String>, Condition>> getOperationFunction(Field<?> field) {
        var map = new HashMap<ConditionOperators, Function<List<String>, Condition>>();
        operations.entrySet().stream()
                .filter(k -> k.getKey().equals(field))
                .collect(Collectors.toList()).get(0).getValue().onCondition.accept(map);
        return map;
    }

    public void init() {
        condition = DSL.noCondition();
    }

    @Override
    public Condition visit(AndNode andNode, Void unused) {
        var cond = DSL.noCondition();
        for (var node : andNode.getChildren()) {
            cond = cond.and(node.accept(this));
        }
        return condition.and(cond);
    }

    @Override
    public Condition visit(OrNode orNode, Void unused) {
        var cond = DSL.noCondition();
        for (var node : orNode.getChildren()) {
            cond = cond.or(node.accept(this));
        }
        return condition.or(cond);
    }

    @Override
    public Condition visit(ComparisonNode comparisonNode, Void unused) {
        var fieldName = comparisonNode.getSelector();
        var operator = NamedEnum.get(ConditionOperators.class, comparisonNode.getOperator().getSymbol());
        var args = comparisonNode.getArguments();

        var field = getOperationField(fieldName);
        if (field.isEmpty()) {
            throw new UnifiedException(
                    Constant.ERROR_UNSUPPORTED_FIELD,
                    String.format("Unsupported Field: %s", fieldName),
                    400
            );
        }

        var func = getOperationFunction(field.get()).get(operator);
        if (func == null) {
            throw new UnifiedException(
                    Constant.ERROR_UNSUPPORTED_OPERATION,
                    String.format("Unsupported Operation %s For Field: %s", operator, fieldName),
                    400
            );
        }

        return func.apply(args);
    }

    @Builder
    public static class ConditionX {
        private String queryAlias;

        private Consumer<HashMap<ConditionOperators, Function<List<String>, Condition>>> onCondition;
    }
}
