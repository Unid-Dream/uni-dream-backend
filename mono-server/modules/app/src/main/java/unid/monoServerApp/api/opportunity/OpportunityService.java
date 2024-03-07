package unid.monoServerApp.api.opportunity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.opportunity.DbOpportunity;
import unid.monoServerApp.database.table.skill.DbStudentUploadedWriting;
import unid.monoServerApp.mapper.OpportunityMapper;
import unid.monoServerMeta.api.AcademicMajorI18nResponse;
import unid.monoServerMeta.api.EcaCourseResponse;
import unid.monoServerMeta.api.OpportunityResponse;
import unid.monoServerMeta.model.I18n;

import java.util.List;

import static org.jooq.impl.DSL.*;
import static unid.jooqMono.model.Tables.*;
import static unid.jooqMono.model.tables.EcaCourseTable.ECA_COURSE;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OpportunityService {
    private final DSLContext dslContext;
    private final OpportunityMapper opportunityMapper;

    public List<OpportunityResponse> list() {
        List<DbOpportunity.Result> list = dslContext.select()
                .from(OPPORTUNITY)
                .fetchInto(DbOpportunity.Result.class);
        list.forEach((e)->{
            List<DbI18N.Result> i18n = dslContext.select()
                    .from(I18N)
                    .where(I18N.ID.eq(e.getTitleI18nId()))
                    .fetchInto(DbI18N.Result.class);
            if(!i18n.isEmpty()){
                e.setTitleI18n(i18n.get(0));
            }
        });
        return opportunityMapper.toResponse(list);

    }
}
