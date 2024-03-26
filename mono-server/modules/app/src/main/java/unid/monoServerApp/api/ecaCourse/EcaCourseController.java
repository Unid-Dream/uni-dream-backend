package unid.monoServerApp.api.ecaCourse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import unid.monoServerApp.api.educationLevel.EducationLevelPagination;
import unid.monoServerApp.database.table.educationLevel.DbEducationLevel;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.api.version2.request.EcaCoursePagePayload;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Eca Course")
@Slf4j
public class EcaCourseController {
    private final EcaCourseService ecaCourseService;

    @GetMapping(value={"/student/student/ecaCourse"})
    @ACL(
            authed = true,
            allowedRoles = { UserRoleEnum.STUDENT }
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<PageResponse<EcaCourseResponse>> list(
            @Valid
            @ParameterObject
            EcaCoursePageRequest payload
    ) {
         return UnifiedResponse.of(ecaCourseService.page(payload));
    }

    @GetMapping(value={"/admin/ecaCourse/page"})
    @ACL(
            authed = true,
            allowedRoles = { UserRoleEnum.ADMIN }
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Page"
    )
    public @Valid UnifiedResponse<UniPageResponse<EcaCourseResponse>> page(
            @Valid
            @ParameterObject
            EcaCoursePagePayload payload
    ) {
        return UnifiedResponse.of(ecaCourseService.page(payload));
    }


    @PostMapping("/admin/ecaCourse")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create"
    )
    public @Valid UnifiedResponse<Void> create(
            @Valid
            @RequestBody
            EcaCourseRequest payload
    ) {
        ecaCourseService.create(payload);
        return UnifiedResponse.of(null);
    }

    @PutMapping("/admin/ecaCourse")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update"
    )
    public @Valid UnifiedResponse<Void> update(
            @Valid
            @RequestBody
            EcaCourseRequest payload
    ) {
        ecaCourseService.create(payload);
        return UnifiedResponse.of(null);
    }


    @GetMapping("/admin/ecaCourse/{id}")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update"
    )
    public @Valid UnifiedResponse<Void> get(
            @PathVariable("id") @NotNull UUID id
    ) {
        ecaCourseService.get(id);
        return UnifiedResponse.of(null);
    }



    @DeleteMapping("/admin/ecaCourse/{id}")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Delete"
    )
    public @Valid UnifiedResponse<Void> delete(
            @PathVariable("id") @NotNull UUID id
    ) {
        ecaCourseService.delete(id);
        return UnifiedResponse.of(null);
    }
}
