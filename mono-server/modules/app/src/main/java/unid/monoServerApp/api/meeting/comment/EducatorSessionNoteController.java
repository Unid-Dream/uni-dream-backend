package unid.monoServerApp.api.meeting.comment;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.coreRsqlJooq.jooq.PaginatedQuery;
import pwh.coreRsqlJooq.jooq.PaginatedQuerySorting;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreRsqlJooq.model.PaginationResponse;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Constant;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.school.SchoolPagination;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNote;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.EducatorSessionNoteMapper;
import unid.monoServerApp.service.EmailService;
import unid.monoServerMeta.api.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/meeting/educator/sessionNote")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Educator Session Note")
@Slf4j
public class EducatorSessionNoteController {
    private final EducatorSessionNoteService educatorSessionNoteService;
    private final EducatorSessionNoteMapper educatorSessionNoteMapper;
    private final DbEducatorSessionNote dbEducatorSessionNote;
    private final ObjectMapper objectMapper;

    @GetMapping
    @ACL(
            authed = true,
            // TODO remove EDUCATOR
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT, UserRoleEnum.EDUCATOR}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<PaginationResponse<EducatorSessionNoteResponse>> list(
            @Valid
            @ParameterObject
            PaginationRequest payload
    ) {
        var table = EducatorSessionNotePagination.TABLE;
        var dslContext = dbEducatorSessionNote.getDsl();
        var extraConditionOnFirstPage = DSL.noCondition();
        if (StringUtils.isBlank(payload.getPage())) {
            extraConditionOnFirstPage = dbEducatorSessionNote.validateCondition(table);
        }
        var result = PaginatedQuery.init(
                        dslContext,
                        objectMapper,
                        payload,
                        RequestHolder.get().getAuthToken(),
                        Constant.PAGINATION_MIN_SIZE,
                        Constant.PAGINATION_MAX_SIZE
                )
                .select(dsl -> dbEducatorSessionNote.getQuery(table))
                .conditions(EducatorSessionNotePagination.conditionVisitor)
                .extraConditions(extraConditionOnFirstPage)
                .sortBy(
                        EducatorSessionNotePagination.orderingVisitor,
                        null,
                        null,
                        null,
                        uniqueSort -> {
                            uniqueSort.add(
                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
                                            table.CREATED_ON,
                                            SortOrder.DESC,
                                            OrderingVisitor.Seeking.builder()
                                                    .whenSeeking(OffsetDateTime::parse)
                                                    .build()
                                    )
                            );
                            uniqueSort.add(
                                    new PaginatedQuerySorting.ExtraOrUniqueSort(
                                            table.ID,
                                            SortOrder.DESC,
                                            OrderingVisitor.Seeking.builder()
                                                    .whenSeeking(UUID::fromString)
                                                    .build()
                                    )
                            );
                        }
                )
                .fetch();
        return UnifiedResponse.of(
                PaginationResponse.asResult(result, educatorSessionNoteMapper.toResponse(result.getResult().into(DbEducatorSessionNote.Result.class)))
        );
    }

    @GetMapping("{id}")
    @ACL(
            authed = true,
            // TODO remove EDUCATOR
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT, UserRoleEnum.EDUCATOR}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public @Valid UnifiedResponse<EducatorSessionNoteResponse> get(
            @PathVariable("id") UUID id
    ) {
        var result = educatorSessionNoteService.get(id);
        return UnifiedResponse.of(
                educatorSessionNoteMapper.toResponse(result)
        );
    }

    @PostMapping
    @Transactional
    @ACL(
            authed = true,
            // TODO remove EDUCATOR
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT, UserRoleEnum.EDUCATOR}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create"
    )
    public @Valid UnifiedResponse<EducatorSessionNoteResponse> create(
            @RequestBody @Valid
            EducatorSessionNoteRequest payload) {
        var result = educatorSessionNoteService.get(
                educatorSessionNoteService.create(payload)
                        .getId()
        );
        var resp = educatorSessionNoteMapper.toResponse(result);
        return UnifiedResponse.of(
                resp
        );
    }

    @PutMapping("{id}")
    @Transactional
    @ACL(
            authed = true,
            // TODO remove EDUCATOR
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT, UserRoleEnum.EDUCATOR}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update"
    )
    public @Valid UnifiedResponse<EducatorSessionNoteResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid
            EducatorSessionNoteRequest payload
    ) {
        var result = educatorSessionNoteService.get(
                educatorSessionNoteService.update(id, payload)
                        .getId()
        );
        var resp = educatorSessionNoteMapper.toResponse(result);
        return UnifiedResponse.of(
                resp
        );
    }

    @DeleteMapping("{id}")
    @Transactional
    @ACL(
            authed = true,
            // TODO remove EDUCATOR
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT, UserRoleEnum.EDUCATOR}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Obsolete"
    )
    public @Valid UnifiedResponse<EducatorSessionNoteResponse> obsolete(
            @PathVariable UUID id) {
        var result = educatorSessionNoteService.get(
                educatorSessionNoteService.obsolete(id)
                        .getId()
        );
        var resp = educatorSessionNoteMapper.toResponse(result);
        return UnifiedResponse.of(
                resp
        );
    }
}
