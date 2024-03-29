package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerMeta.api.ConsultationSessionResponse;
import unid.monoServerMeta.api.EducatorCalendarRequest;
import unid.monoServerMeta.api.EducatorCalendarResponse;
import unid.monoServerMeta.api.EducatorCalendarSimpleResponse;
import unid.monoServerMeta.api.EducatorCalendarTimeSlotResponse;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.PaymentStatus;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-28T23:20:22+0800",
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
    public void merge(EducatorCalendarPojo data, EducatorCalendarRequest.TimeSlotPayload source) {
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
    public EducatorCalendarTimeSlotResponse toAvailableTimeSlotResponse(EducatorCalendarPojo data) {
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
    public EducatorCalendarSimpleResponse.TimeSlotPayload toSimpleResponse(DbEducatorCalendar.Result data) {
        if ( data == null ) {
            return null;
        }

        EducatorCalendarSimpleResponse.TimeSlotPayload timeSlotPayload = new EducatorCalendarSimpleResponse.TimeSlotPayload();

        timeSlotPayload.setStartDateTimeUtc( data.getStartTimeUtc() );
        timeSlotPayload.setEndDateTimeUtc( data.getEndTimeUtc() );
        timeSlotPayload.setId( data.getId() );
        timeSlotPayload.setStudentProfiles( resultListToStudentProfileSimplePayloadList( data.getStudentProfiles() ) );

        return timeSlotPayload;
    }

    @Override
    public List<EducatorCalendarSimpleResponse.TimeSlotPayload> toSimpleResponse(List<DbEducatorCalendar.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<EducatorCalendarSimpleResponse.TimeSlotPayload> list = new ArrayList<EducatorCalendarSimpleResponse.TimeSlotPayload>( data.size() );
        for ( DbEducatorCalendar.Result result : data ) {
            list.add( toSimpleResponse( result ) );
        }

        return list;
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

    @Override
    public ConsultationSessionResponse toSessionResponse(DbEducatorCalendar.Result data) {
        if ( data == null ) {
            return null;
        }

        ConsultationSessionResponse consultationSessionResponse = new ConsultationSessionResponse();

        consultationSessionResponse.setPaymentTransaction( resultToTransactionResponse( data.getPaymentTransaction() ) );
        consultationSessionResponse.setBookingStatus( bookingStatusEnumToBookingStatus( data.getBookingStatus() ) );

        return consultationSessionResponse;
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
            case RESCHEDULE_REJECTED: bookingStatusEnum = BookingStatusEnum.RESCHEDULE_REJECTED;
            break;
            case RESCHEDULE_ACCEPTED: bookingStatusEnum = BookingStatusEnum.RESCHEDULE_ACCEPTED;
            break;
            case VOID: bookingStatusEnum = BookingStatusEnum.VOID;
            break;
            case FINISHED: bookingStatusEnum = BookingStatusEnum.FINISHED;
            break;
            case UNFINISHED: bookingStatusEnum = BookingStatusEnum.UNFINISHED;
            break;
            case ATTEND: bookingStatusEnum = BookingStatusEnum.ATTEND;
            break;
            case ABSENT: bookingStatusEnum = BookingStatusEnum.ABSENT;
            break;
            case PAID: bookingStatusEnum = BookingStatusEnum.PAID;
            break;
            case RESCHEDULE: bookingStatusEnum = BookingStatusEnum.RESCHEDULE;
            break;
            case REVIEWED: bookingStatusEnum = BookingStatusEnum.REVIEWED;
            break;
            case PENDING_APPROVAL: bookingStatusEnum = BookingStatusEnum.PENDING_APPROVAL;
            break;
            case PENDING_PAYMENT: bookingStatusEnum = BookingStatusEnum.PENDING_PAYMENT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + bookingStatus );
        }

        return bookingStatusEnum;
    }

    protected I18n resultToI18n(DbI18N.Result result) {
        if ( result == null ) {
            return null;
        }

        I18n.I18nBuilder<?, ?> i18n = I18n.builder();

        i18n.english( result.getEnglish() );
        i18n.chineseTraditional( result.getChineseTraditional() );
        i18n.chineseSimplified( result.getChineseSimplified() );

        return i18n.build();
    }

    protected EducatorCalendarSimpleResponse.Country resultToCountry(DbCountry.Result result) {
        if ( result == null ) {
            return null;
        }

        EducatorCalendarSimpleResponse.Country country = new EducatorCalendarSimpleResponse.Country();

        country.setId( result.getId() );
        country.setNameI18n( resultToI18n( result.getNameI18n() ) );

        return country;
    }

    protected EducatorCalendarSimpleResponse.SecondarySchool resultToSecondarySchool(DbSchool.Result result) {
        if ( result == null ) {
            return null;
        }

        EducatorCalendarSimpleResponse.SecondarySchool secondarySchool = new EducatorCalendarSimpleResponse.SecondarySchool();

        secondarySchool.setId( result.getId() );
        secondarySchool.setNameI18n( resultToI18n( result.getNameI18n() ) );

        return secondarySchool;
    }

    protected EducatorCalendarSimpleResponse.SecondarySchoolEducationLevel resultToSecondarySchoolEducationLevel(DbEducationLevel.Result result) {
        if ( result == null ) {
            return null;
        }

        EducatorCalendarSimpleResponse.SecondarySchoolEducationLevel secondarySchoolEducationLevel = new EducatorCalendarSimpleResponse.SecondarySchoolEducationLevel();

        secondarySchoolEducationLevel.setGrade( result.getGrade() );

        return secondarySchoolEducationLevel;
    }

    protected EducatorCalendarSimpleResponse.StudentProfileSimplePayload resultToStudentProfileSimplePayload(DbStudentProfile.Result result) {
        if ( result == null ) {
            return null;
        }

        EducatorCalendarSimpleResponse.StudentProfileSimplePayload studentProfileSimplePayload = new EducatorCalendarSimpleResponse.StudentProfileSimplePayload();

        studentProfileSimplePayload.setId( result.getId() );
        studentProfileSimplePayload.setLastNameI18n( resultToI18n( result.getLastNameI18n() ) );
        studentProfileSimplePayload.setFirstNameI18n( resultToI18n( result.getFirstNameI18n() ) );
        studentProfileSimplePayload.setCountry( resultToCountry( result.getCountry() ) );
        studentProfileSimplePayload.setProfilePicture( result.getProfilePicture() );
        studentProfileSimplePayload.setSecondarySchool( resultToSecondarySchool( result.getSecondarySchool() ) );
        studentProfileSimplePayload.setSecondarySchoolEducationLevel( resultToSecondarySchoolEducationLevel( result.getSecondarySchoolEducationLevel() ) );

        return studentProfileSimplePayload;
    }

    protected List<EducatorCalendarSimpleResponse.StudentProfileSimplePayload> resultListToStudentProfileSimplePayloadList(List<DbStudentProfile.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<EducatorCalendarSimpleResponse.StudentProfileSimplePayload> list1 = new ArrayList<EducatorCalendarSimpleResponse.StudentProfileSimplePayload>( list.size() );
        for ( DbStudentProfile.Result result : list ) {
            list1.add( resultToStudentProfileSimplePayload( result ) );
        }

        return list1;
    }

    protected PaymentStatus paymentStatusEnumToPaymentStatus(PaymentStatusEnum paymentStatusEnum) {
        if ( paymentStatusEnum == null ) {
            return null;
        }

        PaymentStatus paymentStatus;

        switch ( paymentStatusEnum ) {
            case PENDING: paymentStatus = PaymentStatus.PENDING;
            break;
            case PAID: paymentStatus = PaymentStatus.PAID;
            break;
            case EXPIRED: paymentStatus = PaymentStatus.EXPIRED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + paymentStatusEnum );
        }

        return paymentStatus;
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

    protected ConsultationSessionResponse.TransactionResponse resultToTransactionResponse(DbStudentPaymentTransaction.Result result) {
        if ( result == null ) {
            return null;
        }

        ConsultationSessionResponse.TransactionResponse transactionResponse = new ConsultationSessionResponse.TransactionResponse();

        transactionResponse.setStudentProfileId( result.getStudentProfileId() );
        transactionResponse.setPaymentStatus( paymentStatusEnumToPaymentStatus( result.getPaymentStatus() ) );
        transactionResponse.setProcessStatus( bookingStatusEnumToBookingStatus( result.getProcessStatus() ) );

        return transactionResponse;
    }
}
