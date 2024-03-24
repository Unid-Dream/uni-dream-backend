package unid.monoServerApp.api.ecaCourse;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.StudentEcaProfileMapPojo;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileOption;
import unid.monoServerApp.database.table.ecaProfile.DbEcaProfileSection;
import unid.monoServerApp.database.table.ecaProfile.DbStudentEcaProfileMap;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.mapper.EcaCourseMapper;
import unid.monoServerApp.mapper.EcaProfileMapper;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerApp.mapper.StudentEcaProfileMapMapper;
import unid.monoServerMeta.api.EcaProfileRequest;
import unid.monoServerMeta.api.EcaProfileSectionResponse;
import unid.monoServerMeta.api.StudentEcaProfileSectionResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.impl.DSL.*;
import static unid.jooqMono.model.Tables.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EcaProfileService {
    private final DSLContext dslContext;
    private final EcaProfileMapper ecaProfileMapper;
    private final DbStudentEcaProfileMap dbStudentEcaProfileMap;
    private final StudentEcaProfileMapMapper studentEcaProfileMapMapper;
    private final I18nMapper i18nMapper;
    private final DbI18N dbI18N;
    private final EcaCourseMapper ecaCourseMapper;

    public List<EcaProfileSectionResponse> query() {
        List<DbEcaProfileSection.Result> list = dslContext.select(
                        ECA_PROFILE_SECTION.ID,
                        multiset(
                                DSL.select(I18N.fields())
                                        .from(I18N)
                                        .where(ECA_PROFILE_SECTION.SECTION_I18N_ID.eq(I18N.ID))
                        ).as(DbEcaProfileSection.Result.Fields.section).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                        multiset(
                                DSL.select(
                                                ECA_PROFILE_OPTION.ID,
                                                multiset(
                                                        DSL.select(I18N.fields())
                                                                .from(I18N)
                                                                .where(ECA_PROFILE_OPTION.OPTION_I18N_ID.eq(I18N.ID))
                                                ).as(DbEcaProfileOption.Result.Fields.optionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
                                        )
                                        .from(ECA_PROFILE_OPTION)
                                        .where(ECA_PROFILE_OPTION.SECTION_ID.eq(ECA_PROFILE_SECTION.ID))
                        ).as(DbEcaProfileSection.Result.Fields.options).convertFrom(r -> r.isEmpty() ? null : r.into(DbEcaProfileOption.Result.class))
                )
                .from(ECA_PROFILE_SECTION)
                .orderBy(ECA_PROFILE_SECTION.SORT.asc())
                .fetchInto(DbEcaProfileSection.Result.class);
        return ecaProfileMapper.toResponse(list);
    }

    public void saveOrUpdate(UUID studentProfileId, EcaProfileRequest request) {
        for (EcaProfileRequest.EcaProfileSectionPayload payload : request.getPayload()) {
            StudentEcaProfileMapPojo createOrUpdate = dbStudentEcaProfileMap.getDsl()
                    .select(STUDENT_ECA_PROFILE_MAP.fields())
                    .from(STUDENT_ECA_PROFILE_MAP)
                    .where(STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_SECTION_ID.eq(payload.getId()).and(STUDENT_ECA_PROFILE_MAP.STUDENT_PROFILE_ID.eq(studentProfileId)))
                    .fetchOptionalInto(StudentEcaProfileMapPojo.class)
                    .orElse(new StudentEcaProfileMapPojo()
                            .setStudentProfileId(studentProfileId)
                            .setEcaProfileId(payload.getId())
                            .setEcaProfileSectionId(payload.getId()));
            createOrUpdate.setEcaProfileOptionCheckedId(payload.getOptions());
            Optional.ofNullable(createOrUpdate.getId())
                    .ifPresentOrElse(
                            id -> {
                                // ID 非 null，执行更新操作
                                dbStudentEcaProfileMap.getDao().update(createOrUpdate); // 假设这是执行更新的方法
                            },
                            () -> {
                                // ID 为 null，执行新增操作
                                dbStudentEcaProfileMap.getDao().insert(createOrUpdate); // 假设这是执行新增的方法
                            }
                    );

        }
    }

    public List<StudentEcaProfileSectionResponse> get(UUID studentProfileId) {
        Field<Integer> scoreField =
                case_()
                        .when(max(ECA_PROFILE_OPTION.SCORE).eq(5), val(5))
                        .else_(round(avg(ECA_PROFILE_OPTION.SCORE)).cast(Integer.class))
                        .as(DbStudentEcaProfileMap.Result.Fields.score);

       List<DbStudentEcaProfileMap.Result> list = dslContext.select(
                        multiset(
                                DSL.select(I18N.fields())
                                        .from(I18N,ECA_PROFILE_SECTION)
                                        .where(ECA_PROFILE_SECTION.SECTION_I18N_ID.eq(I18N.ID).and(ECA_PROFILE_SECTION.ID.eq(STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_SECTION_ID)))
                        ).as(DbStudentEcaProfileMap.Result.Fields.section).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                       scoreField,
                       STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_SECTION_ID
                 ).from(ECA_PROFILE_OPTION, STUDENT_ECA_PROFILE_MAP,ECA_PROFILE_SECTION)
                .where(ECA_PROFILE_OPTION.ID.eq(any(STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_OPTION_CHECKED_ID)))
                .and(STUDENT_ECA_PROFILE_MAP.STUDENT_PROFILE_ID.eq(studentProfileId))
                .and(ECA_PROFILE_SECTION.ID.eq(STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_SECTION_ID))
                .groupBy(STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_SECTION_ID,ECA_PROFILE_SECTION.SORT)
                .orderBy(ECA_PROFILE_SECTION.SORT.asc())
                .fetchInto(DbStudentEcaProfileMap.Result.class);
        if(list.isEmpty()){
            //如果当前学生没有提交过eca profile,则返回给前端score=null
            list = dslContext.select(
                            multiset(
                                    DSL.select(I18N.fields())
                                            .from(I18N,ECA_PROFILE_SECTION)
                                            .where(ECA_PROFILE_SECTION.SECTION_I18N_ID.eq(I18N.ID))
                            ).as(DbStudentEcaProfileMap.Result.Fields.section).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class)),
                            ECA_PROFILE_SECTION.ID
                    ).from(ECA_PROFILE_SECTION)
                    .orderBy(ECA_PROFILE_SECTION.SORT.asc())
                    .fetchInto(DbStudentEcaProfileMap.Result.class);
        }
        return studentEcaProfileMapMapper.toResponse(list);
    }
}
