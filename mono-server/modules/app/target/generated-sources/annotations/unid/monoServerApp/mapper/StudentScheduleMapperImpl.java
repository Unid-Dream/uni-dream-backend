package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerMeta.api.EducatorProfileSimpleResponse;
import unid.monoServerMeta.api.StudentScheduleResponse;
import unid.monoServerMeta.model.BookingStatus;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-28T23:20:22+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class StudentScheduleMapperImpl implements StudentScheduleMapper {

    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private EnumMapper enumMapper;
    @Autowired
    private I18nMapper i18nMapper;

    @Override
    public StudentScheduleResponse toResponse(DbStudentPaymentTransaction.ResultForList data) {
        if ( data == null ) {
            return null;
        }

        StudentScheduleResponse studentScheduleResponse = new StudentScheduleResponse();

        studentScheduleResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        studentScheduleResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );
        studentScheduleResponse.setId( data.getId() );
        studentScheduleResponse.setTransactionAmount( data.getTransactionAmount() );
        studentScheduleResponse.setTransactionCurrency( enumMapper.toCurrencyResponse( data.getTransactionCurrency() ) );
        studentScheduleResponse.setTransactionItem( enumMapper.toTransactionItemResponse( data.getTransactionItem() ) );
        studentScheduleResponse.setPaymentMethod( enumMapper.toPaymentMethodResponse( data.getPaymentMethod() ) );
        studentScheduleResponse.setPaymentStatus( enumMapper.toPaymentStatusResponse( data.getPaymentStatus() ) );
        studentScheduleResponse.setEducator( resultToEducatorProfileSimpleResponse( data.getEducator() ) );
        studentScheduleResponse.setCalendar( resultToCalendar( data.getCalendar() ) );

        return studentScheduleResponse;
    }

    @Override
    public List<StudentScheduleResponse> toResponse(List<DbStudentPaymentTransaction.ResultForList> data) {
        if ( data == null ) {
            return null;
        }

        List<StudentScheduleResponse> list = new ArrayList<StudentScheduleResponse>( data.size() );
        for ( DbStudentPaymentTransaction.ResultForList resultForList : data ) {
            list.add( toResponse( resultForList ) );
        }

        return list;
    }

    protected EducatorProfileSimpleResponse resultToEducatorProfileSimpleResponse(DbUser.Result result) {
        if ( result == null ) {
            return null;
        }

        EducatorProfileSimpleResponse educatorProfileSimpleResponse = new EducatorProfileSimpleResponse();

        educatorProfileSimpleResponse.setId( result.getId() );
        educatorProfileSimpleResponse.setFirstNameI18n( i18nMapper.toModel( result.getFirstNameI18n() ) );
        educatorProfileSimpleResponse.setLastNameI18n( i18nMapper.toModel( result.getLastNameI18n() ) );
        educatorProfileSimpleResponse.setEmail( result.getEmail() );

        return educatorProfileSimpleResponse;
    }

    protected BookingStatus bookingStatusEnumToBookingStatus(BookingStatusEnum bookingStatusEnum) {
        if ( bookingStatusEnum == null ) {
            return null;
        }

        BookingStatus bookingStatus;

        switch ( bookingStatusEnum ) {
            case AVAILABLE: bookingStatus = BookingStatus.AVAILABLE;
            break;
            case RESERVED: bookingStatus = BookingStatus.RESERVED;
            break;
            case PENDING: bookingStatus = BookingStatus.PENDING;
            break;
            case ACCEPTED: bookingStatus = BookingStatus.ACCEPTED;
            break;
            case REJECTED: bookingStatus = BookingStatus.REJECTED;
            break;
            case CANCELLED: bookingStatus = BookingStatus.CANCELLED;
            break;
            case VOID: bookingStatus = BookingStatus.VOID;
            break;
            case FINISHED: bookingStatus = BookingStatus.FINISHED;
            break;
            case UNFINISHED: bookingStatus = BookingStatus.UNFINISHED;
            break;
            case ATTEND: bookingStatus = BookingStatus.ATTEND;
            break;
            case ABSENT: bookingStatus = BookingStatus.ABSENT;
            break;
            case PAID: bookingStatus = BookingStatus.PAID;
            break;
            case RESCHEDULE: bookingStatus = BookingStatus.RESCHEDULE;
            break;
            case REVIEWED: bookingStatus = BookingStatus.REVIEWED;
            break;
            case PENDING_APPROVAL: bookingStatus = BookingStatus.PENDING_APPROVAL;
            break;
            case PENDING_PAYMENT: bookingStatus = BookingStatus.PENDING_PAYMENT;
            break;
            case RESCHEDULE_REJECTED: bookingStatus = BookingStatus.RESCHEDULE_REJECTED;
            break;
            case RESCHEDULE_ACCEPTED: bookingStatus = BookingStatus.RESCHEDULE_ACCEPTED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + bookingStatusEnum );
        }

        return bookingStatus;
    }

    protected StudentScheduleResponse.Calendar resultToCalendar(DbEducatorCalendar.Result result) {
        if ( result == null ) {
            return null;
        }

        StudentScheduleResponse.Calendar calendar = new StudentScheduleResponse.Calendar();

        calendar.setDate( result.getDate() );
        calendar.setHourStart( result.getHourStart() );
        calendar.setHourEnd( result.getHourEnd() );
        calendar.setBookingStatus( bookingStatusEnumToBookingStatus( result.getBookingStatus() ) );
        calendar.setMeetingUrl( result.getMeetingUrl() );
        calendar.setEducatorProfileId( result.getEducatorProfileId() );

        return calendar;
    }
}
