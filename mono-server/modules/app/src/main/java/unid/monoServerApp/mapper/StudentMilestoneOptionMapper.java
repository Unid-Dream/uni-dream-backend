package unid.monoServerApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import unid.jooqMono.model.tables.pojos.StudentMilestoneOptionsPojo;
import unid.jooqMono.model.tables.pojos.StudentMilestoneQuestionnairePojo;
import unid.monoServerMeta.api.version2.StudentMilestoneQuestionnairePayload;

@Mapper(
        componentModel = "spring"
)
public interface StudentMilestoneOptionMapper {


    void merge(@MappingTarget StudentMilestoneOptionsPojo pojo , StudentMilestoneQuestionnairePayload.StudentMilestoneAnswerPayload payload);

    @Mappings({
            @Mapping(target = StudentMilestoneQuestionnairePojo.Columns.id,ignore = true)
    })
    StudentMilestoneOptionsPojo toPojo(StudentMilestoneQuestionnairePayload.StudentMilestoneAnswerPayload payload);

}
