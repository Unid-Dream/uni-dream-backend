package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerMeta.api.EducatorCalendarRequest;
import unid.monoServerMeta.api.EducatorCalendarResponse;
import unid.monoServerMeta.api.EducatorCalendarTimeSlot;

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

    @Mappings({
            @Mapping(target = EducatorCalendarPojo.Columns.startDatetime,source = EducatorCalendarTimeSlot.Fields.startDateTimeUtc),
            @Mapping(target = EducatorCalendarPojo.Columns.endDatetime,source = EducatorCalendarTimeSlot.Fields.endDateTimeUtc),
    })
    void merge(@MappingTarget EducatorCalendarPojo data, EducatorCalendarTimeSlot source);

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
