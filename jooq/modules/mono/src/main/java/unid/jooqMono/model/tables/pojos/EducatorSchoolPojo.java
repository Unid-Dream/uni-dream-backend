/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;


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
@lombok.experimental.FieldNameConstants(innerTypeName = "Columns")
public class EducatorSchoolPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID universityId;
    private UUID degreeId;
    private UUID educatorProfileId;

    public EducatorSchoolPojo() {}

    public EducatorSchoolPojo(EducatorSchoolPojo value) {
        this.id = value.id;
        this.universityId = value.universityId;
        this.degreeId = value.degreeId;
        this.educatorProfileId = value.educatorProfileId;
    }

    @ConstructorProperties({ "id", "universityId", "degreeId", "educatorProfileId" })
    public EducatorSchoolPojo(
        @Nonnull UUID id,
        @Nullable UUID universityId,
        @Nullable UUID degreeId,
        @Nullable UUID educatorProfileId
    ) {
        this.id = id;
        this.universityId = universityId;
        this.degreeId = degreeId;
        this.educatorProfileId = educatorProfileId;
    }

    /**
     * Getter for <code>public.educator_school.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.educator_school.id</code>.
     */
    public EducatorSchoolPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.educator_school.university_id</code>.
     */
    @Nullable
    public UUID getUniversityId() {
        return this.universityId;
    }

    /**
     * Setter for <code>public.educator_school.university_id</code>.
     */
    public EducatorSchoolPojo setUniversityId(@Nullable UUID universityId) {
        this.universityId = universityId;
        return this;
    }

    /**
     * Getter for <code>public.educator_school.degree_id</code>.
     */
    @Nullable
    public UUID getDegreeId() {
        return this.degreeId;
    }

    /**
     * Setter for <code>public.educator_school.degree_id</code>.
     */
    public EducatorSchoolPojo setDegreeId(@Nullable UUID degreeId) {
        this.degreeId = degreeId;
        return this;
    }

    /**
     * Getter for <code>public.educator_school.educator_profile_id</code>.
     */
    @Nullable
    public UUID getEducatorProfileId() {
        return this.educatorProfileId;
    }

    /**
     * Setter for <code>public.educator_school.educator_profile_id</code>.
     */
    public EducatorSchoolPojo setEducatorProfileId(@Nullable UUID educatorProfileId) {
        this.educatorProfileId = educatorProfileId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("EducatorSchoolPojo (");

        sb.append(id);
        sb.append(", ").append(universityId);
        sb.append(", ").append(degreeId);
        sb.append(", ").append(educatorProfileId);

        sb.append(")");
        return sb.toString();
    }
}
