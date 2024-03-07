package unid.monoServerApp.database.table.city;

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
import unid.jooqMono.model.tables.CityTable;
import unid.jooqMono.model.tables.daos.CityDao;
import unid.jooqMono.model.tables.pojos.CityPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;

@Component
public class DbCity extends Db<CityTable, CityDao> {
    private final DbI18N dbI18NQuery;
    private final DbCountry dbCountry;

    @Autowired
    public DbCity(
            DSLContext dslContext,
            DbI18N dbI18NQuery,
            DbCountry dbCountry
    ) {
        super(dslContext, Public.PUBLIC.CITY, new CityDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
        this.dbCountry = dbCountry;
    }

    @Override
    public SelectJoinStep<Record> getQuery(CityTable alias) {
        var i18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable()));
        var i18nQ = dbI18NQuery.getQuery(i18n);
        var country = dbCountry.getTable().as(combineAlias(alias, dbCountry.getTable()));
        var countryQ = dbCountry.getQuery(country);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                i18nQ.where(alias.NAME_I18N_ID.eq(i18n.ID))
                        ).as(Result.Fields.nameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                countryQ.where(alias.COUNTRY_ID.eq(country.ID))
                        ).as(Result.Fields.country).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(CityTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends CityPojo implements Serializable {
        private DbCountry.Result country;
        private DbI18N.Result nameI18n;
    }
}
