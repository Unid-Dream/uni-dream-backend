package unid.monoServerMeta.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwh.coreStarter.type.NamedEnum;

@RequiredArgsConstructor
@Getter
public enum TagCategory implements NamedEnum {
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
    UNIVERSITY("UNIVERSITY"),

    ;

    private final String value;

    @Override
    public String toNamedString() {
        return value;
    }
}
