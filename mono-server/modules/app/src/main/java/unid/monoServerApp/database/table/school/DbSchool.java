package unid.monoServerApp.database.table.school;

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
import unid.jooqMono.model.tables.SchoolTable;
import unid.jooqMono.model.tables.daos.SchoolDao;
import unid.jooqMono.model.tables.pojos.SchoolPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.city.DbCity;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;

import java.io.Serializable;

@Component
public class DbSchool extends Db<SchoolTable, SchoolDao> {
    private final DbI18N dbI18NQuery;
    private final DbTag dbTag;
    private final DbCity dbCity;

    @Autowired
    public DbSchool(
            DSLContext dslContext,
            DbI18N dbI18NQuery,
            DbTag dbTag,
            DbCity dbCity
    ) {
        super(dslContext, Public.PUBLIC.SCHOOL, new SchoolDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
        this.dbTag = dbTag;
        this.dbCity = dbCity;
    }

    @Override
    public SelectJoinStep<Record> getQuery(SchoolTable alias) {
        var nameI18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable().as("name")));
        var nameI18nQ = dbI18NQuery.getQuery(nameI18n);
        var addressI18n = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable().as("address")));
        var addressI18nQ = dbI18NQuery.getQuery(addressI18n);
        var tag = dbTag.getTable().as(combineAlias(alias, dbTag.getTable()));
        var tagQ = dbTag.getQuery(tag);
        var city = dbCity.getTable().as(combineAlias(alias, dbCity.getTable()));
        var cityQ = dbCity.getQuery(city);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                nameI18nQ.where(alias.NAME_I18N_ID.eq(nameI18n.ID))
                        ).as(DbSchool.Result.Fields.nameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                tagQ.where(alias.TAG_ID.eq(tag.ID))
                        ).as(DbSchool.Result.Fields.tag).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                cityQ.where(alias.CITY_ID.eq(city.ID))
                        ).as(Result.Fields.city).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                addressI18nQ.where(alias.DETAILED_ADDRESS_I18N_ID.eq(addressI18n.ID))
                        ).as(Result.Fields.detailedAddressI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(SchoolTable table) {
        return DSL.noCondition();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends SchoolPojo implements Serializable {
        private DbI18N.Result nameI18n;
        private DbTag.Result tag;
        private DbCity.Result city;
        private DbI18N.Result detailedAddressI18n;
    }
}
