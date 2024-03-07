package unid.monoServerApp.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DAOImpl;
import org.jooq.impl.TableImpl;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
public abstract class Db<T extends TableImpl<?>, D extends DAOImpl<?, ?, ?>> {
    // for handling table alias being too long
    private static final HashMap<String, String> tableAliases = new HashMap<>();
    @Getter
    protected final DSLContext dsl;
    @Getter
    protected final T table;
    @Getter
    protected final D dao;

    public static <DbRecord, SourceRecord> InsertUpdateDelete<DbRecord, SourceRecord> parentCrud(
            Supplier<DbRecord> dbRecord,
            Supplier<SourceRecord> sourceRecord
    ) {
        return new InsertUpdateDelete<>(new DbSource<>(dbRecord.get(), sourceRecord.get()));
    }

    public static <DbRecord, SourceRecord> void parentListCrud(
            Supplier<List<DbRecord>> dbRecord,
            Supplier<List<SourceRecord>> sourceRecord,
            Function<DbRecord, Object> dbPrimaryKey,
            Function<SourceRecord, Object> sourcePrimaryKey,
            @Nullable Consumer<Object> onUpdateWithPrimaryKey,
            Consumer<InsertUpdateDelete<DbRecord, SourceRecord>> onAction
    ) {
        var dbList = ListUtils.defaultIfNull(dbRecord.get(), new ArrayList<>());
        var sourceList = ListUtils.defaultIfNull(sourceRecord.get(), new ArrayList<>());
        var map = new HashMap<Object, DbSource<DbRecord, SourceRecord>>();
        dbList.forEach(db -> {
            var primaryKey = dbPrimaryKey.apply(db);
            map.put(primaryKey, new DbSource<>(db, null));
        });
        sourceList.forEach(source -> {
            var primaryKey = sourcePrimaryKey.apply(source);
            if (!map.containsKey(primaryKey)) {
                if (onUpdateWithPrimaryKey != null && primaryKey != null) {
                    onUpdateWithPrimaryKey.accept(primaryKey);
                }
                map.put(
                        primaryKey == null ? String.format("_%s", UUID.randomUUID()) : primaryKey,
                        new DbSource<>(null, source)
                );
            } else {
                map.get(primaryKey).setSourceRecord(source);
            }
        });
        map.values().forEach(dbSource -> {
            onAction.accept(new InsertUpdateDelete<>(dbSource));
        });
    }

    public abstract SelectJoinStep<Record> getQuery(T alias);

    public SelectJoinStep<Record> getQuery() {
        return getQuery(getTable());
    }

    public abstract Condition validateCondition(T table);

    protected String combineAlias(TableImpl<?>... tables) {
        var name = Stream.of(tables)
                .map(t -> t.getName())
                .collect(Collectors.joining("_"));
        tableAliases.putIfAbsent(
                name,
                String.format(
                        "%s%s",
                        RandomStringUtils.random(8, true, true),
                        Instant.now().getNano()
                )
        );
        return tableAliases.get(name);
    }

    @RequiredArgsConstructor
    public static class InsertUpdateDelete<DbRecord, SourceRecord> {
        @NotNull
        private final DbSource<DbRecord, SourceRecord> dbSource;
        @Nullable
        private BiConsumer<@NotNull DbRecord, @NotNull SourceRecord> onUpdate;
        @Nullable
        private Consumer<@NotNull DbRecord> onDelete;
        @Nullable
        private Consumer<@NotNull SourceRecord> onInsert;

        public InsertUpdateDelete<DbRecord, SourceRecord> onUpdate(BiConsumer<@NotNull DbRecord, @NotNull SourceRecord> onUpdate) {
            this.onUpdate = onUpdate;
            return this;
        }

        public InsertUpdateDelete<DbRecord, SourceRecord> onDelete(Consumer<@NotNull DbRecord> onDelete) {
            this.onDelete = onDelete;
            return this;
        }

        public InsertUpdateDelete<DbRecord, SourceRecord> onInsert(Consumer<@NotNull SourceRecord> onInsert) {
            this.onInsert = onInsert;
            return this;
        }

        public void execute() {
            if (onUpdate != null && dbSource.getDbRecord() != null && dbSource.getSourceRecord() != null) {
                onUpdate.accept(dbSource.getDbRecord(), dbSource.getSourceRecord());
                return;
            }
            if (onDelete != null && dbSource.getDbRecord() != null && dbSource.getSourceRecord() == null) {
                onDelete.accept(dbSource.getDbRecord());
                return;
            }
            if (onInsert != null && dbSource.getDbRecord() == null && dbSource.getSourceRecord() != null) {
                onInsert.accept(dbSource.getSourceRecord());
            }
        }
    }

    @AllArgsConstructor
    @Data
    public static class DbSource<DbRecord, SourceRecord> {
        @Nullable
        private DbRecord dbRecord;
        @Nullable
        private SourceRecord sourceRecord;
    }
}
