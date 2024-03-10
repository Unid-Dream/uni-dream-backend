package unid.monoServerApp.mapper;

import org.apache.commons.compress.utils.Lists;
import org.mapstruct.*;
import unid.jooqMono.model.tables.pojos.AcademicSubjectPojo;
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerMeta.api.AcademicSubjectRequest;
import unid.monoServerMeta.api.AcademicSubjectResponse;
import unid.monoServerMeta.model.I18n;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CommonMapper.class,
                I18nMapper.class,
                TagMapper.class,
                AcademicSubjectResourceMapper.class
        }
)
public interface AcademicSubjectMapper {

    AcademicSubjectPojo toPojo(AcademicSubjectResponse data);

    void merge(@MappingTarget DbAcademicSubject.Result data, AcademicSubjectRequest source);

    void merge(@MappingTarget AcademicSubjectPojo data, AcademicSubjectRequest source);

    @Mappings({
            @Mapping(source = DbAcademicSubject.Result.Columns.createdOn, target = AcademicSubjectResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbAcademicSubject.Result.Columns.updatedOn, target = AcademicSubjectResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    AcademicSubjectResponse toResponse(DbAcademicSubject.Result data);

    @AfterMapping
    default void mapFieldsToList(DbAcademicSubject.Result source, @MappingTarget AcademicSubjectResponse target,I18nMapper i18nMapper) {
        List<I18n> list = Lists.newArrayList();
        if(source.getDescriptionMasterDegreeI18n() != null) {
            list.add(i18nMapper.toModel(source.getDescriptionMasterDegreeI18n()));
        }
        if(source.getDescriptionPhdI18n() != null) {
            list.add(i18nMapper.toModel(source.getDescriptionPhdI18n()));
        }
        target.setAnswers(list);
    }

    @Mappings({
            @Mapping(source = DbAcademicSubject.Result.Columns.createdOn, target = AcademicSubjectResponse.BaseResponseFields.createdOnUtc),
            @Mapping(source = DbAcademicSubject.Result.Columns.updatedOn, target = AcademicSubjectResponse.BaseResponseFields.updatedOnUtc)
    })
    @InheritConfiguration
    List<AcademicSubjectResponse> toResponse(List<DbAcademicSubject.Result> data);
}
