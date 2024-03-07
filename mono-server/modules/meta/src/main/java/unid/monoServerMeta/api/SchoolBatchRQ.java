package unid.monoServerMeta.api;

import lombok.Data;
import unid.monoServerMeta.model.I18n;

@Data
public class SchoolBatchRQ {
    private I18n countryI18n;
    private I18n cityI18n;
    private I18n nameI18n;
    private I18n tagI18n;

    private String lng;
    private String lat;
    private String acceptanceRate;
    private String undergradPopulation;
    private String uFactor;
    private String tuition;

}
