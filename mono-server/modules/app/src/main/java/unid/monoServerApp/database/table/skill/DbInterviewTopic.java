package unid.monoServerApp.database.table.skill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.InterviewTopicPojo;
import unid.jooqMono.model.tables.pojos.WritingTopicPojo;
import unid.monoServerApp.database.table.i18n.DbI18N;

import java.io.Serializable;
import java.util.List;

@Component
public class DbInterviewTopic {


    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @FieldNameConstants
    @ToString(callSuper = true)
    // (selectively) inherited from related jOOQ generated POJO
    // expanding foreign keys
    public static final class Result extends InterviewTopicPojo implements Serializable {
        private List<DbI18N.Result> items;
    }
}
