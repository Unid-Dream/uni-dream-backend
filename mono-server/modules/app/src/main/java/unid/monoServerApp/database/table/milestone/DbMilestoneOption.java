package unid.monoServerApp.database.table.milestone;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.StudentMilestoneOptionsPojo;
import unid.jooqMono.model.tables.pojos.StudentMilestoneQuestionnairePojo;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;

@Component
public class DbMilestoneOption {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends StudentMilestoneOptionsPojo implements Serializable {
        private DbI18N.Result answerI18n;
    }
}
