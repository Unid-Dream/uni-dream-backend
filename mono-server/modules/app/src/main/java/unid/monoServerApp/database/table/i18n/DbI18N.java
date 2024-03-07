package unid.monoServerApp.database.table.i18n;

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
import unid.jooqMono.model.tables.I18nTable;
import unid.jooqMono.model.tables.daos.I18nDao;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.monoServerApp.database.Db;

@Component
public class DbI18N extends Db<I18nTable, I18nDao> {

    @Autowired
    public DbI18N(DSLContext dslContext) {
        super(dslContext, Public.PUBLIC.I18N, new I18nDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(I18nTable alias) {
        @Cleanup var q = dsl.select(alias.asterisk());
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(I18nTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends I18nPojo {
    }
}
