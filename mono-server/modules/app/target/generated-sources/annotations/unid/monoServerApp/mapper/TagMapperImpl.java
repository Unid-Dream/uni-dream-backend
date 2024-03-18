package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.tables.pojos.TagPojo;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerMeta.api.TagRequest;
import unid.monoServerMeta.api.TagResponse;
import unid.monoServerMeta.model.TagCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T21:35:09+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public TagPojo toPojo(TagResponse data) {
        if ( data == null ) {
            return null;
        }

        TagPojo tagPojo = new TagPojo();

        tagPojo.setId( data.getId() );
        tagPojo.setTagCategory( tagCategoryToTagCategoryEnum( data.getTagCategory() ) );

        return tagPojo;
    }

    @Override
    public void merge(TagPojo data, TagRequest source) {
        if ( source == null ) {
            return;
        }

        data.setTagCategory( tagCategoryToTagCategoryEnum( source.getTagCategory() ) );
    }

    @Override
    public TagResponse toResponse(TagPojo data) {
        if ( data == null ) {
            return null;
        }

        TagResponse tagResponse = new TagResponse();

        tagResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        tagResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        tagResponse.setId( data.getId() );
        tagResponse.setTagCategory( tagCategoryEnumToTagCategory( data.getTagCategory() ) );

        return tagResponse;
    }

    @Override
    public TagResponse toResponse(DbTag.Result data) {
        if ( data == null ) {
            return null;
        }

        TagResponse tagResponse = new TagResponse();

        tagResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        tagResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        tagResponse.setId( data.getId() );
        tagResponse.setDescriptionI18n( i18nMapper.toModel( data.getDescriptionI18n() ) );
        tagResponse.setTagCategory( tagCategoryEnumToTagCategory( data.getTagCategory() ) );

        return tagResponse;
    }

    @Override
    public List<TagResponse> toResponse(List<DbTag.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<TagResponse> list = new ArrayList<TagResponse>( data.size() );
        for ( DbTag.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }

    protected TagCategoryEnum tagCategoryToTagCategoryEnum(TagCategory tagCategory) {
        if ( tagCategory == null ) {
            return null;
        }

        TagCategoryEnum tagCategoryEnum;

        switch ( tagCategory ) {
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
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + tagCategory );
        }

        return tagCategoryEnum;
    }

    protected TagCategory tagCategoryEnumToTagCategory(TagCategoryEnum tagCategoryEnum) {
        if ( tagCategoryEnum == null ) {
            return null;
        }

        TagCategory tagCategory;

        switch ( tagCategoryEnum ) {
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
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + tagCategoryEnum );
        }

        return tagCategory;
    }
}
