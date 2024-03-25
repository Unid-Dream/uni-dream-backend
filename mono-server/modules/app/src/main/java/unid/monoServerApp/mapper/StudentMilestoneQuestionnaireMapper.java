package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import unid.jooqMono.model.tables.pojos.StudentMilestoneQuestionnairePojo;
import unid.monoServerMeta.api.version2.StudentMilestoneQuestionnairePayload;
import unid.monoServerMeta.api.version2.request.StudentMilestoneQuestionnaireRequest;

@Mapper(
        componentModel = "spring"
)
public interface StudentMilestoneQuestionnaireMapper {


    void merge(@MappingTarget StudentMilestoneQuestionnairePojo pojo , StudentMilestoneQuestionnairePayload payload);

    @Mappings({
            @Mapping(target = StudentMilestoneQuestionnairePojo.Columns.id,ignore = true)
    })
    StudentMilestoneQuestionnairePojo toPojo(StudentMilestoneQuestionnairePayload payload);

}
