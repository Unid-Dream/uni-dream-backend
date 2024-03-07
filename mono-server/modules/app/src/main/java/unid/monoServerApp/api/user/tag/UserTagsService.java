package unid.monoServerApp.api.user.tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pwh.coreStarter.exception.UnifiedException;
import pwh.springWebStarter.ErrorCodeGlobal;
import unid.jooqMono.model.enums.TagTargetEnum;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.cache.CacheTags;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.database.table.tagging.DbTagging;
import unid.monoServerApp.database.table.user.DbUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserTagsService {
    private final DbStudentProfile dbStudentProfile;
    private final DbTagging dbTagging;
    private final DbTag dbTag;
    private final DbEducatorProfile dbEducatorProfile;
    private final DbUser dbUser;

    @Cacheable(value = CacheTags.USER_TAGS, key = "#userId")
    public List<DbTag.Result> getTags(UUID userId) {
        var userRole = Optional.ofNullable(
                        dbUser.getDao().fetchOneById(userId)
                ).orElseThrow(() -> Exceptions.notFound("User Not Found"))
                .getUserRole();
        TagTargetEnum tagType = null;
        UUID profileId = null;
        switch (userRole) {
            case STUDENT:
                tagType = TagTargetEnum.STUDENT_PROFILE;
                profileId = Optional.ofNullable(
                                dbStudentProfile.getDao().fetchOneByUserId(userId)
                        ).orElseThrow(() -> Exceptions.notFound("Profile Not Found"))
                        .getId();
                break;
            case EDUCATOR:
                tagType = TagTargetEnum.EDUCATOR_PROFILE;
                profileId = Optional.ofNullable(
                                dbEducatorProfile.getDao().fetchOneByUserId(userId)
                        ).orElseThrow(() -> Exceptions.notFound("Profile Not Found"))
                        .getId();
                break;
            default:
                throw new UnifiedException(
                        ErrorCodeGlobal.ERROR_INVALID_INPUT,
                        "Unknown User Identity",
                        HttpStatus.BAD_REQUEST.value()
                );
        }
        var tagging = dbTagging.getTable().as("tagging");
        var tag = dbTag.getTable().as("tag");
        return dbTag.getQuery(tag)
                .join(
                        tagging
                )
                .on(
                        tag.ID.eq(tagging.TAG_ID)
                                .and(tagging.TARGET.eq(tagType)
                                        .and(tagging.TARGET_ID.eq(profileId)))
                )
                .fetchInto(DbTag.Result.class);
    }
}
