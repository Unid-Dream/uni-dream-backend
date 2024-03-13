package unid.monoServerApp.database.table.user;

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
import unid.jooqMono.model.tables.UserTable;
import unid.jooqMono.model.tables.daos.UserDao;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;

@Component
public class DbUser extends Db<UserTable, UserDao> {
    private final DbI18N dbI18NQuery;

    @Autowired
    public DbUser(
            DSLContext dslContext,
            DbI18N dbI18NQuery
    ) {
        super(dslContext, Public.PUBLIC.USER, new UserDao(dslContext.configuration()));
        this.dbI18NQuery = dbI18NQuery;
    }

    @Override
    public SelectJoinStep<Record> getQuery(UserTable alias) {
        var firstName = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable().as("firstName")));
        var firstNameQ = dbI18NQuery.getQuery(firstName);
        var lastName = dbI18NQuery.getTable().as(combineAlias(alias, dbI18NQuery.getTable().as("lastName")));
        var lastNameQ = dbI18NQuery.getQuery(lastName);
        @Cleanup var q = dsl
                .select(
                        alias.asterisk(),
                        DSL.multiset(
                                firstNameQ.where(alias.FIST_NAME_I18N_ID.eq(firstName.ID))
                        ).as(DbUser.Result.Fields.firstNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0)),
                        DSL.multiset(
                                lastNameQ.where(alias.LAST_NAME_I18N_ID.eq(lastName.ID))
                        ).as(DbUser.Result.Fields.lastNameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0))
                );
        return q.from(alias);
    }

    @Override
    public Condition validateCondition(UserTable table) {
        return table.DELETED.eq(false).or(table.DELETED.isNull());
    }

    @Builder
    public static class Parent {
        private boolean name;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends UserPojo implements Serializable {
        private DbI18N.Result firstNameI18n;
        private DbI18N.Result lastNameI18n;

    }
}
