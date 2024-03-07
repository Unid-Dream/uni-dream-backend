package unid.monoServerApp.service;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwh.springStarter.service.ValidationService;
import unid.jooqMono.model.tables.pojos.EducatorProfilePojo;
import unid.jooqMono.model.tables.pojos.StudentProfilePojo;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerApp.Properties;
import unid.monoServerApp.database.service.UserCacheService;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.JwtTokenClaimsMapper;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.JwtTokenClaims;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtTokenService {
    private final Properties properties;
    private final ObjectMapper objectMapper;
    private final JwtTokenClaimsMapper jwtTokenClaimsMapper;
    private final UserCacheService userCacheService;
    private final ValidationService validationService;
    private final JwtSecretService jwtSecretService;
    private final I18nMapper i18nMapper;


    public String generateTokenForUser(UserPojo userPojo) {
        var expiry = OffsetDateTime.now().plus(
                Duration.ofMinutes(properties.getJwtExpiryMinutes())
        );
        var claims = jwtTokenClaimsMapper.toClaims(userPojo);
        var userId = userPojo.getId();
//        var userProfile = userCacheService.getEducatorProfileByUserId(userId);
//        StaticLog.info("用户基本信息：{}",JSONUtil.toJsonStr(userProfile.get()));
        claims.setUserProfileId(
                userCacheService.getStudentProfileByUserId(userId)
                        .map(StudentProfilePojo::getId)
                        .or(() -> userCacheService.getEducatorProfileByUserId(userId)
                                .map(EducatorProfilePojo::getId))
                        .orElse(null)
        );
        DbStudentProfile.Result profile = userCacheService.getStudentProfile(userPojo);
        I18n firstNameI18n;
        if(profile.getUser().getFirstNameI18n() == null){
            firstNameI18n = new I18n();
            firstNameI18n.setEnglish(profile.getUser().getEmail());
        }else{
            firstNameI18n = i18nMapper.toModel(profile.getUser().getFirstNameI18n());
        }

        claims.setFirstNameI18n(
                firstNameI18n
        );
        claims.setLastNameI18n(
                i18nMapper.toModel(profile.getUser().getLastNameI18n())
        );
        claims.setProfilePicture(profile.getProfilePicture());
        claims.setEmail(userPojo.getEmail());
        validationService.validate(claims);
        var token = Jwts.builder()
                .setId(String.format("%s-%s", UUID.randomUUID(), UUID.randomUUID()))
                .setSubject("User Token")
                .setIssuedAt(new Date())
                .setIssuer(properties.getJwtIssuer())
                .setExpiration(Date.from(expiry.toInstant()))
                .addClaims(objectMapper.convertValue(claims, new TypeReference<>() {
                }))
                // signature
                .signWith(SignatureAlgorithm.HS256, jwtSecretService.generateRandomSecret(userId))
                .compact();
        return token;
    }

    public boolean validateToken(String token, JwtTokenClaims claimsObject) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecretService.getSecret(claimsObject.getUserId()))
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Error while validating JWT Token: {}", token, e);
        }
        return false;
    }

    public Optional<Claims> getClaims(String token) {
        try {
            var claims = Jwts.parser()
                    .parseClaimsJwt(token.substring(0, token.lastIndexOf('.') + 1));
            return Optional.of(claims.getBody());
        } catch (Exception e) {
            log.error("Error while parsing JWT Token: {}", token, e);
        }
        return Optional.empty();
    }

    public Optional<JwtTokenClaims> getClaimsObject(String token) {
        var claims = getClaims(token);
        if (claims.isPresent()) {
            var payload = objectMapper.convertValue(
                    claims.get(),
                    new TypeReference<JwtTokenClaims>() {
                    }
            );
            return Optional.of(payload);
        }
        return Optional.empty();
    }
}
