package unid.monoServerMeta.api;


import lombok.Data;
import unid.monoServerMeta.model.I18n;


@Data
public class EducationResponse {
    //学历
    private EducationLevelResponse degree;
    //学校
    private I18n university;
}
