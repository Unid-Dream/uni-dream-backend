package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerMeta.api.StudentBookingEducatorCalendarRequest;
import unid.monoServerMeta.api.StudentPaymentTransactionResponse;

@Mapper(
        componentModel = "spring"
)
public interface StudentPaymentTransactionMapper {

    @Mappings({
            @Mapping(target = StudentPaymentTransactionPojo.Columns.transactionItemRefId,source = StudentBookingEducatorCalendarRequest.Fields.educatorCalendarId)
    })
    StudentPaymentTransactionPojo toPojo(StudentBookingEducatorCalendarRequest request);


    StudentPaymentTransactionResponse toResponse(StudentPaymentTransactionPojo pojo);

}
