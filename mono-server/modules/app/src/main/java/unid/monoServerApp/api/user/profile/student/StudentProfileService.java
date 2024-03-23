package unid.monoServerApp.api.user.profile.student;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.StudentProfilePojo;
import unid.jooqMono.model.tables.pojos.StudentProfileSchoolReportPojo;
import unid.jooqMono.model.tables.pojos.UserPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.cache.CacheTags;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.service.UserCacheService;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfileSchoolReport;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.SchoolMapper;
import unid.monoServerApp.mapper.StudentProfileMapper;
import unid.monoServerApp.service.S3Service;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.FileUploadPath;
import unid.monoServerMeta.model.I18n;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.any;
import static org.jooq.impl.DSL.count;
import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class StudentProfileService {
    private final DbStudentProfile dbStudentProfile;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;
    private final SessionService sessionService;
    private final StudentProfileMapper studentProfileMapper;
    private final DbStudentProfileSchoolReport dbStudentProfileSchoolReport;
    private final DbUser dbUser;
    private final S3Service s3Service;
    private final UserCacheService userCacheService;
    private final DbSchool dbSchool;
    private final SchoolMapper schoolMapper;

    DbStudentProfile.Result get(UUID userId) {
        // TODO ACL (student: get self only, admin: get all)
        return userCacheService.getStudentProfileByUserId(userId)
                .orElseThrow(() -> Exceptions.notFound("Profile Not Found"));
    }

    DbStudentProfile.Result getByProfileId(UUID profileId) {
        // TODO ACL (student: get self only, admin: get all)
        return userCacheService.getStudentProfileByProfileId(profileId)
                .orElseThrow(() -> Exceptions.notFound("Profile Not Found"));
    }

    @CacheEvict(
            value = CacheTags.STUDENT_PROFILE,
            key = "#userId"
    )
    public StudentProfilePojo create(UUID userId, StudentProfileRequest payload) {
        sessionService.initDatabaseSession();
        var profilePojo = studentProfileMapper.toPojo(payload)
                .setUserId(userId);
        dbStudentProfile.getDao().insert(profilePojo);
        updateUser(userId, payload);
        insertOrUpdateSchoolReports(
                profilePojo.getId(),
                Optional.ofNullable(payload.getStudentProfileSchoolReports())
                        .orElse(new ArrayList<>())
        );
        s3Service.tempToPermanent(profilePojo.getProfilePicture());
        return profilePojo;
    }

//    @CacheEvict(
//            value = CacheTags.STUDENT_PROFILE,
//            key = "#userId"
//    )
    public StudentProfilePojo update(UUID userId, StudentProfileRequest payload) {
        sessionService.initDatabaseSession();
        var existingRecord = Optional.ofNullable(
                dbStudentProfile.getDao().fetchOneByUserId(userId)
        ).orElseThrow(() -> Exceptions.notFound("Profile Not Found"));
        //更新student reports
        List<StudentProfileSchoolReportPayload> studentProfileSchoolReportPayloads = Optional.ofNullable(payload.getStudentProfileSchoolReports())
                .orElseGet(Lists::newArrayList);
        for(StudentProfileSchoolReportPayload report : studentProfileSchoolReportPayloads){
            Optional<DbStudentProfileSchoolReport.Result> exist = dbStudentProfile.getDsl().select()
                    .from(STUDENT_PROFILE_SCHOOL_REPORT)
                    .where(STUDENT_PROFILE_SCHOOL_REPORT.STUDENT_PROFILE_ID.eq(existingRecord.getId())
                            .and(STUDENT_PROFILE_SCHOOL_REPORT.SECONDARY_SCHOOL_REPORT_ACADEMIC_YEAR.eq(report.getSecondarySchoolReportAcademicYear())
                                    .and(STUDENT_PROFILE_SCHOOL_REPORT.SECONDARY_SCHOOL_REPORT_SEMESTER.eq(report.getSecondarySchoolReportSemester()))))
                    .fetchOptionalInto(DbStudentProfileSchoolReport.Result.class);
            if(exist.isPresent()){
                //替换s3上的文件
                s3Service.deleteObject(FileUploadPath.SCHOOL_REPORT,report.getSecondarySchoolReport());
                //更新
                DbStudentProfileSchoolReport.Result updatedReport =  exist.get();
                updatedReport.setStudentProfileId(existingRecord.getId());
                updatedReport.setSecondarySchoolReport(report.getSecondarySchoolReport());
                dbStudentProfileSchoolReport.getDao().update(updatedReport);
            }else{
                //如果没有,则新增
                StudentProfileSchoolReportPojo createdReport = studentProfileMapper.toPojo(report);
                createdReport.setStudentProfileId(existingRecord.getId());
                dbStudentProfileSchoolReport.getDao().insert(createdReport);
            }
        }
        //当report为空当时候,则删除所有当report
        if(payload.getStudentProfileSchoolReports().isEmpty()){
            dbStudentProfileSchoolReport.getDsl()
                    .deleteFrom(STUDENT_PROFILE_SCHOOL_REPORT)
                    .where(STUDENT_PROFILE_SCHOOL_REPORT.STUDENT_PROFILE_ID.eq(existingRecord.getId()))
                    .execute();
        }
        //如果新旧文件不一致，则删除历史文件
        if(payload.getProfilePicture()!=null && !payload.getProfilePicture().equals(existingRecord.getProfilePicture())){
            s3Service.deleteObject(FileUploadPath.PROFILE_PICTURE,existingRecord.getProfilePicture());
        }
        //更新倾向的university
        studentProfileMapper.merge(existingRecord, payload);
        dbStudentProfile.getDao().update(existingRecord);
        //更新firstName & lastName
        updateUser(userId, payload);
        return existingRecord;
    }

    private void updateUser(UUID userId, StudentProfileRequest payload) {
        var user = Optional.ofNullable(dbUser.getDao().fetchOneById(userId))
                .orElseThrow(() -> Exceptions.notFound("User Not Found"));
        var firstName = i18nMapper.toPojo(payload.getFirstNameI18n());
        Optional.ofNullable(user.getFistNameI18nId())
                .ifPresentOrElse(id -> {
                    firstName.setId(id);
                    dbI18N.getDao().update(firstName);
                }, () -> {
                    dbI18N.getDao().insert(firstName);
                });
        user.setFistNameI18nId(firstName.getId());
        var lastName = i18nMapper.toPojo(payload.getLastNameI18n());
        Optional.ofNullable(user.getLastNameI18nId())
                .ifPresentOrElse(id -> {
                    lastName.setId(id);
                    dbI18N.getDao().update(lastName);
                }, () -> {
                    dbI18N.getDao().insert(lastName);
                });
        user.setLastNameI18nId(lastName.getId());
        dbUser.getDao().update(user);
    }

    private void updateUser(UUID userId, StudentProfileSchoolReportRequest payload) {
        var user = Optional.ofNullable(dbUser.getDao().fetchOneById(userId))
                .orElseThrow(() -> Exceptions.notFound("User Not Found"));
        var student = Optional.ofNullable(dbStudentProfile.getDao().fetchOneById(user.getId()))
                .orElseThrow(() -> Exceptions.notFound("Student Profile Not Found"));






//        var firstName = i18nMapper.toPojo(payload.getFirstName());
//        Optional.ofNullable(user.getFistNameI18nId())
//                .ifPresentOrElse(id -> {
//                    firstName.setId(id);
//                    dbI18N.getDao().update(firstName);
//                }, () -> {
//                    dbI18N.getDao().insert(firstName);
//                });
//        user.setFistNameI18nId(firstName.getId());
//        var lastName = i18nMapper.toPojo(payload.getLastName());
//        Optional.ofNullable(user.getLastNameI18nId())
//                .ifPresentOrElse(id -> {
//                    lastName.setId(id);
//                    dbI18N.getDao().update(lastName);
//                }, () -> {
//                    dbI18N.getDao().insert(lastName);
//                });
//        user.setLastNameI18nId(lastName.getId());
//        dbUser.getDao().update(user);
    }

    private void insertOrUpdateSchoolReports(
            UUID profileId,
            @NotNull
            List<StudentProfileSchoolReportPayload> payloads
    ) {
        var table = dbStudentProfileSchoolReport.getTable();
        var existingRecords = dbStudentProfileSchoolReport.getQuery(table)
                .where(table.STUDENT_PROFILE_ID.eq(profileId))
                .fetchInto(DbStudentProfileSchoolReport.Result.class);
        Db.parentListCrud(
                () -> existingRecords,
                () -> payloads,
                StudentProfileSchoolReportPojo::getId,
                StudentProfileSchoolReportPayload::getId,
                primaryKey -> Exceptions.notFound("No Such Report", primaryKey.toString()),
                crud -> crud
                        .onUpdate((db, source) -> {
                            var reportPojo = studentProfileMapper.toPojo(source)
                                    .setStudentProfileId(profileId);
                            dbStudentProfileSchoolReport.getDao().update(reportPojo.setId(db.getId()));
                            s3Service.oldToNew(db.getSecondarySchoolReport(), source.getSecondarySchoolReport());
                        })
                        .onDelete((db) -> {
                            dbStudentProfileSchoolReport.getDao().deleteById(db.getId());
                            s3Service.deleteObject(FileUploadPath.SCHOOL_REPORT,db.getSecondarySchoolReport());
                        })
                        .onInsert((source) -> {
                            var reportPojo = studentProfileMapper.toPojo(source)
                                    .setStudentProfileId(profileId);
                            dbStudentProfileSchoolReport.getDao().insert(reportPojo);
                            s3Service.tempToPermanent(source.getSecondarySchoolReport());
                        })
                        .execute()
        );
    }

    public void fill(DbStudentProfile.Result result, StudentProfileResponse response) {
        //Preferred Other University
        //FirstName
        I18n firstNameI18n = dbStudentProfile.getDsl().select().from(I18N)
                .where(I18N.ID.eq(result.getUser().getFistNameI18nId()))
                .fetchOneInto(I18n.class);
        response.setFirstNameI18n(firstNameI18n);
        //LastName
        I18n lastNameI18n = dbStudentProfile.getDsl().select().from(I18N)
                .where(I18N.ID.eq(result.getUser().getLastNameI18nId()))
                .fetchOneInto(I18n.class);
        response.setLastNameI18n(lastNameI18n);
        //查询user邮箱
        UserPojo user = dbUser.getDao().fetchOneById(result.getUserId());
        if(user != null && user.getEmail() != null){
            response.setEmail(user.getEmail());
        }
    }

    public StudentProfilePojo deleteSchoolReport(UUID userId, UUID schoolReportId) {
        StudentProfileSchoolReportPojo report = dbStudentProfileSchoolReport.getDao().fetchOneById(schoolReportId);
        assert report != null;
        s3Service.deleteObject(FileUploadPath.SCHOOL_REPORT,report.getSecondarySchoolReport());
        dbStudentProfileSchoolReport.getDao().deleteById(schoolReportId);

        return Optional.ofNullable(
                dbStudentProfile.getDao().fetchOneByUserId(userId)
        ).orElseThrow(() -> Exceptions.notFound("Profile Not Found"));
    }


    public UniPageResponse<StudentProfilePayload> page(StudentProfilePageRequest request) {
        var table = dbStudentProfile.getTable();
        List<StudentProfilePayload> payload = dbStudentProfile.getDsl()
                .select(
                        table.asterisk(),
                        USER.EMAIL.as(StudentProfilePayload.Fields.email),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(USER.FIST_NAME_I18N_ID))
                        ).as(StudentProfilePayload.Fields.firstNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class)),
                        DSL.multiset(
                                DSL.selectFrom(I18N).where(I18N.ID.eq(USER.LAST_NAME_I18N_ID))
                        ).as(StudentProfilePayload.Fields.lastNameI18n).convertFrom(r->r.isEmpty()?null:r.get(0).into(I18n.class))
                )
                .select(count().over().as(StudentProfilePayload.Fields.total))
                .from(table,USER)
                .where(table.USER_ID.eq(USER.ID))
                .orderBy(table.CREATED_ON.desc())
                .limit(request.getPageSize())
                .offset((request.getPageNumber() - 1) * request.getPageSize())
                .fetchInto(StudentProfilePayload.class);

        int totalSize = payload.stream()
                .findFirst()
                .map(StudentProfilePayload::getTotal)
                .orElse(0);

        return new UniPageResponse<>(
                totalSize,
                request.getPageNumber(),
                request.getPageSize(),
                null,
                payload
        );
    }
}
