package unid.monoServerApp.database.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.TableImpl;
import org.jooq.impl.TableRecordImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unid.jooqMono.model.DefaultCatalog;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.enums.SchoolLevelEnum;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.jooqMono.model.tables.records.I18nRecord;
import unid.monoServerApp.database.table.language.DbLanguage;
import unid.monoServerApp.database.table.user.DbUser;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DbTestInitService {
    private final DSLContext dslContext;
    private final DbUser dbUser;
    private final DbLanguage dbLanguage;

    @Transactional
    public void injectInitialData() {
        log.info("Starting to inject test data");
        var publicSchema = Public.PUBLIC;

        // lang
        var langs = Map.of(
                Lang.EN, createI18n("English", "英文"),
                Lang.TC, createI18n("Cantonese", "廣東話")
        );
        var langTagMap =
                insertSingular(
                        publicSchema.TAG,
                        langs,
                        (record, value) -> record
                                .setTagCategory(TagCategoryEnum.LANGUAGE)
                                .setDescriptionI18nId(insertI18n(value.getValue()).getId())
                );
        var langMap = insertSingular(
                publicSchema.LANGUAGE,
                langs,
                (record, value) -> record
                        .setTagId(langTagMap.get(value.getKey()).getId())
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );

        // academic_major
        var majors = Stream.of("a", "b", "c", "d", "e")
                .map(val -> Map.entry(val, createI18n(
                        String.format("Major_%s", val),
                        String.format("專修_%s", val)
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var majorsTagMap = insertSingular(
                publicSchema.TAG,
                majors,
                (record, value) -> record
                        .setTagCategory(TagCategoryEnum.ACADEMIC_MAJOR)
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );
        var majorMap = insertSingular(
                publicSchema.ACADEMIC_MAJOR,
                majors,
                (record, value) -> record
                        .setTagId(majorsTagMap.get(value.getKey()).getId())
                        .setTitleI18nId(insertI18n(value.getValue()).getId())
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );

        // academic_subject
        var subjects = Stream.of("a1", "a2", "b1", "e1", "e2")
                .map(val -> Map.entry(val, createI18n(
                        String.format("Subject_%s", val),
                        String.format("科目_%s", val)
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var subjectTagMap = insertSingular(
                publicSchema.TAG,
                subjects,
                (record, value) -> record
                        .setTagCategory(TagCategoryEnum.ACADEMIC_SUBJECT)
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );
        var subjectMap = insertSingular(
                publicSchema.ACADEMIC_SUBJECT,
                subjects,
                (record, value) -> record
                        .setTagId(subjectTagMap.get(value.getKey()).getId())
                        .setTitleI18nId(insertI18n(value.getValue()).getId())
                        .setDescriptionMasterDegreeI18nId(insertI18n(value.getValue()).getId())
                        .setDescriptionPhdI18nId(insertI18n(value.getValue()).getId())
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );

        // major & subject map
        var majorSubjectMap = majorMap.entrySet().stream()
                .filter(allMajors -> subjectMap.keySet().stream().anyMatch(k -> k.contains(allMajors.getKey())))
                .map(majorThatHasSubjects -> Map.entry(
                        majorThatHasSubjects.getValue().getId(),
                        subjectMap.entrySet().stream()
                                .filter(s -> s.getKey().contains(majorThatHasSubjects.getKey()))
                                .map(s -> s.getValue().getId())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        insert(
                publicSchema.ACADEMIC_MAJOR_SUBJECT_MAP,
                majorSubjectMap,
                (record, value) -> record
                        .setAcademicMajorId(value.getKey())
                        .setAcademicSubjectId(value.getValue())
        );

        // expertise
        var expertise = Stream.of("ac", "cd", "de", "be", "ff", "gg")
                .map(val -> Map.entry(val, createI18n(
                        String.format("Expertise_%s", val),
                        String.format("專業範疇_%s", val)
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var expertiseTagMap = insertSingular(
                publicSchema.TAG,
                expertise,
                (record, value) -> record
                        .setTagCategory(TagCategoryEnum.EXPERTISE)
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );
        var expertiseMap = insertSingular(
                publicSchema.EXPERTISE,
                expertise,
                (record, value) -> record
                        .setTagId(expertiseTagMap.get(value.getKey()).getId())
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );

        // expertise & major map
        var expertiseMajorMap = expertiseMap.entrySet().stream()
                .filter(allExpertises -> majorMap.keySet().stream().anyMatch(k -> allExpertises.getKey().contains(k)))
                .map(expertiseHasMajor -> Map.entry(
                        expertiseHasMajor.getValue().getId(),
                        majorMap.entrySet().stream()
                                .filter(s -> expertiseHasMajor.getKey().contains(s.getKey()))
                                .map(s -> s.getValue().getId())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        insert(
                publicSchema.EXPERTISE_ACADEMIC_MAJOR_MAP,
                expertiseMajorMap,
                (record, value) -> record
                        .setExpertiseId(value.getKey())
                        .setAcademicMajorId(value.getValue())
        );

        //country
        var countries = Map.of(
                "BR", createI18n("England", "英國"),
                "HK", createI18n("Hong Kong", "香港")
        );
        var countryTags = insertSingular(
                publicSchema.TAG,
                countries,
                (record, value) -> record
                        .setTagCategory(TagCategoryEnum.COUNTRY)
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );
        var countryMap = insertSingular(
                publicSchema.COUNTRY,
                countries,
                (record, value) -> record
                        .setTagId(countryTags.get(value.getKey()).getId())
                        .setNameI18nId(insertI18n(value.getValue()).getId())
        );

        // city
        var cities = Stream.of("HK_1", "HK_2", "HK_3", "BR_1", "BR_2", "BR_3")
                .map(val -> Map.entry(val, createI18n(
                        String.format("City_%s", val),
                        String.format("城市_%s", val)
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var cityMap = insertSingular(
                publicSchema.CITY,
                cities,
                (record, value) -> record
                        .setNameI18nId(insertI18n(value.getValue()).getId())
                        .setCountryId(countryMap.entrySet().stream()
                                .filter(country -> value.getKey().contains(country.getKey()))
                                .findFirst().get().getValue().getId())
        );

        // school
        var secondarySchools = Stream.of("HK_1", "HK_2", "HK_3", "BR_1", "BR_2", "BR_3")
                .map(val -> Map.entry(val, createI18n(
                        String.format("Secondary School %s", val),
                        String.format("中學_%s", val)
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var secondarySchoolTag = insertSingular(
                publicSchema.TAG,
                secondarySchools,
                (record, value) -> record
                        .setTagCategory(TagCategoryEnum.SCHOOL)
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );
        var secondarySchoolMap = insertSingular(
                publicSchema.SCHOOL,
                secondarySchools,
                (record, value) -> {
                    var city = cityMap.entrySet().stream()
                            .filter(c -> value.getKey().equals(c.getKey()))
                            .findFirst().get().getValue();
                    return record
                            .setSchoolLevel(SchoolLevelEnum.SECONDARY_SCHOOL)
                            .setNameI18nId(insertI18n(value.getValue()).getId())
                            .setCountryId(city.getCountryId())
                            .setCityId(city.getId())
                            .setTagId(secondarySchoolTag.get(value.getKey()).getId())
                            .setLatitude("38.8951")
                            .setLongitude("-77.0364")
                            .setDetailedAddressI18nId(insertI18n(
                                    createI18n(
                                            "This Is A Very Detailed Address",
                                            "好詳盡地址"
                                    )
                            ).getId());
                }
        );
        var universities = Stream.of("HK_1", "HK_2", "HK_3", "BR_1", "BR_2", "BR_3")
                .map(val -> Map.entry(val, createI18n(
                        String.format("University %s", val),
                        String.format("大學_%s", val)
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var universityTag = insertSingular(
                publicSchema.TAG,
                universities,
                (record, value) -> record
                        .setTagCategory(TagCategoryEnum.SCHOOL)
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );
        var universityMap = insertSingular(
                publicSchema.SCHOOL,
                universities,
                (record, value) -> {
                    var city = cityMap.entrySet().stream()
                            .filter(c -> value.getKey().equals(c.getKey()))
                            .findFirst().get().getValue();
                    return record
                            .setSchoolLevel(SchoolLevelEnum.UNIVERSITY)
                            .setNameI18nId(insertI18n(value.getValue()).getId())
                            .setCountryId(city.getCountryId())
                            .setCityId(city.getId())
                            .setTagId(universityTag.get(value.getKey()).getId())
                            .setLatitude("38.8951")
                            .setLongitude("-77.0364")
                            .setDetailedAddressI18nId(insertI18n(
                                    createI18n(
                                            "This Is A Very Detailed Address",
                                            "好詳盡地址"
                                    )
                            ).getId());
                }
        );

        // education level
        var secondarySchoolEduLvl = Stream.of("9", "10", "11", "13")
                .map(val -> Map.entry(val, createI18n(
                        String.format("Secondary School Grade %s", val),
                        String.format("中學%s年級", val)
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var secondarySchoolEduLvlMap = insertSingular(
                publicSchema.EDUCATION_LEVEL,
                secondarySchoolEduLvl,
                (record, value) -> record
                        .setSchoolLevel(SchoolLevelEnum.SECONDARY_SCHOOL)
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
                        .setGrade(value.getKey())
        );
        var universityEduLvl = Stream.of("Bachelor", "Master", "Doctor")
                .map(val -> Map.entry(val, createI18n(
                        String.format("University %s", val),
                        String.format("大學%s", val)
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var universityEduLvlmap = insertSingular(
                publicSchema.EDUCATION_LEVEL,
                universityEduLvl,
                (record, value) -> record
                        .setSchoolLevel(SchoolLevelEnum.UNIVERSITY)
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
                        .setGrade(value.getKey())
        );

        // curriculum
        var secondarySchoolCurriculums = Stream.of("a", "b", "c", "d", "e", "f")
                .map(val -> Map.entry(val, createI18n(
                        String.format("Secondary School Curriculum %s", val),
                        String.format("中學課程_%s", val)
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var secondarySchoolCurriculumTags = insertSingular(
                publicSchema.TAG,
                secondarySchoolCurriculums,
                (record, value) -> record
                        .setTagCategory(TagCategoryEnum.CURRICULUM)
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );
        var secondarySchoolCurriculumMap = insertSingular(
                publicSchema.CURRICULUM,
                secondarySchoolCurriculums,
                (record, value) -> record
                        .setSchoolLevel(SchoolLevelEnum.SECONDARY_SCHOOL)
                        .setNameI18nId(insertI18n(value.getValue()).getId())
                        .setTagId(secondarySchoolCurriculumTags.get(value.getKey()).getId())
        );
        var universityCurriculums = Stream.of("a", "b", "c", "d", "e", "f")
                .map(val -> Map.entry(val, createI18n(
                        String.format("University Curriculum %s", val),
                        String.format("大學課程_%s", val)
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var universityCurriculumTags = insertSingular(
                publicSchema.TAG,
                universityCurriculums,
                (record, value) -> record
                        .setTagCategory(TagCategoryEnum.CURRICULUM)
                        .setDescriptionI18nId(insertI18n(value.getValue()).getId())
        );
        var universityCurriculumnMap = insertSingular(
                publicSchema.CURRICULUM,
                universityCurriculums,
                (record, value) -> record
                        .setSchoolLevel(SchoolLevelEnum.UNIVERSITY)
                        .setNameI18nId(insertI18n(value.getValue()).getId())
                        .setTagId(universityCurriculumTags.get(value.getKey()).getId())
        );

        // school identity
        var schoolIdentityMap = insertSingular(
                publicSchema.SCHOOL_IDENTITY,
                Stream.of('a', 'b', 'c', 'd', 'e')
                        .map(val -> Map.entry(val, createI18n(
                                String.format("School Identity %s", val),
                                String.format("院校身份 %s", val)
                        )))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)),
                (record, value) -> record.setNameI18nId(insertI18n(value.getValue()).getId())
        );

        // user
        var students = Stream.of("ck.wong@powhouse.tech")
                .map(val -> Map.entry(val, createI18n(
                        "Lo_Joshua",
                        "盧_智聰"
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var studentMap = insertSingular(
                publicSchema.USER,
                students,
                (record, value) -> {
                    var firstName = value.getValue()
                            .entrySet().stream()
                            .map(s -> Map.entry(s.getKey(), s.getValue().split("_")[1]))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    var lastName = value.getValue()
                            .entrySet().stream()
                            .map(s -> Map.entry(s.getKey(), s.getValue().split("_")[0]))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    return record
                            .setUserRole(UserRoleEnum.STUDENT)
                            .setFistNameI18nId(insertI18n(firstName).getId())
                            .setLastNameI18nId(insertI18n(lastName).getId())
                            .setEmail(value.getKey())
                            .setEmailVerified(true)
                            .setLoginId(value.getKey())
                            .setLoginPassword("Aa1234567890");
                }
        );
        var educators = Stream.of("ck.wong.0212@gmail.com")
                .map(val -> Map.entry(val, createI18n(
                        "Wong_CK",
                        "大_癲佬"
                )))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        var educatorMap = insertSingular(
                publicSchema.USER,
                educators,
                (record, value) -> {
                    var firstName = value.getValue()
                            .entrySet().stream()
                            .map(s -> Map.entry(s.getKey(), s.getValue().split("_")[1]))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    var lastName = value.getValue()
                            .entrySet().stream()
                            .map(s -> Map.entry(s.getKey(), s.getValue().split("_")[0]))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    return record
                            .setUserRole(UserRoleEnum.EDUCATOR)
                            .setFistNameI18nId(insertI18n(firstName).getId())
                            .setLastNameI18nId(insertI18n(lastName).getId())
                            .setEmail(value.getKey())
                            .setEmailVerified(true)
                            .setLoginId(value.getKey())
                            .setLoginPassword("Aa1234567890");
                }
        );

//        throw new RuntimeException("lalalala");
    }

    private Map<Lang, String> createI18n(String en, String tc) {
        return Map.of(Lang.EN, en, Lang.TC, tc);
    }

    private Map<Lang, String> createI18n(I18nRecord i18nRecord) {
        return Map.of(Lang.EN, i18nRecord.getEnglish(), Lang.TC, i18nRecord.getChineseTraditional());
    }

    private I18nRecord insertI18n(Map<Lang, String> i18nMap) {
        return dslContext.insertInto(DefaultCatalog.DEFAULT_CATALOG.PUBLIC.I18N).set(
                DefaultCatalog.DEFAULT_CATALOG.PUBLIC.I18N.newRecord()
                        .setEnglish(i18nMap.get(Lang.EN))
                        .setChineseTraditional(i18nMap.get(Lang.TC))
        ).returning().fetchOne();
    }

    private <Record extends TableRecordImpl<Record>> Record select(TableImpl<Record> table, UUID id) {
        return dslContext.selectFrom(table)
                .where(table.field("id", UUID.class).eq(id))
                .fetchOne();
    }

    private I18nRecord selectI18n(UUID id) {
        return dslContext.selectFrom(DefaultCatalog.DEFAULT_CATALOG.PUBLIC.I18N)
                .where(DefaultCatalog.DEFAULT_CATALOG.PUBLIC.I18N.ID.eq(id))
                .fetchOne();
    }

    private <Key, Value, Record extends TableRecordImpl<Record>> Map<Key, Record> insertSingular(
            TableImpl<Record> table,
            Map<Key, Value> inputMap,
            BiFunction<Record, Map.Entry<Key, Value>, Record> newRecord
    ) {
        return inputMap.entrySet().stream().map(entry -> Map.entry(
                entry.getKey(),
                Objects.requireNonNull(dslContext.insertInto(table).set(
                        newRecord.apply(table.newRecord(), entry)
                ).returning().fetchOptional().orElse(null))
        )).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
        ));
    }

    private <Key, Value, Record extends TableRecordImpl<Record>> Map<Key, List<Record>> insertMultiple(
            TableImpl<Record> table,
            Map<Key, List<Value>> inputMap,
            BiFunction<Record, Map.Entry<Key, Value>, Record> newRecord
    ) {
        return inputMap.entrySet().stream().map(entry -> Map.entry(
                entry.getKey(),
                entry.getValue().stream().map(value -> Objects.requireNonNull(dslContext.insertInto(table).set(
                                newRecord.apply(table.newRecord(), Map.entry(entry.getKey(), value))
                        ).returning().fetchOptional().orElse(null)))
                        .collect(Collectors.toList())
        )).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
        ));
    }

    private <Key, Value, Record extends TableRecordImpl<Record>> void insert(
            TableImpl<Record> table,
            Map<Key, List<Value>> inputMap,
            BiFunction<Record, Map.Entry<Key, Value>, Record> eachRecord
    ) {
        inputMap.forEach((key, values) -> {
            values.forEach(value -> {
                dslContext.insertInto(table).set(eachRecord.apply(table.newRecord(), Map.entry(key, value))).execute();
            });
        });
    }

    enum Lang {
        EN, TC
    }
}
