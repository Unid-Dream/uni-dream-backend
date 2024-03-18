package unid.monoServerApp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerMeta.api.StudentScheduleResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T20:54:49+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.20.1 (Amazon.com Inc.)"
)
@Component
public class StudentScheduleMapperImpl implements StudentScheduleMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public StudentScheduleResponse toResponse(DbStudentPaymentTransaction.ResultForList data) {
        if ( data == null ) {
            return null;
        }

        StudentScheduleResponse studentScheduleResponse = new StudentScheduleResponse();

        studentScheduleResponse.setCreatedOnUtc( commonMapper.toEpochMilli( data.getCreatedOn() ) );
        studentScheduleResponse.setUpdatedOnUtc( commonMapper.toEpochMilli( data.getUpdatedOn() ) );

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
}
