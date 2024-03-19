package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerMeta.api.StudentBookingEducatorCalendarRequest;
import unid.monoServerMeta.api.StudentPaymentTransactionResponse;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface StudentPaymentTransactionMapper {

    @Mappings({
            @Mapping(target = StudentPaymentTransactionPojo.Columns.transactionItemRefId,source = StudentBookingEducatorCalendarRequest.Fields.educatorCalendarId)
    })
    StudentPaymentTransactionPojo toPojo(StudentBookingEducatorCalendarRequest request);


    StudentPaymentTransactionResponse toResponse(StudentPaymentTransactionPojo pojo);

    StudentPaymentTransactionResponse toResponse(DbStudentPaymentTransaction.Result data);

    List<StudentPaymentTransactionResponse> toResponse(List<DbStudentPaymentTransaction.Result> data);

}
