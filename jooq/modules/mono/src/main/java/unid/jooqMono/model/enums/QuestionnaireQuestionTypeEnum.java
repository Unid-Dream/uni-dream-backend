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
public enum QuestionnaireQuestionTypeEnum implements EnumType {

    SINGLE_CHOICE("SINGLE_CHOICE"),

    MULTIPLE_CHOICE("MULTIPLE_CHOICE");

    private final String literal;

    private QuestionnaireQuestionTypeEnum(String literal) {
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
        return "questionnaire_question_type";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}
