package unid.monoServerApp.database.table.educatorProfile;

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
import unid.jooqMono.model.tables.EducatorProfileLanguageMapTable;
import unid.jooqMono.model.tables.daos.EducatorProfileLanguageMapDao;
import unid.jooqMono.model.tables.pojos.EducatorProfileLanguageMapPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.language.DbLanguage;

@Component
public class DbEducatorProfileLanguageMap extends Db<EducatorProfileLanguageMapTable, EducatorProfileLanguageMapDao> {
    private final DbLanguage dbLanguage;
    private final DbEducatorProfile dbEducatorProfile;

    @Autowired
    public DbEducatorProfileLanguageMap(
            DSLContext dslContext,
            DbLanguage dbLanguage,
            DbEducatorProfile dbEducatorProfile) {
        super(dslContext, Public.PUBLIC.EDUCATOR_PROFILE_LANGUAGE_MAP, new EducatorProfileLanguageMapDao(dslContext.configuration()));
        this.dbEducatorProfile = dbEducatorProfile;
        this.dbLanguage = dbLanguage;
    }

    @Override
    public SelectJoinStep<Record> getQuery(EducatorProfileLanguageMapTable alias) {
        var profile = dbEducatorProfile.getTable().as(combineAlias(alias, dbEducatorProfile.getTable()));
        var profileQ = dbEducatorProfile.getQuery(profile);
        var lang = dbLanguage.getTable().as(combineAlias(alias, dbLanguage.getTable()));
        var langQ = dbLanguage.getQuery(lang);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                profileQ.where(alias.EDUCATOR_PROFILE_ID.eq(profile.ID))
                        ).as(Result.Fields.educatorProfile).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        alias.asterisk(),
                        DSL.multiset(
                                langQ.where(alias.LANGUAGE_ID.eq(lang.ID))
                        ).as(Result.Fields.language).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        alias.asterisk()
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(EducatorProfileLanguageMapTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends EducatorProfileLanguageMapPojo {
        private DbEducatorProfile.Result educatorProfile;
        private DbLanguage.Result language;
    }
}
