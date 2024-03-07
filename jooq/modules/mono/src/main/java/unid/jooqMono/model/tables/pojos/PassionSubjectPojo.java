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
public class PassionSubjectPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID nameI18nId;
    private UUID descI18nId;
    private UUID majorId;

    public PassionSubjectPojo() {}

    public PassionSubjectPojo(PassionSubjectPojo value) {
        this.id = value.id;
        this.nameI18nId = value.nameI18nId;
        this.descI18nId = value.descI18nId;
        this.majorId = value.majorId;
    }

    @ConstructorProperties({ "id", "nameI18nId", "descI18nId", "majorId" })
    public PassionSubjectPojo(
        @Nonnull UUID id,
        @Nullable UUID nameI18nId,
        @Nullable UUID descI18nId,
        @Nullable UUID majorId
    ) {
        this.id = id;
        this.nameI18nId = nameI18nId;
        this.descI18nId = descI18nId;
        this.majorId = majorId;
    }

    /**
     * Getter for <code>public.passion_subject.id</code>.
     */
    @Nonnull
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.passion_subject.id</code>.
     */
    public PassionSubjectPojo setId(@Nonnull UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.passion_subject.name_i18n_id</code>.
     */
    @Nullable
    public UUID getNameI18nId() {
        return this.nameI18nId;
    }

    /**
     * Setter for <code>public.passion_subject.name_i18n_id</code>.
     */
    public PassionSubjectPojo setNameI18nId(@Nullable UUID nameI18nId) {
        this.nameI18nId = nameI18nId;
        return this;
    }

    /**
     * Getter for <code>public.passion_subject.desc_i18n_id</code>.
     */
    @Nullable
    public UUID getDescI18nId() {
        return this.descI18nId;
    }

    /**
     * Setter for <code>public.passion_subject.desc_i18n_id</code>.
     */
    public PassionSubjectPojo setDescI18nId(@Nullable UUID descI18nId) {
        this.descI18nId = descI18nId;
        return this;
    }

    /**
     * Getter for <code>public.passion_subject.major_id</code>.
     */
    @Nullable
    public UUID getMajorId() {
        return this.majorId;
    }

    /**
     * Setter for <code>public.passion_subject.major_id</code>.
     */
    public PassionSubjectPojo setMajorId(@Nullable UUID majorId) {
        this.majorId = majorId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PassionSubjectPojo (");

        sb.append(id);
        sb.append(", ").append(nameI18nId);
        sb.append(", ").append(descI18nId);
        sb.append(", ").append(majorId);

        sb.append(")");
        return sb.toString();
    }
}
