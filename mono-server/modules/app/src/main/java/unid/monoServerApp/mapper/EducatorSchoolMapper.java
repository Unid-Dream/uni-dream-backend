package unid.monoServerApp.mapper;


import org.mapstruct.Mapper;
import unid.jooqMono.model.tables.pojos.EducatorSessionNotePojo;
import unid.monoServerApp.database.table.school.DbEducatorSchool;
import unid.monoServerMeta.api.EducatorLevelResponse;
import unid.monoServerMeta.api.EducatorSessionNoteResponse;

@Mapper(
        componentModel = "spring"
)
public interface EducatorSchoolMapper {

    EducatorLevelResponse toPojo(DbEducatorSchool.Result data);

}
