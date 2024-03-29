package unid.monoServerApp.api.academicSubject;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Hidden;
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
import unid.monoServerApp.database.table.academicSubject.DbAcademicSubject;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.AcademicSubjectMapper;
import unid.monoServerMeta.api.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Academic Subjects")
@Slf4j
public class AcademicSubjectController {
    private final AcademicSubjectService academicSubjectService;
    private final AcademicSubjectMapper academicSubjectMapper;
    private final DbAcademicSubject dbAcademicSubject;
    private final ObjectMapper objectMapper;


    @GetMapping("admin/academicSubject/page")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<AcademicSubjectPayload>> page(
            @Valid
            @ParameterObject
            AcademicSubjectPageRequest request
    ) {
        return UnifiedResponse.of(
                academicSubjectService.page(request)
        );
    }


    @GetMapping("admin/academicSubject/{id}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public @Valid UnifiedResponse<AcademicSubjectPayload> get(
            @PathVariable("id") UUID id) {
        return UnifiedResponse.of(
                academicSubjectService.get(id)
        );
    }

    @PostMapping("admin/academicSubject")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create One"
    )
    public @Valid UnifiedResponse<AcademicSubjectPayload> create(
            @RequestBody @Valid
            AcademicSubjectPayload payload
    ) {
        var pojo = academicSubjectService.create(payload);
        return UnifiedResponse.of(
                academicSubjectService.get(pojo.getId())
        );
    }

    @PutMapping("/admin/academicSubject")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    public @Valid UnifiedResponse<AcademicSubjectPayload> update(
            @RequestBody @Valid AcademicSubjectPayload req
    ) {
        var payload = academicSubjectService.get(
                academicSubjectService.update(req).getId()
        );
        return UnifiedResponse.of(
                payload
        );
    }


    @DeleteMapping("/admin/academicSubject/{id}")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    public @Valid UnifiedResponse<Void> delete(
            @PathVariable("id") UUID id
    ) {
        academicSubjectService.delete(id);
        return UnifiedResponse.of(null);
    }
}
