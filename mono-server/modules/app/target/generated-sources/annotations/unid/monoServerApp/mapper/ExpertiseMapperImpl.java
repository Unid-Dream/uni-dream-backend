package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.ExpertisePojo;
import unid.monoServerApp.database.table.expertise.DbExpertise;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.api.ExpertiseRequest;
import unid.monoServerMeta.api.ExpertiseResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-21T11:02:10+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class ExpertiseMapperImpl implements ExpertiseMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private I18nMapper i18nMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private AcademicMajorMapper academicMajorMapper;

    @Override
    public ExpertisePojo toPojo(ExpertiseResponse data) {
        if ( data == null ) {
            return null;
        }

        ExpertisePojo expertisePojo = new ExpertisePojo();

        expertisePojo.setId( data.getId() );

        return expertisePojo;
    }

    @Override
    public void merge(DbExpertise.Result data, ExpertiseRequest source) {
        if ( source == null ) {
            return;
        }

        data.setTagId( source.getTagId() );
        if ( source.getDescriptionI18n() != null ) {
            if ( data.getDescriptionI18n() == null ) {
                data.setDescriptionI18n( new DbI18N.Result() );
            }
            i18nMapper.merge( data.getDescriptionI18n(), source.getDescriptionI18n() );
        }
        else {
            data.setDescriptionI18n( null );
        }
    }

    @Override
    public void merge(ExpertisePojo data, ExpertiseRequest source) {
        if ( source == null ) {
            return;
        }

        data.setTagId( source.getTagId() );
    }

    @Override
    public ExpertiseResponse toResponse(DbExpertise.Result data) {
        if ( data == null ) {
            return null;
        }

        ExpertiseResponse expertiseResponse = new ExpertiseResponse();

        expertiseResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        expertiseResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        expertiseResponse.setId( data.getId() );
        expertiseResponse.setDescriptionI18n( i18nMapper.toModel( data.getDescriptionI18n() ) );
        expertiseResponse.setTag( tagMapper.toResponse( data.getTag() ) );
        expertiseResponse.setMajors( academicMajorMapper.toResponse( data.getMajors() ) );

        return expertiseResponse;
    }

    @Override
    public List<ExpertiseResponse> toResponse(List<DbExpertise.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<ExpertiseResponse> list = new ArrayList<ExpertiseResponse>( data.size() );
        for ( DbExpertise.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }
}
