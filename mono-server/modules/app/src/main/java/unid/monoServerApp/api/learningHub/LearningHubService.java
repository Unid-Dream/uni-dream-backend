package unid.monoServerApp.api.learningHub;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.*;
import unid.jooqMono.model.tables.pojos.StudentPaymentTransactionPojo;
import unid.jooqMono.model.tables.pojos.StudentUploadedInterviewPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.api.academicMajor.AcademicMajorService;
import unid.monoServerApp.api.user.profile.educator.EducatorProfileService;
import unid.monoServerApp.config.Transaction;
import unid.monoServerApp.database.table.learningHub.DbLearningHub;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerMeta.api.AcademicMajorI18nResponse;
import unid.monoServerMeta.api.EducatorResponse;
import unid.monoServerMeta.api.EventEducatorProfileResponse;
import unid.monoServerMeta.api.LearningHubResponse;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.TransactionItem;

import java.time.OffsetDateTime;
import java.util.*;

import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;
import static unid.jooqMono.model.Tables.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class LearningHubService {
    private final EducatorProfileService educatorProfileService;
    private final DbLearningHub dbLearningHub;
    private final AcademicMajorService academicMajorService;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;

    public JSONObject page(Integer pageNumber, Integer pageSize, EventTypeEnum eventType) {
        JSONObject response = JSONUtil.createObj();

        int totalRecords = dbLearningHub.getQueryCnt().and(eventType == null?DSL.noCondition():EVENT.EVENT_TYPE.eq(eventType)).fetchOptionalInto(Integer.class).orElse(0);

        int totalPages = (totalRecords + pageSize - 1) / pageSize;

        List<LearningHubResponse> list = dbLearningHub.getQuery()
                .and(eventType == null?DSL.noCondition():EVENT.EVENT_TYPE.eq(eventType))
                .limit(pageSize)
                .offset((pageNumber - 1) * pageSize)
                .fetchInto(LearningHubResponse.class);
        for(LearningHubResponse hub : list){
            EducatorResponse educator = educatorProfileService.getCourseEducator(hub.getEducatorProfileId());
            AcademicMajorI18nResponse major = academicMajorService.getOneBy(hub.getAcademicMajorId());
            hub.setEducator(educator);
            hub.setAcademic(major);
        }

        response.set("totalRecords", totalRecords);
        response.set("pageNumber", pageNumber);
        response.set("totalPages", totalPages);
        response.set("pageSize", pageSize);
        response.set("result", list);

        return response;
    }


    public LearningHubResponse getOneBy(UUID id) {
        LearningHubResponse result = dbLearningHub.getQuery()
                .and(EVENT.ID.eq(id))
                .fetchOptional().orElseThrow(()-> Exceptions.notFound("Learning Hub Not Found"))
                .into(LearningHubResponse.class);
        result.setAcademic(academicMajorService.getOneBy(result.getAcademicMajorId()));
        result.setEducator(educatorProfileService.getCourseEducator(result.getEducatorProfileId()));
        return result;
    }


    //确认课程
    public UUID enroll(UUID id,UUID studentProfileId){
        LearningHubResponse hub = getOneBy(id);
        StudentTransactionItemEnum item = StudentTransactionItemEnum.valueOf(hub.getEventType());
        var transaction = new StudentPaymentTransactionPojo()
                .setStudentProfileId(studentProfileId)
                .setTransactionAmount(Objects.requireNonNull(hub.getPrice()))
                .setTransactionCurrency(CurrencyEnum.USD)
                .setTransactionItem(item)
                .setPaymentStatus(PaymentStatusEnum.PENDING)
                .setCreatedOn(OffsetDateTime.now())
                .setUpdatedOn(OffsetDateTime.now())
                .setTransactionItemRefId(id)
                .setPaymentMethod(PaymentMethodEnum.COD_ALIPAY_CN);
        dbStudentPaymentTransaction.getDao().insert();
        return transaction.getId();
    }
}
