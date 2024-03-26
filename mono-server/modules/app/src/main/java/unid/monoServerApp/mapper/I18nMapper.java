package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.monoServerMeta.model.I18n;

import java.util.UUID;

@Mapper(
        componentModel = "spring"
)
public interface I18nMapper {
    I18nPojo toPojo(I18n data);

    I18n toModel(I18nPojo data);

    void merge(@MappingTarget I18nPojo data, I18n source);


    void merge(@MappingTarget I18nPojo data, I18n source, UUID id);
}
