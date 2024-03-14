package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerMeta.api.EducatorCalendarRequest;
import unid.monoServerMeta.api.EducatorCalendarResponse;
import unid.monoServerMeta.api.EducatorCalendarTimeSlotPayload;
import unid.monoServerMeta.api.EducatorCalendarTimeSlotResponse;
import unid.monoServerMeta.model.BookingStatus;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-14T20:52:28+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class EducatorCalendarMapperImpl implements EducatorCalendarMapper {

    @Override
    public EducatorCalendarPojo toPojo(EducatorCalendarResponse data) {
        if ( data == null ) {
            return null;
        }

        EducatorCalendarPojo educatorCalendarPojo = new EducatorCalendarPojo();

        educatorCalendarPojo.setId( data.getId() );
        educatorCalendarPojo.setDate( data.getDate() );
        educatorCalendarPojo.setHourStart( data.getHourStart() );
        educatorCalendarPojo.setHourEnd( data.getHourEnd() );
        educatorCalendarPojo.setBookingStatus( bookingStatusToBookingStatusEnum( data.getBookingStatus() ) );

        return educatorCalendarPojo;
    }

    @Override
    public void merge(EducatorCalendarPojo data, EducatorCalendarTimeSlotPayload source) {
        if ( source == null ) {
            return;
        }
    }

    @Override
    public void merge(DbEducatorCalendar.Result data, EducatorCalendarRequest source) {
        if ( source == null ) {
            return;
        }
    }

    @Override
    public EducatorCalendarTimeSlotResponse toResponse(DbEducatorCalendar.Result data) {
        if ( data == null ) {
            return null;
        }

        EducatorCalendarTimeSlotResponse educatorCalendarTimeSlotResponse = new EducatorCalendarTimeSlotResponse();

        educatorCalendarTimeSlotResponse.setStartDateTimeUtc( data.getStartTimeUtc() );
        educatorCalendarTimeSlotResponse.setEndDateTimeUtc( data.getEndTimeUtc() );
        educatorCalendarTimeSlotResponse.setId( data.getId() );

        return educatorCalendarTimeSlotResponse;
    }

    @Override
    public List<EducatorCalendarTimeSlotResponse> toResponse(List<DbEducatorCalendar.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<EducatorCalendarTimeSlotResponse> list = new ArrayList<EducatorCalendarTimeSlotResponse>( data.size() );
        for ( DbEducatorCalendar.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
    }

    protected BookingStatusEnum bookingStatusToBookingStatusEnum(BookingStatus bookingStatus) {
        if ( bookingStatus == null ) {
            return null;
        }

        BookingStatusEnum bookingStatusEnum;

        switch ( bookingStatus ) {
            case AVAILABLE: bookingStatusEnum = BookingStatusEnum.AVAILABLE;
            break;
            case RESERVED: bookingStatusEnum = BookingStatusEnum.RESERVED;
            break;
            case PENDING: bookingStatusEnum = BookingStatusEnum.PENDING;
            break;
            case ACCEPTED: bookingStatusEnum = BookingStatusEnum.ACCEPTED;
            break;
            case REJECTED: bookingStatusEnum = BookingStatusEnum.REJECTED;
            break;
            case CANCELLED: bookingStatusEnum = BookingStatusEnum.CANCELLED;
            break;
            case VOID: bookingStatusEnum = BookingStatusEnum.VOID;
            break;
            case FINISHED: bookingStatusEnum = BookingStatusEnum.FINISHED;
            break;
            case UNFINISHED: bookingStatusEnum = BookingStatusEnum.UNFINISHED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + bookingStatus );
        }

        return bookingStatusEnum;
    }
}
