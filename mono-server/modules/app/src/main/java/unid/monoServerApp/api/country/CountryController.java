package unid.monoServerApp.api.country;//package unid.monoServerApp.api.country;

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
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.skill.DbInterviewTopic;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.CountryMapper;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.I18n;

import javax.validation.Valid;
import java.time.OffsetDateTime;
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


    @GetMapping(value = {"admin/{userId}/country/page"})
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query"
    )
    public @Valid UnifiedResponse<UniPageResponse<CountryPayload>> page(
            @PathVariable("userId") UUID userId,
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

    @PostMapping("admin/{userId}/country")
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
            @PathVariable("userId") UUID userId,
            @RequestBody @Valid
            CountryPayload payload
    ) {
        return UnifiedResponse.of(
                countryService.get(
                        countryService.create(payload).getId()
                )
        );
    }

    @PutMapping("{id}")
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update One"
    )
    public @Valid UnifiedResponse<CountryResponse> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid
            CountryRequest payload
    ) {
        var result = countryService.get(
                countryService.update(id, payload)
                        .getId()
        );
        return UnifiedResponse.of(
                null
        );
    }
}
