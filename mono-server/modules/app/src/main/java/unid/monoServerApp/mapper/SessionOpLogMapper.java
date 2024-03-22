package unid.monoServerApp.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import unid.jooqMono.model.tables.pojos.SessionOpLogPojo;
import unid.monoServerApp.model.SessionLogger;

@Mapper(
        componentModel = "spring"
)
public interface SessionOpLogMapper {

    @Mappings({
            @Mapping(target=SessionOpLogPojo.Columns.opType,source = SessionLogger.OpEvent.Fields.opType),
            @Mapping(target=SessionOpLogPojo.Columns.userId,source = SessionLogger.OpEvent.Fields.userId),
            @Mapping(target=SessionOpLogPojo.Columns.timeUtc,source = SessionLogger.OpEvent.Fields.timeUtc),
            @Mapping(target=SessionOpLogPojo.Columns.status,source = SessionLogger.OpEvent.Fields.status),
            @Mapping(target=SessionOpLogPojo.Columns.transactionId,source = SessionLogger.OpEvent.Fields.transactionId),
    })
    SessionOpLogPojo toPojo(SessionLogger.OpEvent opEvent);
}
