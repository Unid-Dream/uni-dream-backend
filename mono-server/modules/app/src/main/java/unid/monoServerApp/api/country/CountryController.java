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
import unid.monoServerMeta.api.CountryRequest;
import unid.monoServerMeta.api.CountryResponse;
import unid.monoServerMeta.api.TagResponse;
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

//    @GetMapping
//    @ACL(
//            authed = true
//    )
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(
//            summary = "Query"
//    )
//    public @Valid UnifiedResponse<PaginationResponse<CountryResponse>> list(
//            @Valid
//            @ParameterObject
//            PaginationRequest payload
//    ) {
//        var table = CountryPagination.TABLE;
//        var dslContext = dbCountry.getDsl();
//        var extraConditionOnFirstPage = DSL.noCondition();
//        if (StringUtils.isBlank(payload.getPage())) {
//            extraConditionOnFirstPage = dbCountry.validateCondition(table);
//        }
//        var result = PaginatedQuery.init(
//                        dslContext,
//                        objectMapper,
//                        payload,
//                        RequestHolder.get().getAuthToken(),
//                        Constant.PAGINATION_MIN_SIZE,
//                        Constant.PAGINATION_MAX_SIZE
//                )
//                .select(dsl -> dbCountry.getQuery(table))
//                .conditions(CountryPagination.conditionVisitor)
//                .extraConditions(extraConditionOnFirstPage)
//                .sortBy(
//                        CountryPagination.orderingVisitor,
//                        null,
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
//                PaginationResponse.asResult(result, countryMapper.toResponse(result.getResult().into(DbCountry.Result.class)))
//        );
//    }
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


    @GetMapping(value = {"educator/country/tags"})
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

    @GetMapping("{id}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    @Hidden
    public @Valid UnifiedResponse<CountryResponse> get(
            @PathVariable("id") UUID id
    ) {
        var result = countryService.get(id);
        return UnifiedResponse.of(
                countryMapper.toResponse(result)
        );
    }

    @PostMapping
    @Transactional
    @ACL(
            authed = true,
            allowedRoles = {UserRoleEnum.ADMIN, UserRoleEnum.ROOT}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create One"
    )
    @Hidden
    public @Valid UnifiedResponse<CountryResponse> create(
            @RequestBody @Valid
            CountryRequest payload
    ) {
        var result = countryService.get(
                countryService.create(payload)
                        .getId()
        );
        return UnifiedResponse.of(
                countryMapper.toResponse(result)
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
    @Hidden
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
                countryMapper.toResponse(result)
        );
    }
}
