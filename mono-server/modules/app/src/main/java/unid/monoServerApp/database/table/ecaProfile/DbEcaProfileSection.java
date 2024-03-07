package unid.monoServerApp.database.table.ecaProfile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.EcaProfileSectionPojo;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;
import java.util.List;

@Component
public class DbEcaProfileSection{

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends EcaProfileSectionPojo implements Serializable {
        private DbI18N.Result section;
        private List<DbEcaProfileOption.Result> options;
    }
}
