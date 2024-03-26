package unid.monoServerApp.api.country;//package unid.monoServerApp.api.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.impl.DSL;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.api.tag.TagService;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.mapper.CountryMapper;
import unid.monoServerMeta.api.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.jooq.impl.DSL.*;
import static unid.jooqMono.model.Tables.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Countries")
@Slf4j
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;
    private final DbCountry dbCountry;
    private final ObjectMapper objectMapper;
    private final TagService tagService;


    @GetMapping(value = {"admin/country/page"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<UniPageResponse<CountryPayload>> page(
            @ParameterObject CountryPageRequest request
    ) {
         return UnifiedResponse.of(
                 countryService.page(request)
         );
    }


    @GetMapping(value = {"student/country/list"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<List<CountryResponse>> list() {
        List<DbCountry.Result> list = dbCountry.getDsl().select(
                        COUNTRY.ID,
                        COUNTRY.NAME_I18N_ID,
                        multiset(
                                DSL.select(I18N.asterisk()).from(I18N).where(I18N.ID.eq(COUNTRY.NAME_I18N_ID))
                        ).as(DbCountry.Result.Fields.nameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
        ).from(COUNTRY,I18N).where(I18N.CHINESE_SIMPLIFIED.isNotNull().and(I18N.ID.eq(COUNTRY.NAME_I18N_ID)))
                .groupBy(COUNTRY.ID,COUNTRY.NAME_I18N_ID)
                .fetchInto(DbCountry.Result.class);
        return UnifiedResponse.of(countryMapper.toResponse(list));
    }


    @GetMapping(value = {"educator/country/tags","admin/country/tags"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<List<TagResponse>> tags() {
//        List<DbCountry.Result> list = dbCountry.getDsl().select(
//                        COUNTRY.ID,
//                        COUNTRY.NAME_I18N_ID,
//                        multiset(
//                                DSL.select(I18N.asterisk()).from(I18N).where(I18N.ID.eq(COUNTRY.NAME_I18N_ID))
//                        ).as(DbCountry.Result.Fields.nameI18n).convertFrom(r -> r.isEmpty() ? null : r.get(0).into(DbI18N.Result.class))
//                ).from(COUNTRY,I18N).where(I18N.CHINESE_SIMPLIFIED.isNotNull().and(I18N.ID.eq(COUNTRY.NAME_I18N_ID)))
//                .groupBy(COUNTRY.ID,COUNTRY.NAME_I18N_ID)
//                .fetchInto(DbCountry.Result.class);
        var list = tagService.list(TagCategoryEnum.COUNTRY);
        return UnifiedResponse.of(list);
    }

//    @GetMapping("{id}")
//    @ACL(
//            authed = true
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Get One"
//    )
//    @Hidden
//    public @Valid UnifiedResponse<CountryResponse> get(
//            @PathVariable("id") UUID id
//    ) {
//        var result = countryService.get(id);
//        return UnifiedResponse.of(
//                countryMapper.toResponse(result)
//        );
//    }

    @PostMapping("admin/country")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create One"
    )
    public @Valid UnifiedResponse<CountryPayload> create(
            @RequestBody @Valid
            CountryPayload.Create payload
    ) {
        return UnifiedResponse.of(
                countryService.get(
                        countryService.create(payload).getId()
                )
        );
    }

    @PutMapping("/admin/country")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    public @Valid UnifiedResponse<CountryPayload> update(
            @RequestBody @Valid
            CountryPayload payload
    ) {
        return UnifiedResponse.of(
                countryService.get(
                        countryService.update(payload)
                                .getId()
                )
        );
    }

    @DeleteMapping("/admin/country/{id}")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Delete One"
    )
    public @Valid UnifiedResponse<Void> delete(
            @PathVariable("id") UUID id
    ) {
        countryService.delete(id);
        return UnifiedResponse.of(
                null
        );
    }
}
