package unid.monoServerApp.database.table.tagging;

import lombok.Cleanup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.tables.TaggingTable;
import unid.jooqMono.model.tables.daos.TaggingDao;
import unid.jooqMono.model.tables.pojos.TaggingPojo;
import unid.monoServerApp.database.Db;

import java.io.Serializable;

@Component
public class DbTagging extends Db<TaggingTable, TaggingDao> {

    @Autowired
    public DbTagging(
            DSLContext dslContext
    ) {
        super(dslContext, Public.PUBLIC.TAGGING, new TaggingDao(dslContext.configuration()));
    }

    @Override
    public SelectJoinStep<Record> getQuery(TaggingTable alias) {
        @Cleanup var q = dsl
                .select(
                        alias.asterisk()
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(TaggingTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
//    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends TaggingPojo implements Serializable {
    }
}
