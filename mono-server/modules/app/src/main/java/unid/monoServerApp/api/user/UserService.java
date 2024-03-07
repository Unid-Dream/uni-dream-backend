package unid.monoServerApp.api.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.UserMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.UserRequest;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserService {
    private final DbUser dbUser;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final UserMapper userMapper;

    DbUser.Result get(UUID id) {
        var table = dbUser.getTable();
        return dbUser.getQuery(table)
                .where(table.ID.eq(id).and(dbUser.validateCondition(table)))
                .fetchOptional().orElseThrow(() -> Exceptions.notFound("User Not Found"))
                .into(DbUser.Result.class);
    }

    UserPojo create(UserRequest payload) {
        sessionService.initDatabaseSession();
        var firstName = i18nMapper.toPojo(payload.getFistNameI18n());
        dbI18N.getDao().insert(firstName);
        var lastName = i18nMapper.toPojo(payload.getLastNameI18n());
        dbI18N.getDao().insert(lastName);
        var pojo = new UserPojo()
                .setFistNameI18nId(firstName.getId())
                .setLastNameI18nId(lastName.getId());
        dbUser.getDao().insert(pojo);
        return pojo;
    }

    UserPojo update(UUID id, UserRequest payload) {
        sessionService.initDatabaseSession();
        var user = get(id);
        var firstNameI18n = Optional.ofNullable(dbI18N.getDao().fetchOneById(user.getFistNameI18nId()))
                .orElseThrow(() -> Exceptions.notFound("First Name Not Found"));
        var lastNameI18n = Optional.ofNullable(dbI18N.getDao().fetchOneById(user.getLastNameI18nId()))
                .orElseThrow(() -> Exceptions.notFound("Last Name Not Found"));
        i18nMapper.merge(firstNameI18n, payload.getFistNameI18n());
        dbI18N.getDao().update(firstNameI18n);
        i18nMapper.merge(lastNameI18n, payload.getLastNameI18n());
        dbI18N.getDao().update(lastNameI18n);
        userMapper.merge(user, payload);
        dbUser.getDao().update(user);
        return user;
    }
}
