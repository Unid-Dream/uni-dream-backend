package unid.monoServerApp.mapper;

import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.TagPojo;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerMeta.api.TagRequest;
import unid.monoServerMeta.api.TagResponse;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class
        }
)
public interface TagMapper {

    TagPojo toPojo(TagResponse data);

    void merge(@MappingTarget TagPojo data, TagRequest source);

    @Mappings({
            @Mapping(source = TagPojo.Columns.createdOn, target = TagResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = TagPojo.Columns.updatedOn, target = TagResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    TagResponse toResponse(TagPojo data);

    @Mappings({
            @Mapping(source = DbTag.Result.Columns.createdOn, target = TagResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbTag.Result.Columns.updatedOn, target = TagResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    TagResponse toResponse(DbTag.Result data);

    @Mappings({
            @Mapping(source = DbTag.Result.Columns.createdOn, target = TagResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbTag.Result.Columns.updatedOn, target = TagResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<TagResponse> toResponse(List<DbTag.Result> data);
}
