package unid.monoServerApp.api.auth.register;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwh.springStarter.service.ValidationService;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;
import unid.jooqMono.model.tables.pojos.StudentProfilePojo;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerApp.Properties;
import unid.monoServerApp.cache.auth.register.NewRegisterUser;
import unid.monoServerApp.cache.auth.register.NewRegisterUserRepository;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.mapper.UserMapper;
import unid.monoServerApp.util.OtpUtils;
import unid.monoServerMeta.api.AuthRegisterRequest;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthRegisterService {
    private final DbUser dbUser;
    private final UserMapper userMapper;
    private final NewRegisterUserRepository newRegisterUserRepository;
    private final ValidationService validationService;
    private final Properties properties;
    private final DbStudentProfile dbStudentProfile;
    private final DbEducatorProfile dbEducatorProfile;

    Optional<UserPojo> getExistRecordFromDb(@NotNull AuthRegisterRequest payload) {
        var userTable = dbUser.getTable();
        @Cleanup var clause = dbUser.getQuery(userTable)
                .where(
                        userTable.EMAIL.equalIgnoreCase(payload.getEmail())
                                .or(userTable.LOGIN_ID.equalIgnoreCase(payload.getEmail()))
                );
        return Optional.ofNullable(clause.fetchOneInto(UserPojo.class));
    }

    Optional<NewRegisterUser> getExistRecordFromCache(@NotNull AuthRegisterRequest payload) {
        return Optional.ofNullable(
                newRegisterUserRepository
                        .findByEmailIgnoreCase(payload.getEmail())
        );
    }

    NewRegisterUser createTempNewUser(
            @NotNull UserRoleEnum userRole,
            @NotNull AuthRegisterRequest payload
    ) {
        var record = NewRegisterUser.builder()
                .id(UUID.randomUUID())
                .otp(OtpUtils.generate())
                .otpIssuedAtUtc(OffsetDateTime.now().toInstant())
                .maxResendOtpTimes(properties.getRegisterUserOtpResendMaxRetires())
                .resentOtpTimes(0)
                .request(payload)
                .email(payload.getEmail())
                .userRole(userRole)
                .ttl(properties.getRegisterUserTimeoutMinutes())
                .build();
        validationService.validate(record);
        return newRegisterUserRepository.save(record);
    }

    NewRegisterUser refreshTempUser(NewRegisterUser user) {
        user.setOtp(OtpUtils.generate());
        user.setResentOtpTimes(user.getResentOtpTimes() + 1);
        user.setOtpIssuedAtUtc(OffsetDateTime.now().toInstant());
        user.setTtl(properties.getRegisterUserTimeoutMinutes());
        return newRegisterUserRepository.save(user);
    }

    UserPojo createNewUser(
            @NotNull AuthRegisterRequest payload
    ) {
        var user = userMapper.toPojo(payload)
                .setLoginId(payload.getEmail())
                .setEmailVerified(true);
        dbUser.getDao().insert(user);
        //根据角色创建对应的角色表
        if(user.getUserRole().equals(UserRoleEnum.STUDENT)){
            dbStudentProfile.getDao().insert(
                    new StudentProfilePojo().setUserId(user.getId())
            );
        }else if (user.getUserRole().equals(UserRoleEnum.EDUCATOR)){
            dbEducatorProfile.getDao().insert(
                    new EducatorProfilePojo().setUserId(user.getId())
            );
        }
        log.info("Inserted User: {}", user);
        return user;
    }
}
