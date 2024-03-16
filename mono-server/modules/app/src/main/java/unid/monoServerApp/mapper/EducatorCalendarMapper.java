package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerMeta.api.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                StudentProfileMapper.class,
                CountryMapper.class,
                SchoolMapper.class,
                EducationLevelMapper.class
        }
)
public interface EducatorCalendarMapper {

    EducatorCalendarPojo toPojo(EducatorCalendarResponse data);

    void merge(@MappingTarget EducatorCalendarPojo data, EducatorCalendarRequest.TimeSlotPayload source);

    void merge(@MappingTarget DbEducatorCalendar.Result data, EducatorCalendarRequest source);

//    @Mappings({
//            @Mapping(source = DbEducatorCalendar.Result.Columns.createdOn, target = EducatorCalendarResponse.BaseResponseFields.createdOnUtc),
//            @Mapping(source = DbEducatorCalendar.Result.Columns.updatedOn, target = EducatorCalendarResponse.BaseResponseFields.updatedOnUtc)
//    })
//    @InheritConfiguration
//    EducatorCalendarResponse toResponse(DbEducatorCalendar.Result data);

//    @Mappings({
//            @Mapping(source = DbEducatorCalendar.Result.Columns.createdOn, target = EducatorCalendarResponse.BaseResponseFields.createdOnUtc),
//            @Mapping(source = DbEducatorCalendar.Result.Columns.updatedOn, target = EducatorCalendarResponse.BaseResponseFields.updatedOnUtc)
//    })
//    @InheritConfiguration
//    List<EducatorCalendarResponse> toResponse(List<DbEducatorCalendar.Result> data);




    @Mappings({
            @Mapping(source = DbEducatorCalendar.Result.Columns.startTimeUtc, target = EducatorCalendarTimeSlotResponse.Fields.startDateTimeUtc),
            @Mapping(source = DbEducatorCalendar.Result.Columns.endTimeUtc, target = EducatorCalendarTimeSlotResponse.Fields.endDateTimeUtc),
    })
    @InheritConfiguration
    EducatorCalendarTimeSlotResponse toResponse(DbEducatorCalendar.Result data);

    @Mappings({
            @Mapping(source = DbEducatorCalendar.Result.Columns.startTimeUtc, target = EducatorCalendarTimeSlotResponse.Fields.startDateTimeUtc),
            @Mapping(source = DbEducatorCalendar.Result.Columns.endTimeUtc, target = EducatorCalendarTimeSlotResponse.Fields.endDateTimeUtc),
    })
    @InheritConfiguration
    EducatorCalendarTimeSlotResponse toAvailableTimeSlotResponse(EducatorCalendarPojo data);

    @Mappings({
            @Mapping(source = DbEducatorCalendar.Result.Columns.startTimeUtc, target = EducatorCalendarTimeSlotResponse.Fields.startDateTimeUtc),
            @Mapping(source = DbEducatorCalendar.Result.Columns.endTimeUtc, target = EducatorCalendarTimeSlotResponse.Fields.endDateTimeUtc),
    })
    @InheritConfiguration
    EducatorCalendarSimpleResponse.TimeSlotPayload toSimpleResponse(DbEducatorCalendar.Result data);

    List<EducatorCalendarSimpleResponse.TimeSlotPayload> toSimpleResponse(List<DbEducatorCalendar.Result> data);

    List<EducatorCalendarTimeSlotResponse> toResponse(List<DbEducatorCalendar.Result> data);
}
