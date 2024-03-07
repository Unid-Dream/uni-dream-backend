package unid.monoServerApp.database.table.milestone;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.OpportunityPojo;
import unid.jooqMono.model.tables.pojos.StudentMilestoneQuestionnairePojo;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;

@Component
public class DbMilestoneQuestionnaire {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends StudentMilestoneQuestionnairePojo implements Serializable {
        private DbI18N.Result questionI18n;
    }
}
