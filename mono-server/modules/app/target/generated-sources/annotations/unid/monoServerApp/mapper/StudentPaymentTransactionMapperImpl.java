package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.CurrencyEnum;
import unid.jooqMono.model.enums.GenderEnum;
import unid.jooqMono.model.enums.PaymentStatusEnum;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.database.table.course.DbEvent;
import unid.monoServerApp.database.table.course.DbEventScheduleTime;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteItem;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerMeta.api.EducatorProfileSimpleResponse;
import unid.monoServerMeta.api.ScheduleTransactionResponse;
import unid.monoServerMeta.api.StudentBookingEducatorCalendarRequest;
import unid.monoServerMeta.api.StudentPaymentTransactionResponse;
import unid.monoServerMeta.model.ApplicationApprovalEnum;
import unid.monoServerMeta.model.BookingStatus;
import unid.monoServerMeta.model.Currency;
import unid.monoServerMeta.model.Gender;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.PaymentStatus;
import unid.monoServerMeta.model.TransactionItem;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-21T11:02:08+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class StudentPaymentTransactionMapperImpl implements StudentPaymentTransactionMapper {

    @Autowired
    private EventScheduleTimeMapper eventScheduleTimeMapper;

    @Override
    public StudentPaymentTransactionPojo toPojo(StudentBookingEducatorCalendarRequest request) {
        if ( request == null ) {
            return null;
        }

        StudentPaymentTransactionPojo studentPaymentTransactionPojo = new StudentPaymentTransactionPojo();

        studentPaymentTransactionPojo.setTransactionItemRefId( request.getEducatorCalendarId() );

        return studentPaymentTransactionPojo;
    }

    @Override
    public ScheduleTransactionResponse toResponse(StudentPaymentTransactionPojo pojo) {
        if ( pojo == null ) {
            return null;
        }

        ScheduleTransactionResponse scheduleTransactionResponse = new ScheduleTransactionResponse();

        scheduleTransactionResponse.setId( pojo.getId() );

        return scheduleTransactionResponse;
    }

    @Override
    public StudentPaymentTransactionResponse toResponse(DbStudentPaymentTransaction.Result data) {
        if ( data == null ) {
            return null;
        }

        StudentPaymentTransactionResponse studentPaymentTransactionResponse = new StudentPaymentTransactionResponse();

        studentPaymentTransactionResponse.setId( data.getId() );
        studentPaymentTransactionResponse.setStudentProfile( simpleResultToStudentProfileResponse( data.getStudentProfile() ) );
        studentPaymentTransactionResponse.setTransactionAmount( data.getTransactionAmount() );
        studentPaymentTransactionResponse.setTransactionCurrency( currencyEnumToCurrency( data.getTransactionCurrency() ) );
        studentPaymentTransactionResponse.setPaymentStatus( paymentStatusEnumToPaymentStatus( data.getPaymentStatus() ) );
        studentPaymentTransactionResponse.setProcessStatus( bookingStatusEnumToBookingStatus( data.getProcessStatus() ) );
        studentPaymentTransactionResponse.setTransactionItem( studentTransactionItemEnumToTransactionItem( data.getTransactionItem() ) );
        studentPaymentTransactionResponse.setSession( simpleResultToConsultationSessionResponse( data.getSession() ) );
        studentPaymentTransactionResponse.setEvent( simpleResultToCourseEventResponse( data.getEvent() ) );

        return studentPaymentTransactionResponse;
    }

    @Override
    public List<StudentPaymentTransactionResponse> toResponse(List<DbStudentPaymentTransaction.Result> data) {
        if ( data == null ) {
            return null;
        }

        List<StudentPaymentTransactionResponse> list = new ArrayList<StudentPaymentTransactionResponse>( data.size() );
        for ( DbStudentPaymentTransaction.Result result : data ) {
            list.add( toResponse( result ) );
        }

        return list;
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

    protected StudentPaymentTransactionResponse.StudentProfileResponse simpleResultToStudentProfileResponse(DbStudentProfile.SimpleResult simpleResult) {
        if ( simpleResult == null ) {
            return null;
        }

        StudentPaymentTransactionResponse.StudentProfileResponse studentProfileResponse = new StudentPaymentTransactionResponse.StudentProfileResponse();

        studentProfileResponse.setId( simpleResult.getId() );
        studentProfileResponse.setFirstNameI18n( resultToI18n( simpleResult.getFirstNameI18n() ) );
        studentProfileResponse.setLastNameI18n( resultToI18n( simpleResult.getLastNameI18n() ) );

        return studentProfileResponse;
    }

    protected Currency currencyEnumToCurrency(CurrencyEnum currencyEnum) {
        if ( currencyEnum == null ) {
            return null;
        }

        Currency currency;

        switch ( currencyEnum ) {
            case USD: currency = Currency.USD;
            break;
            case HKD: currency = Currency.HKD;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + currencyEnum );
        }

        return currency;
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
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + bookingStatusEnum );
        }

        return bookingStatus;
    }

    protected TransactionItem studentTransactionItemEnumToTransactionItem(StudentTransactionItemEnum studentTransactionItemEnum) {
        if ( studentTransactionItemEnum == null ) {
            return null;
        }

        TransactionItem transactionItem;

        switch ( studentTransactionItemEnum ) {
            case EDUCATOR_SCHEDULE: transactionItem = TransactionItem.EDUCATOR_SCHEDULE;
            break;
            case WRITING: transactionItem = TransactionItem.WRITING;
            break;
            case INTERVIEW: transactionItem = TransactionItem.INTERVIEW;
            break;
            case WEBINAR: transactionItem = TransactionItem.WEBINAR;
            break;
            case COURSE: transactionItem = TransactionItem.COURSE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + studentTransactionItemEnum );
        }

        return transactionItem;
    }

    protected List<String> stringArrayToStringList(String[] stringArray) {
        if ( stringArray == null ) {
            return null;
        }

        List<String> list = new ArrayList<String>( stringArray.length );
        for ( String string : stringArray ) {
            list.add( string );
        }

        return list;
    }

    protected List<UUID> uUIDArrayToUUIDList(UUID[] uUIDArray) {
        if ( uUIDArray == null ) {
            return null;
        }

        List<UUID> list = new ArrayList<UUID>( uUIDArray.length );
        for ( UUID uUID : uUIDArray ) {
            list.add( uUID );
        }

        return list;
    }

    protected ApplicationApprovalEnum applicationApprovalEnumToApplicationApprovalEnum(unid.jooqMono.model.enums.ApplicationApprovalEnum applicationApprovalEnum) {
        if ( applicationApprovalEnum == null ) {
            return null;
        }

        ApplicationApprovalEnum applicationApprovalEnum1;

        switch ( applicationApprovalEnum ) {
            case PENDING: applicationApprovalEnum1 = ApplicationApprovalEnum.PENDING;
            break;
            case APPROVED: applicationApprovalEnum1 = ApplicationApprovalEnum.APPROVED;
            break;
            case REJECTED: applicationApprovalEnum1 = ApplicationApprovalEnum.REJECTED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + applicationApprovalEnum );
        }

        return applicationApprovalEnum1;
    }

    protected Gender genderEnumToGender(GenderEnum genderEnum) {
        if ( genderEnum == null ) {
            return null;
        }

        Gender gender;

        switch ( genderEnum ) {
            case MALE: gender = Gender.MALE;
            break;
            case FEMALE: gender = Gender.FEMALE;
            break;
            case SECRET: gender = Gender.SECRET;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + genderEnum );
        }

        return gender;
    }

    protected EducatorProfileSimpleResponse simpleResultToEducatorProfileSimpleResponse(DbEducatorProfile.SimpleResult simpleResult) {
        if ( simpleResult == null ) {
            return null;
        }

        EducatorProfileSimpleResponse educatorProfileSimpleResponse = new EducatorProfileSimpleResponse();

        educatorProfileSimpleResponse.setId( simpleResult.getId() );
        educatorProfileSimpleResponse.setFirstNameI18n( resultToI18n( simpleResult.getFirstNameI18n() ) );
        educatorProfileSimpleResponse.setLastNameI18n( resultToI18n( simpleResult.getLastNameI18n() ) );
        educatorProfileSimpleResponse.setCountryId( simpleResult.getCountryId() );
        educatorProfileSimpleResponse.setTimezone( simpleResult.getTimezone() );
        educatorProfileSimpleResponse.setDescription( simpleResult.getDescription() );
        educatorProfileSimpleResponse.setExpertiseDescription( stringArrayToStringList( simpleResult.getExpertiseDescription() ) );
        educatorProfileSimpleResponse.setExpertiseId( uUIDArrayToUUIDList( simpleResult.getExpertiseId() ) );
        educatorProfileSimpleResponse.setLanguageId( uUIDArrayToUUIDList( simpleResult.getLanguageId() ) );
        educatorProfileSimpleResponse.setProfilePicture( simpleResult.getProfilePicture() );
        educatorProfileSimpleResponse.setHourlyRate( simpleResult.getHourlyRate() );
        educatorProfileSimpleResponse.setApplicationApproval( applicationApprovalEnumToApplicationApprovalEnum( simpleResult.getApplicationApproval() ) );
        educatorProfileSimpleResponse.setPhoneCountryCode( simpleResult.getPhoneCountryCode() );
        educatorProfileSimpleResponse.setPhone( simpleResult.getPhone() );
        educatorProfileSimpleResponse.setGender( genderEnumToGender( simpleResult.getGender() ) );
        educatorProfileSimpleResponse.setEmail( simpleResult.getEmail() );

        return educatorProfileSimpleResponse;
    }

    protected StudentPaymentTransactionResponse.SessionCommentItem resultToSessionCommentItem(DbEducatorSessionNoteItem.Result result) {
        if ( result == null ) {
            return null;
        }

        StudentPaymentTransactionResponse.SessionCommentItem sessionCommentItem = new StudentPaymentTransactionResponse.SessionCommentItem();

        sessionCommentItem.setTitleI18n( resultToI18n( result.getTitleI18n() ) );
        sessionCommentItem.setComment( result.getComment() );

        return sessionCommentItem;
    }

    protected List<StudentPaymentTransactionResponse.SessionCommentItem> resultListToSessionCommentItemList(List<DbEducatorSessionNoteItem.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentPaymentTransactionResponse.SessionCommentItem> list1 = new ArrayList<StudentPaymentTransactionResponse.SessionCommentItem>( list.size() );
        for ( DbEducatorSessionNoteItem.Result result : list ) {
            list1.add( resultToSessionCommentItem( result ) );
        }

        return list1;
    }

    protected StudentPaymentTransactionResponse.ConsultationSessionResponse simpleResultToConsultationSessionResponse(DbEducatorCalendar.SimpleResult simpleResult) {
        if ( simpleResult == null ) {
            return null;
        }

        StudentPaymentTransactionResponse.ConsultationSessionResponse consultationSessionResponse = new StudentPaymentTransactionResponse.ConsultationSessionResponse();

        consultationSessionResponse.setId( simpleResult.getId() );
        consultationSessionResponse.setEducatorProfile( simpleResultToEducatorProfileSimpleResponse( simpleResult.getEducatorProfile() ) );
        consultationSessionResponse.setComments( resultListToSessionCommentItemList( simpleResult.getComments() ) );
        consultationSessionResponse.setStartTimeUtc( simpleResult.getStartTimeUtc() );
        consultationSessionResponse.setEndTimeUtc( simpleResult.getEndTimeUtc() );
        consultationSessionResponse.setMeetingUrl( simpleResult.getMeetingUrl() );
        consultationSessionResponse.setMeetingId( simpleResult.getMeetingId() );
        consultationSessionResponse.setBookingStatus( bookingStatusEnumToBookingStatus( simpleResult.getBookingStatus() ) );

        return consultationSessionResponse;
    }

    protected List<StudentPaymentTransactionResponse.EventScheduleTime> resultListToEventScheduleTimeList(List<DbEventScheduleTime.Result> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentPaymentTransactionResponse.EventScheduleTime> list1 = new ArrayList<StudentPaymentTransactionResponse.EventScheduleTime>( list.size() );
        for ( DbEventScheduleTime.Result result : list ) {
            list1.add( eventScheduleTimeMapper.toPojo( result ) );
        }

        return list1;
    }

    protected StudentPaymentTransactionResponse.CourseEventResponse simpleResultToCourseEventResponse(DbEvent.SimpleResult simpleResult) {
        if ( simpleResult == null ) {
            return null;
        }

        StudentPaymentTransactionResponse.CourseEventResponse courseEventResponse = new StudentPaymentTransactionResponse.CourseEventResponse();

        courseEventResponse.setId( simpleResult.getId() );
        courseEventResponse.setTitleI18n( resultToI18n( simpleResult.getTitleI18n() ) );
        courseEventResponse.setDescriptionI18n( resultToI18n( simpleResult.getDescriptionI18n() ) );
        if ( simpleResult.getEventType() != null ) {
            courseEventResponse.setEventType( simpleResult.getEventType().name() );
        }
        if ( simpleResult.getMaxNumberOfStudent() != null ) {
            courseEventResponse.setMaxNumberOfStudent( simpleResult.getMaxNumberOfStudent().intValue() );
        }
        courseEventResponse.setPosterImage( simpleResult.getPosterImage() );
        courseEventResponse.setEducator( simpleResultToEducatorProfileSimpleResponse( simpleResult.getEducator() ) );
        courseEventResponse.setAgendaI18n( resultToI18n( simpleResult.getAgendaI18n() ) );
        if ( simpleResult.getEventStatus() != null ) {
            courseEventResponse.setEventStatus( simpleResult.getEventStatus().name() );
        }
        courseEventResponse.setEventScheduleTimes( resultListToEventScheduleTimeList( simpleResult.getEventScheduleTimes() ) );

        return courseEventResponse;
    }
}
