package unid.monoServerApp.api.language;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Hidden;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.coreRsqlJooq.jooq.PaginatedQuery;
import pwh.coreRsqlJooq.jooq.PaginatedQuerySorting;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreRsqlJooq.model.PaginationResponse;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.Constant;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.tag.TagService;
import unid.monoServerApp.database.table.language.DbLanguage;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.LanguageMapper;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Languages")
@Slf4j
public class LanguageController {
    private final LanguageService languageService;
    private final LanguageMapper languageMapper;
    private final DbLanguage dbLanguage;
    private final ObjectMapper objectMapper;
    private final TagService tagService;

    @GetMapping("/admin/language/page")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.ADMIN
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public UnifiedResponse<UniPageResponse<LanguagePayload>> page(
            @ParameterObject LanguagePageRequest request
    ){
        return UnifiedResponse.of(
                languageService.page(request)
        );
    }


    @GetMapping("/student/language/list")
    @ACL(
            authed = true,
            allowedRoles = UserRoleEnum.STUDENT
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get List"
    )
    public @Valid UnifiedResponse<List<TagResponse>> list() {
        return this.tags();
    }

    @GetMapping(value = {"/educator/language/tags","/admin/language/tags"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Tags"
    )
    public @Valid UnifiedResponse<List<TagResponse>> tags() {
        var result = tagService.list(TagCategoryEnum.LANGUAGE);
        return UnifiedResponse.of(
                result
        );
    }


    @GetMapping("/admin/language/{id}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public @Valid UnifiedResponse<LanguagePayload> get(
            @PathVariable("id") UUID id
    ) {
        return UnifiedResponse.of(
                languageService.get(id)
        );
    }

    @DeleteMapping("/admin/language/{id}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public @Valid UnifiedResponse<Void> delete(
            @PathVariable("id") UUID id
    ) {
        languageService.delete(id);
        return UnifiedResponse.of(
                null
        );
    }

    @PostMapping("/admin/language")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create One"
    )
    public @Valid UnifiedResponse<LanguagePayload> create(
            @RequestBody @Valid
            LanguagePayload payload
    ) {
        return UnifiedResponse.of(
                languageService.get(
                        languageService.create(payload)
                                .getId()
                )
        );
    }

    @PutMapping("/admin/language")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    public @Valid UnifiedResponse<LanguagePayload> update(
            @RequestBody @Valid
            LanguagePayload payload
    ) {
        return UnifiedResponse.of(
                languageService.get(
                        languageService.update(payload)
                                .getId()
                )
        );
    }
}
