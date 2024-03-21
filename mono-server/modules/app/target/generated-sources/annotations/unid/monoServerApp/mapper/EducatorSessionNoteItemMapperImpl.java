package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteItem;
import unid.monoServerMeta.api.EducatorSessionNoteCommentResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-21T11:02:11+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class EducatorSessionNoteItemMapperImpl implements EducatorSessionNoteItemMapper {

    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public EducatorSessionNoteCommentResponse toResponse(DbEducatorSessionNoteItem.Result data) {
        if ( data == null ) {
            return null;
        }

        EducatorSessionNoteCommentResponse educatorSessionNoteCommentResponse = new EducatorSessionNoteCommentResponse();

        educatorSessionNoteCommentResponse.setId( data.getId() );
        educatorSessionNoteCommentResponse.setTitleI18n( i18nMapper.toModel( data.getTitleI18n() ) );
        educatorSessionNoteCommentResponse.setComment( data.getComment() );

        return educatorSessionNoteCommentResponse;
    }

    @Override
    public List<EducatorSessionNoteCommentResponse> toResponse(List<DbEducatorSessionNoteItem.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<EducatorSessionNoteCommentResponse> list = new ArrayList<EducatorSessionNoteCommentResponse>( data.size() );
        for ( DbEducatorSessionNoteItem.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }
}
