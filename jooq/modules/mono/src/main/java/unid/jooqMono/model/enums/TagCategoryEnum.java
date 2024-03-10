/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model.enums;


import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;

import unid.jooqMono.model.Public;


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
public enum TagCategoryEnum implements EnumType {

    LANGUAGE("LANGUAGE"),

    EXPERTISE("EXPERTISE"),

    ACADEMIC_MAJOR("ACADEMIC_MAJOR"),

    ACADEMIC_SUBJECT("ACADEMIC_SUBJECT"),

    COUNTRY("COUNTRY"),

    SCHOOL("SCHOOL"),

    CURRICULUM("CURRICULUM"),

    QUESTIONNAIRE_QUESTION_ANSWER("QUESTIONNAIRE_QUESTION_ANSWER"),

    CITY("CITY"),

    EDUCATION_LEVEL("EDUCATION_LEVEL"),

    UNIVERSITY("UNIVERSITY");

    private final String literal;

    private TagCategoryEnum(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public String getName() {
        return "tag_category";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}
