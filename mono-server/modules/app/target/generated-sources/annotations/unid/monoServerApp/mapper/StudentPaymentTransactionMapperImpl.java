package unid.monoServerApp.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerMeta.api.StudentBookingEducatorCalendarRequest;
import unid.monoServerMeta.api.StudentPaymentTransactionResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-17T09:35:00+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class StudentPaymentTransactionMapperImpl implements StudentPaymentTransactionMapper {

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
    public StudentPaymentTransactionResponse toResponse(StudentPaymentTransactionPojo pojo) {
        if ( pojo == null ) {
            return null;
        }

        StudentPaymentTransactionResponse studentPaymentTransactionResponse = new StudentPaymentTransactionResponse();

        studentPaymentTransactionResponse.setId( pojo.getId() );

        return studentPaymentTransactionResponse;
    }
}
