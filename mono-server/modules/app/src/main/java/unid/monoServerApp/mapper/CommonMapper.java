package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;

import java.time.OffsetDateTime;

@Mapper(
        componentModel = "spring"
)
public interface CommonMapper {
    default Long toEpochMilli(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toInstant().toEpochMilli();
    }
}
