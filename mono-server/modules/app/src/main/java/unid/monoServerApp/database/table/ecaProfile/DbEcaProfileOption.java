package unid.monoServerApp.database.table.ecaProfile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.EcaProfileOptionPojo;
import unid.jooqMono.model.tables.pojos.EcaProfileSectionPojo;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Component
public class DbEcaProfileOption {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends EcaProfileOptionPojo implements Serializable {
        private DbI18N.Result optionI18n;
    }
}
