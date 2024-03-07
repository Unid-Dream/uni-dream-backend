package unid.monoServerApp.mapper;


import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
        }
)
public interface PassionMajorMapper {
}
