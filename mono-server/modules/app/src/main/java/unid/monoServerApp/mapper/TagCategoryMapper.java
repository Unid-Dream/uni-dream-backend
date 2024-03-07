package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.monoServerMeta.model.TagCategory;

@Mapper(
        componentModel = "spring"
)
public interface TagCategoryMapper {
    TagCategoryEnum toRecord(TagCategory data);

    TagCategory toPojo(TagCategoryEnum data);
}
