package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import unid.jooqMono.model.tables.pojos.EventPojo;
import unid.monoServerMeta.api.CourseEventPayload;

@Mapper(
        componentModel = "spring"
)
public interface CourseEventMapper {

    void merge(@MappingTarget EventPojo data, CourseEventPayload payload);
}
