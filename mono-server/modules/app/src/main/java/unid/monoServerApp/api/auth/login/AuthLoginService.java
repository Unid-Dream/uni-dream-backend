package unid.monoServerApp.api.auth.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwh.coreJooqLibs.converter.HashingConverter;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerMeta.api.AuthLoginRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthLoginService {
    private final DbUser dbUser;

    Optional<UserPojo> getExistUser(AuthLoginRequest authLoginRequest) {
        var table = dbUser.getTable();
        return Optional.ofNullable(
                dbUser.getQuery(table)
                        .where(
                                table.EMAIL.equalIgnoreCase(authLoginRequest.getEmail())
                                        .and(dbUser.validateCondition(table))
                        )
                        .fetchOneInto(UserPojo.class)
        );
    }

    boolean isUserPasswordCorrect(String userPassword, UserPojo userPojo) {
        return HashingConverter.isValid(userPojo.getLoginPassword(), userPassword);
    }
}
