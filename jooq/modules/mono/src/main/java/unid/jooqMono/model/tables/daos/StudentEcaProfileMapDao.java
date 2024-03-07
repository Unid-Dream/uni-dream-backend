/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.daos;


import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import unid.jooqMono.model.tables.StudentEcaProfileMapTable;
import unid.jooqMono.model.tables.pojos.StudentEcaProfileMapPojo;
import unid.jooqMono.model.tables.records.StudentEcaProfileMapRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.15.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StudentEcaProfileMapDao extends DAOImpl<StudentEcaProfileMapRecord, StudentEcaProfileMapPojo, UUID> {

    /**
     * Create a new StudentEcaProfileMapDao without any configuration
     */
    public StudentEcaProfileMapDao() {
        super(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP, StudentEcaProfileMapPojo.class);
    }

    /**
     * Create a new StudentEcaProfileMapDao with an attached configuration
     */
    public StudentEcaProfileMapDao(Configuration configuration) {
        super(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP, StudentEcaProfileMapPojo.class, configuration);
    }

    @Override
    @Nonnull
    public UUID getId(StudentEcaProfileMapPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<StudentEcaProfileMapPojo> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    @Nonnull
    public List<StudentEcaProfileMapPojo> fetchById(UUID... values) {
        return fetch(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    @Nullable
    public StudentEcaProfileMapPojo fetchOneById(UUID value) {
        return fetchOne(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ID, value);
    }

    /**
     * Fetch records that have <code>student_profile_id BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    @Nonnull
    public List<StudentEcaProfileMapPojo> fetchRangeOfStudentProfileId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.STUDENT_PROFILE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>student_profile_id IN (values)</code>
     */
    @Nonnull
    public List<StudentEcaProfileMapPojo> fetchByStudentProfileId(UUID... values) {
        return fetch(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.STUDENT_PROFILE_ID, values);
    }

    /**
     * Fetch records that have <code>eca_profile_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    @Nonnull
    public List<StudentEcaProfileMapPojo> fetchRangeOfEcaProfileId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>eca_profile_id IN (values)</code>
     */
    @Nonnull
    public List<StudentEcaProfileMapPojo> fetchByEcaProfileId(UUID... values) {
        return fetch(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_ID, values);
    }

    /**
     * Fetch records that have <code>eca_profile_section_id BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    @Nonnull
    public List<StudentEcaProfileMapPojo> fetchRangeOfEcaProfileSectionId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_SECTION_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>eca_profile_section_id IN (values)</code>
     */
    @Nonnull
    public List<StudentEcaProfileMapPojo> fetchByEcaProfileSectionId(UUID... values) {
        return fetch(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_SECTION_ID, values);
    }

    /**
     * Fetch records that have <code>eca_profile_option_checked_id BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    @Nonnull
    public List<StudentEcaProfileMapPojo> fetchRangeOfEcaProfileOptionCheckedId(UUID[] lowerInclusive, UUID[] upperInclusive) {
        return fetchRange(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_OPTION_CHECKED_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>eca_profile_option_checked_id IN
     * (values)</code>
     */
    @Nonnull
    public List<StudentEcaProfileMapPojo> fetchByEcaProfileOptionCheckedId(UUID[]... values) {
        return fetch(StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP.ECA_PROFILE_OPTION_CHECKED_ID, values);
    }
}
