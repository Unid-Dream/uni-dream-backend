package unid.monoServerApp.api.user.profile.student.schedule;//package unid.monoServerApp.api.country;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.jooq.SortOrder;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.coreRsqlJooq.jooq.PaginatedQuery;
import pwh.coreRsqlJooq.jooq.PaginatedQuerySorting;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreRsqlJooq.model.PaginationResponse;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.StudentTransactionItemEnum;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Constant;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.StudentScheduleMapper;
import unid.monoServerMeta.api.StudentScheduleResponse;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user/profile/student/{profileId}/schedule")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Student Schedule")
@Slf4j
public class StudentScheduleController {
    private final ObjectMapper objectMapper;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final StudentScheduleMapper studentScheduleMapper;
    private final DbUser dbUser;
    private final DbEducatorCalendar dbEducatorCalendar;
    private final StudentScheduleService studentScheduleService;

//    @GetMapping
//    @ACL(
//            authed = true,
//            allowedRoles = {UserRoleEnum.STUDENT, UserRoleEnum.ADMIN, UserRoleEnum.ROOT},
//            matchingSessionProfileId = true,
//            skipMatchingForAdministrative = true
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Query"
//    )
//    public @Valid UnifiedResponse<PaginationResponse<StudentScheduleResponse>> list(
//            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
//            @Valid
//            @ParameterObject
//            PaginationRequest payload
//    ) {
//        var table = StudentSchedulePagination.TABLE;
//        var dslContext = dbStudentPaymentTransaction.getDsl();
//        var extraConditionOnFirstPage = table.STUDENT_PROFILE_ID.eq(profileId)
//                .and(table.TRANSACTION_ITEM.eq(StudentTransactionItemEnum.EDUCATOR_SCHEDULE));
//        if (StringUtils.isBlank(payload.getPage())) {
//            extraConditionOnFirstPage = dbStudentPaymentTransaction.validateCondition(table);
//        }
//        var result = PaginatedQuery.init(
//                        dslContext,
//                        objectMapper,
//                        payload,
//                        RequestHolder.get().getAuthToken(),
//                        Constant.PAGINATION_MIN_SIZE,
//                        Constant.PAGINATION_MAX_SIZE_EDUCATOR_CALENDAR
//                )
//                .select(dsl -> dbStudentPaymentTransaction.getQueryForApiList(table, dbEducatorCalendar, dbUser)
//                )
//                .conditions(StudentSchedulePagination.conditionVisitor)
//                .extraConditions(extraConditionOnFirstPage)
//                .sortBy(
//                        StudentSchedulePagination.orderingVisitor,
//                        sort -> {
//                            sort.add(
//                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
//                                            table.CREATED_ON,
//                                            SortOrder.DESC,
//                                            OrderingVisitor.Seeking.builder()
//                                                    .whenSortByValues(input -> ListUtils.emptyIfNull(input).stream().map(LocalDate::parse).collect(Collectors.toList())
//                                                    )
//                                                    .whenSeeking(OffsetDateTime::parse)
//                                                    .build()
//                                    )
//                            );
//                        },
//                        null,
//                        null,
//                        uniqueSort -> {
//                            uniqueSort.add(
//                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
//                                            table.CREATED_ON,
//                                            SortOrder.DESC,
//                                            OrderingVisitor.Seeking.builder()
//                                                    .whenSeeking(OffsetDateTime::parse)
//                                                    .build()
//                                    )
//                            );
//                            uniqueSort.add(
//                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
//                                            table.ID,
//                                            SortOrder.DESC,
//                                            OrderingVisitor.Seeking.builder()
//                                                    .whenSeeking(UUID::fromString)
//                                                    .build()
//                                    )
//                            );
//                        }
//                )
//                .fetch();
//        return UnifiedResponse.of(
//                PaginationResponse.asResult(result, studentScheduleMapper.toResponse(result.getResult().into(DbStudentPaymentTransaction.ResultForList.class)))
//        );
//    }


    @GetMapping
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<JSONObject> list(
            @PathVariable("profileId") @ACL.ProfileId UUID profileId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return UnifiedResponse.of(studentScheduleService.page(profileId,startDate,endDate,1,10));
    }
}
