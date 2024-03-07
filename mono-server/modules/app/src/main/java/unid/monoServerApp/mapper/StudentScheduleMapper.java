package unid.monoServerApp.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerMeta.api.StudentScheduleResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                EnumMapper.class,
                I18nMapper.class,
                UserMapper.class
        }
)
public interface StudentScheduleMapper {

    @Mappings({
            @Mapping(source = DbStudentPaymentTransaction.ResultForList.Columns.createdOn, target = StudentScheduleResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbStudentPaymentTransaction.ResultForList.Columns.updatedOn, target = StudentScheduleResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    StudentScheduleResponse toResponse(DbStudentPaymentTransaction.ResultForList data);

    @Mappings({
            @Mapping(source = DbStudentPaymentTransaction.ResultForList.Columns.createdOn, target = StudentScheduleResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbStudentPaymentTransaction.ResultForList.Columns.updatedOn, target = StudentScheduleResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<StudentScheduleResponse> toResponse(List<DbStudentPaymentTransaction.ResultForList> data);
}
