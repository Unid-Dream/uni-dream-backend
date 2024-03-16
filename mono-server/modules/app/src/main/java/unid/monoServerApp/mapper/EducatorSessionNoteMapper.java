package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.EducatorSessionNoteItemPojo;
import unid.jooqMono.model.tables.pojos.EducatorSessionNotePojo;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteMap;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNote;
import unid.monoServerMeta.api.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
        }
)
public interface EducatorSessionNoteMapper {

    EducatorSessionNotePojo toPojo(EducatorSessionNoteResponse data);

    void merge(@MappingTarget EducatorSessionNotePojo data, EducatorSessionNoteRequest source);

    void merge(@MappingTarget DbEducatorSessionNote.Result data, EducatorSessionNoteRequest source);

    void merge(@MappingTarget EducatorSessionNoteItemPojo data, EducatorSessionNoteItemPayload source);

    @Mappings({
            @Mapping(source = DbEducatorSessionNote.Result.Columns.createdOn, target = EducatorSessionNoteResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbEducatorSessionNote.Result.Columns.updatedOn, target = EducatorSessionNoteResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    EducatorSessionNoteResponse toResponse(DbEducatorSessionNote.Result data);

    @Mappings({
            @Mapping(source = DbEducatorSessionNote.Result.Columns.createdOn, target = EducatorSessionNoteResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbEducatorSessionNote.Result.Columns.updatedOn, target = EducatorSessionNoteResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<EducatorSessionNoteResponse> toResponse(List<DbEducatorSessionNote.Result> data);

    @Mappings({
            @Mapping(source = DbEducatorSessionNoteMap.Result.Columns.createdOn, target = EducatorSessionNoteResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbEducatorSessionNoteMap.Result.Columns.updatedOn, target = EducatorSessionNoteResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    EducatorSessionNoteResponse toResponse(DbEducatorSessionNoteMap.Result data);

    @Mappings({
            @Mapping(source = DbEducatorSessionNoteMap.Map.Fields.educatorSessionNote, target = EducatorSessionCommentResponse.Fields.subject)
    })
    @InheritConfiguration
    EducatorSessionCommentResponse toCommentResponse(DbEducatorSessionNoteMap.Map data);

    List<EducatorSessionCommentResponse> toCommentResponse(List<DbEducatorSessionNoteMap.Map> data);



}
