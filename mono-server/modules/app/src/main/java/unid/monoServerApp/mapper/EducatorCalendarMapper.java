package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerMeta.api.EducatorCalendarRequest;
import unid.monoServerMeta.api.EducatorCalendarResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                StudentProfileMapper.class
        }
)
public interface EducatorCalendarMapper {

    EducatorCalendarPojo toPojo(EducatorCalendarResponse data);

    void merge(@MappingTarget EducatorCalendarPojo data, EducatorCalendarRequest source);

    void merge(@MappingTarget DbEducatorCalendar.Result data, EducatorCalendarRequest source);

    @Mappings({
            @Mapping(source = DbEducatorCalendar.Result.Columns.createdOn, target = EducatorCalendarResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbEducatorCalendar.Result.Columns.updatedOn, target = EducatorCalendarResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    EducatorCalendarResponse toResponse(DbEducatorCalendar.Result data);

    @Mappings({
            @Mapping(source = DbEducatorCalendar.Result.Columns.createdOn, target = EducatorCalendarResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbEducatorCalendar.Result.Columns.updatedOn, target = EducatorCalendarResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<EducatorCalendarResponse> toResponse(List<DbEducatorCalendar.Result> data);
}
