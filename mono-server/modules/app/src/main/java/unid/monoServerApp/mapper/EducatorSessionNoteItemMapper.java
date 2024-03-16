package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.EducatorSessionNoteItemPojo;
import unid.jooqMono.model.tables.pojos.EducatorSessionNoteMapPojo;
import unid.jooqMono.model.tables.pojos.EducatorSessionNotePojo;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNote;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteItem;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteMap;
import unid.monoServerMeta.api.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
        }
)
public interface EducatorSessionNoteItemMapper {


    EducatorSessionNoteCommentResponse toResponse(DbEducatorSessionNoteItem.Result data);



    List<EducatorSessionNoteCommentResponse> toResponse(List<DbEducatorSessionNoteItem.Result> data);


}
