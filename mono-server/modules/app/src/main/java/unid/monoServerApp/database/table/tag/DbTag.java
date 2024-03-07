package unid.monoServerApp.database.table.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.TagTable;
import unid.jooqMono.model.tables.daos.TagDao;
import unid.jooqMono.model.tables.pojos.TagPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.UUID;

@Component
public class DbTag extends Db<TagTable, TagDao> {
    private final DbI18N dbI18NQuery;

    @Autowired
    public DbTag(
            DSLContext dslContext,
            DbI18N dbI18NQuery
    ) {
        super(dslContext, Public.PUBLIC.TAG, new TagDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
    }

    @Override
    public SelectJoinStep<Record> getQuery(TagTable alias) {
        var i18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable()));
        var i18nQ = dbI18NQuery.getQuery(i18n);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                i18nQ.where(alias.DESCRIPTION_I18N_ID.eq(i18n.ID))
                        ).as(DbTag.Result.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(TagTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends TagPojo implements Serializable {
        public static final String INTERNAL_IDENTIFIER_A12345_VALUE = "I Am A Tag";

        private DbI18N.Result descriptionI18n;
        @JsonProperty(Fields.INTERNAL_IDENTIFIER_A12345)
        private String INTERNAL_IDENTIFIER_A12345 = INTERNAL_IDENTIFIER_A12345_VALUE;

        @Nonnull
        @Override
        @EqualsAndHashCode.Include
        public UUID getId() {
            return super.getId();
        }
    }
}
