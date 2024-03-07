package pwh.coreRsqlJooq.rsql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Field;
import org.jooq.SortOrder;
import pwh.coreRsqlJooq.Constant;
import pwh.coreStarter.exception.UnifiedException;
import pwh.coreStarter.type.NamedEnum;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class OrderingVisitor implements RSQLVisitor<LinkedHashSet<OrderingVisitor.FieldRef>, Void> {
    @Getter
    private final HashMap<Field<?>, Seeking> operations; // input | <field, (ifSortByValue) -> output
    private LinkedHashSet<FieldRef> sortFieldRef;// output

    private Optional<Field<?>> getOperationField(String name) {
        var fields = operations.entrySet().stream()
                .filter(e -> {
                    if (StringUtils.isNotBlank(e.getValue().queryAlias)) {
                        return e.getValue().queryAlias.equals(name);
                    }
                    return e.getKey().getName().equals(name);
                })
                .collect(Collectors.toList());
        return fields.isEmpty() ? Optional.empty() : Optional.of(fields.get(0).getKey());
    }

    private Seeking getOperationFunction(Field<?> field) {
        return operations.entrySet().stream().filter(k -> k.getKey().equals(field)).collect(Collectors.toList()).get(0).getValue();
    }

    public void init() {
        sortFieldRef = new LinkedHashSet<>();
    }

    @Override
    public LinkedHashSet<FieldRef> visit(AndNode andNode, Void unused) {
        for (var node : andNode.getChildren()) {
            node.accept(this);
        }
        return sortFieldRef;
    }

    @Override
    public LinkedHashSet<FieldRef> visit(OrNode orNode, Void unused) {
        throw new UnifiedException(
                Constant.ERROR_UNSUPPORTED_OPERATION,
                String.format("Unsupported Operation: %s", orNode.getOperator()),
                400
        );
    }

    @Override
    public LinkedHashSet<FieldRef> visit(ComparisonNode comparisonNode, Void unused) {
        var fieldName = comparisonNode.getSelector();
        var operator = NamedEnum.get(OrderingOperators.class, comparisonNode.getOperator().getSymbol());
        var args = comparisonNode.getArguments();

        var field = getOperationField(fieldName);
        if (field.isEmpty()) {
            throw new UnifiedException(
                    Constant.ERROR_UNSUPPORTED_FIELD,
                    String.format("Unsupported Field: %s", fieldName),
                    400
            );
        }

        if (sortFieldRef.stream().anyMatch(ref -> ref.getField().equals(field.get()))) {
            throw new UnifiedException(
                    Constant.ERROR_DUPLICATED_FILED,
                    String.format("Duplicated Field: %s", fieldName),
                    400
            );
        }

        var seeking = getOperationFunction(field.get());
        var sortValues = seeking.sortByValues(args);

        if (CollectionUtils.isNotEmpty(sortValues)) {
            // for example, string to enum failure
            if (sortValues.contains(null)) {
                throw new UnifiedException(
                        Constant.ERROR_INVALID_ARGS,
                        String.format("Invalid Field (%s) Arguments: %s", fieldName, args),
                        400
                );
            }
        }

        sortFieldRef.add(
                new FieldRef(
                        field.get(),
                        OrderingOperators.getJooqSortOrder(operator),
                        sortValues,
                        seeking
                )
        );

        return sortFieldRef;
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    @EqualsAndHashCode
    public static class FieldName {
        @Getter
        private final String name;
        @Getter
        private final String qualifiedName;

        public static FieldName of(Field<?> field) {
            return new FieldName(field.getName(), field.getQualifiedName().toString());
        }
    }

    @Builder
    public static class Seeking {
        private String queryAlias;
        private Function<List<String>, List<Object>> whenSortByValues;
        private Function<String, Object> whenSeeking;

        public List<Object> sortByValues(List<String> input) {
            if (whenSortByValues != null) {
                return whenSortByValues.apply(input);
            }
            return new ArrayList<>();
        }

        public Object seeking(String input) {
            if (whenSeeking != null) {
                return whenSeeking.apply(input);
            }
            return null;
        }
    }

    @RequiredArgsConstructor
    @ToString
    public static class FieldRef {
        @Getter
        private final Field<?> field;
        @Getter
        private final SortOrder sortOrder;
        @Getter
        private final List<Object> seekingRef;
        @Getter
        private final Seeking seeking;

        @JsonIgnore
        public boolean isSortByValues() {
            return ObjectUtils.isNotEmpty(seekingRef);
        }
    }
}
