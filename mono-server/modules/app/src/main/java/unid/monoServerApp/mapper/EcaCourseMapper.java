package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import unid.jooqMono.model.tables.pojos.EcaCoursePojo;
import unid.monoServerMeta.api.EcaCourseRequest;

@Mapper(
        componentModel = "spring"
)
public interface EcaCourseMapper {


    EcaCoursePojo toPojo(EcaCourseRequest data);




}
