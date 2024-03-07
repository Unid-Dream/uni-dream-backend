package unid.monoServerApp.api.ecaCourse;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.tables.pojos.PassionMajorPojo;
import unid.monoServerApp.database.table.opportunity.DbOpportunity;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.I18n;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.jooq.impl.DSL.*;
import static org.jooq.util.postgres.PostgresDSL.arrayOverlap;
import static unid.jooqMono.model.Tables.*;
import static unid.jooqMono.model.tables.EcaCourseTable.ECA_COURSE;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EcaCourseService {
    private final DSLContext dslContext;


    public PageResponse<EcaCourseResponse> page(EcaCoursePageRequest payload){
        var opportunitiesQueryCondition = dslContext
                .select(OPPORTUNITY.ID)
                .from(OPPORTUNITY,I18N)
                .where(OPPORTUNITY.TITLE_I18N_ID.eq(I18N.ID))
                .and(I18N.ENGLISH.in(payload.getOpportunities()).or(I18N.CHINESE_SIMPLIFIED.in(payload.getOpportunities())).or(I18N.CHINESE_TRADITIONAL.in(payload.getOpportunities())))
                .fetchInto(DbOpportunity.Result.class)
                .stream()
                .map(DbOpportunity.Result::getId)
                .toArray(UUID[]::new);

        var gradeQueryCondition = payload.getGrades().toArray(Integer[]::new);

        var academicQueryCondition = dslContext
                .select(PASSION_MAJOR.ID)
                .from(PASSION_MAJOR,I18N)
                .where(PASSION_MAJOR.NAME_I18N_ID.eq(I18N.ID))
                .and(I18N.ENGLISH.in(payload.getAcademics()).or(I18N.CHINESE_SIMPLIFIED.in(payload.getAcademics())).or(I18N.CHINESE_TRADITIONAL.in(payload.getAcademics())))
                .fetchInto(PassionMajorPojo.class)
                .stream()
                .map(PassionMajorPojo::getId)
                .toArray(UUID[]::new);


        int totalRecords = dslContext.selectCount()
                .from(ECA_COURSE)
                .where(payload.getOpportunities().isEmpty()?DSL.noCondition():arrayOverlap(ECA_COURSE.OPPORTUNITY_ID,opportunitiesQueryCondition))
                .and(payload.getGrades().isEmpty()?DSL.noCondition():arrayOverlap(ECA_COURSE.GRADE,gradeQueryCondition))
                .and(payload.getAcademics().isEmpty()?DSL.noCondition():arrayOverlap(ECA_COURSE.ACADEMIC_ID,academicQueryCondition))
                .fetchOptionalInto(Integer.class).orElse(0);

        int totalPages = (totalRecords + payload.getPageSize() - 1) / payload.getPageSize();



        List<EcaCourseResponse> list = dslContext.select(
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(ECA_COURSE.TITLE_I18N_ID))
                        ).as(EcaCourseResponse.Fields.titleI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(ECA_COURSE.DESCRIPTION_I18N_ID))
                        ).as(EcaCourseResponse.Fields.descriptionI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                        multiset(
                                select(I18N.fields())
                                        .from(I18N)
                                        .where(I18N.ID.eq(ECA_COURSE.ELIGIBILITY_I18N_ID))
                        ).as(EcaCourseResponse.Fields.eligibilityI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(I18n.class)),
                       multiset(
                               select(I18N.fields())
                                       .from(I18N,OPPORTUNITY)
                                       .where(I18N.ID.eq(OPPORTUNITY.TITLE_I18N_ID).and(OPPORTUNITY.ID.eq(any(ECA_COURSE.OPPORTUNITY_ID))))
                       ).as(EcaCourseResponse.Fields.opportunityI18ns).convertFrom(r -> r.isEmpty() ? null : r.into(I18n.class)),
                        ECA_COURSE.REF_URL,
                        ECA_COURSE.COVER_IMAGE,
                        ECA_COURSE.GRADE,
                        ECA_COURSE.ID,
                        multiset(
                                select(I18N.fields())
                                        .from(I18N,PASSION_MAJOR)
                                        .where(I18N.ID.eq(PASSION_MAJOR.NAME_I18N_ID).and(PASSION_MAJOR.ID.eq(any(ECA_COURSE.ACADEMIC_ID))))
                        ).as(EcaCourseResponse.Fields.academicI18ns).convertFrom(r -> r.isEmpty() ? null : r.into(I18n.class))

                        )
                .from(ECA_COURSE)
                .where(payload.getOpportunities().isEmpty()?DSL.noCondition():arrayOverlap(ECA_COURSE.OPPORTUNITY_ID,opportunitiesQueryCondition))
                .and(payload.getGrades().isEmpty()?DSL.noCondition():arrayOverlap(ECA_COURSE.GRADE,gradeQueryCondition))
                .and(payload.getAcademics().isEmpty()?DSL.noCondition():arrayOverlap(ECA_COURSE.ACADEMIC_ID,academicQueryCondition))
                .offset((payload.getPageNumber() - 1) * payload.getPageSize())
                .limit(payload.getPageSize())
                .fetchInto(EcaCourseResponse.class);

        return new PageResponse<>(
                totalRecords,
                payload.getPageNumber(),
                payload.getPageSize(),
                totalPages,
                list
        );
    }
}
