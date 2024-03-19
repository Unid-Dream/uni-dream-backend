package unid.monoServerApp.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.monoServerMeta.model.TagCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-19T12:49:50+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class TagCategoryMapperImpl implements TagCategoryMapper {

    @Override
    public TagCategoryEnum toRecord(TagCategory data) {
        if ( data == null ) {
            return null;
        }

        TagCategoryEnum tagCategoryEnum;

        switch ( data ) {
            case LANGUAGE: tagCategoryEnum = TagCategoryEnum.LANGUAGE;
            break;
            case EXPERTISE: tagCategoryEnum = TagCategoryEnum.EXPERTISE;
            break;
            case ACADEMIC_MAJOR: tagCategoryEnum = TagCategoryEnum.ACADEMIC_MAJOR;
            break;
            case ACADEMIC_SUBJECT: tagCategoryEnum = TagCategoryEnum.ACADEMIC_SUBJECT;
            break;
            case COUNTRY: tagCategoryEnum = TagCategoryEnum.COUNTRY;
            break;
            case SCHOOL: tagCategoryEnum = TagCategoryEnum.SCHOOL;
            break;
            case CURRICULUM: tagCategoryEnum = TagCategoryEnum.CURRICULUM;
            break;
            case QUESTIONNAIRE_QUESTION_ANSWER: tagCategoryEnum = TagCategoryEnum.QUESTIONNAIRE_QUESTION_ANSWER;
            break;
            case CITY: tagCategoryEnum = TagCategoryEnum.CITY;
            break;
            case EDUCATION_LEVEL: tagCategoryEnum = TagCategoryEnum.EDUCATION_LEVEL;
            break;
            case UNIVERSITY: tagCategoryEnum = TagCategoryEnum.UNIVERSITY;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + data );
        }

        return tagCategoryEnum;
    }

    @Override
    public TagCategory toPojo(TagCategoryEnum data) {
        if ( data == null ) {
            return null;
        }

        TagCategory tagCategory;

        switch ( data ) {
            case LANGUAGE: tagCategory = TagCategory.LANGUAGE;
            break;
            case EXPERTISE: tagCategory = TagCategory.EXPERTISE;
            break;
            case ACADEMIC_MAJOR: tagCategory = TagCategory.ACADEMIC_MAJOR;
            break;
            case ACADEMIC_SUBJECT: tagCategory = TagCategory.ACADEMIC_SUBJECT;
            break;
            case COUNTRY: tagCategory = TagCategory.COUNTRY;
            break;
            case SCHOOL: tagCategory = TagCategory.SCHOOL;
            break;
            case CURRICULUM: tagCategory = TagCategory.CURRICULUM;
            break;
            case QUESTIONNAIRE_QUESTION_ANSWER: tagCategory = TagCategory.QUESTIONNAIRE_QUESTION_ANSWER;
            break;
            case CITY: tagCategory = TagCategory.CITY;
            break;
            case EDUCATION_LEVEL: tagCategory = TagCategory.EDUCATION_LEVEL;
            break;
            case UNIVERSITY: tagCategory = TagCategory.UNIVERSITY;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + data );
        }

        return tagCategory;
    }
}
