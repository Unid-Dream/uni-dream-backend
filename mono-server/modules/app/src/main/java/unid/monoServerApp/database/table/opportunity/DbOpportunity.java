package unid.monoServerApp.database.table.opportunity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.OpportunityPojo;
import unid.jooqMono.model.tables.pojos.SchoolPojo;
import unid.monoServerApp.database.table.city.DbCity;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerMeta.model.I18n;

import java.io.Serializable;

@Component
public class DbOpportunity {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends OpportunityPojo implements Serializable {
        private DbI18N.Result titleI18n;
    }
}
