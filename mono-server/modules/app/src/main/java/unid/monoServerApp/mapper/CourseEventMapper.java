package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import unid.jooqMono.model.tables.pojos.EventPojo;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.monoServerMeta.api.PromotionEventPayload;
import unid.monoServerMeta.model.I18n;

@Mapper(
        componentModel = "spring"
)
public interface CourseEventMapper {

    void merge(@MappingTarget EventPojo data, PromotionEventPayload source);
}
