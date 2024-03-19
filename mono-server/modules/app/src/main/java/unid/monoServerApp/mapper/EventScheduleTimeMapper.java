package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.AcademicMajorPojo;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerApp.database.table.course.DbEventScheduleTime;
import unid.monoServerMeta.api.AcademicMajorRequest;
import unid.monoServerMeta.api.AcademicMajorResponse;
import unid.monoServerMeta.api.StudentPaymentTransactionResponse;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface EventScheduleTimeMapper {

    StudentPaymentTransactionResponse.EventScheduleTime toPojo(DbEventScheduleTime.Result data);
}
