# RSQL (API Query)

## Frontend Usage

### Operators

### Logical Operators (combining conditions)

- and
- or

#### Conditions
- Identifier: `query`
  - EQUALS: `=eq=`
  - NOT EQUAL: `=ne=`
  - LESS THAN: `=lt=`
  - LESS THAN OR EQUALS: `=le=`
  - GREATER THAN: `=gt=`
  - GREATER THAN OR EQUALS: `=ge=`
  - IN: `=in=`
  - NOT IN: `=out=`
  - BETWEEN: `=bt=`
  - LIKE (case sensitivity depends on API design): `=lk=`

#### Sorts

- Identifier: `sort`
  - ASCENDING ORDER: `=asc=`
  - DESCENDING ORDER: `=desc=`

#### Query Size (Page Size)

- Identifier: `limit`

#### Page Cursor

- Identifier: `page`

### Values Input
 - conditions
   - every single **value** must be quoted with single quote (`'`) to avoid errors
     - I.E. `user_name=eq='some_name'`
   - single value input
     - `'some_single_value'`
   - array values input
     - `('some_array_input_0', 'some_array_input_1')`
 - sorting
   - **values** must be provided even its just sorting & quoted with single quote (`'`)
     - normal ordering
       - sample: `some_field=asc=''`
       - meaning: `some_field` will be sorted in ascending order
     - abnormal ordering (sort by values)
        - sample: `some_field=asc=('value1', 'value2')`
        - meaning: `some_field` will be sorted with `value1` & `value2` at the very front

### Samples
 - every single query should be encoded with **`HTML URL Encoding`** to avoid any errors
 - query product with manufactured year between 2014 & 2018
   - `?query=year=bt=('2014','2018')`
     - if wanted to have multiple conditions
       - `?query=year=bt=('2014','2018') or capacity=ge=831`
     - or grouped conditions
         - `?query=(year=bt=('2014','2018') or capacity=ge=831) and (hands=le=8)`
   - & sort by creation date in descending order
     - `?query=year=bt=('2014','2018')&sort=created_on=desc=''`
       - or multiple sorts
         - `?query=year=bt=('2014','2018')&sort=created_on=desc='' and country=asc=`
       - or sort by values
           - `?query=year=bt=('2014','2018')&sort=created_on=desc='' and country=asc='HK,UK'`
             - meaning of country sorting: sort by country in ascending order having 'HK' & 'UK' at the front
   - & limit the output size in 10 records
       - `?query=year=bt=('2014','2018')&sort=created_on=desc=''&limit=10`
   - **then** query the next or previous page
       - `?page=<cursor value>`

## Spring Boot

### Additional Dependencies
```xml
<dependencies>[RSQLjOOQ.md](RSQLjOOQ.md)
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jooq</artifactId>
    </dependency>
    <!-- if you are using PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### Controller
```java
@RestController
@RequestMapping({"/api/user"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Slf4j
public class UserController {
    private final DSLContext dslContext;
    private final ObjectMapper objectMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(
            summary = "Query",
            description = "default limit: 20 | fields: created_on | country | name"
    )
    public UnifiedResponse<PaginationResponse<GetUserResponse>> listUser(
            @Valid
            @ParameterObject // annotate this when POJO is a query for OpenAPI Documentation
            PaginationRequest payload // unified request payload for pagination using jOOQ
    ) {
        /*
         * Query Result
         * payload contains user-defined query & page cursors
         * */
        var table = GetUserPagination.TABLE;
        /*
         * demo custom condition injection on first page query
         * no required, define this when needed
         * */
        var extraConditionOnFirstPage = DSL.noCondition();
        if (StringUtils.isBlank(payload.getPage())) { // 'page' is blank = first page query
            var query = payload.getQuery();
            if (StringUtils.isBlank(query)) {
                extraConditionOnFirstPage = extraConditionOnFirstPage.and(
                        table.PRODUCT_STATUS.eq(VehicleProductStatusEnum.ON_SALE)
                );
            }
        }
        var result = PaginatedQuery.init(
                        dslContext,
                        objectMapper,
                        payload,
                        // optional identifier of current session user
                        // returned result will ownership will be assigned to the specific user if value is specified
                        null,
                        // min query size, applicable only on first page query
                        5,
                        // max query size, applicable only on first page query
                        50
                )
                /*
                 * Defining the select step
                 * */
                .select(dsl -> dslContext.select(table.asterisk()).from(table)) // or from your own query
                /*
                 * Defining the condition visitor (parser)
                 * = API supported user-defined condition query
                 * */
                .conditions(GetUserPagination.conditionVisitor)
                /*
                 * Defining the in-code | injected conditions
                 * = system-defined & mandatory conditions behind the scene
                 * */
                .extraConditions(
                        /*
                         * combining conditional 'first page' condition & mandatory condition
                         * otherwise if no 'first page' condition then just mandatory condition
                         * */
                        extraConditionOnFirstPage.and(
                                table.ENGINE_SIZE.ge(1L)
                                        .and(table.LOT_SIZE.ge((short) 2))
                        )
                )
                .sortBy(
                        GetUserPagination.orderingVisitor,
                        /*
                         * extra ordering applies before user-defined ordering behind the scene
                         * leave 'null' if no extra
                         * */
//                        null,
                        appendBeforeUserDefinition -> appendBeforeUserDefinition.add(
                                new PaginatedQuerySorting.ExtraOrUniqueSort(
                                        table.PRODUCT_STATUS,
                                        SortOrder.ASC,
                                        // 'product_status' is not in user-defined scope, create new one
                                        OrderingVisitor.Seeking.builder()
                                                /*
                                                 * special SQL ordering
                                                 * i.e. order by status = ('on_sale', 'sold')
                                                 * */
                                                .whenSortByValues(input ->
                                                        new ArrayList<>(
                                                                List.of(VehicleProductStatusEnum.ON_SALE, VehicleProductStatusEnum.SOLD)
                                                        )
                                                )
                                                // Java transformation on 'seeking'
                                                .whenSeeking(input -> EnumUtil.fromString(VehicleProductStatusEnum.class, input).get())
                                                .build()
                                )
                        ),
                        /*
                         * default ordering applies when none user-defined ordering
                         * should only apply supported user-defined operation
                         * */
                        defaultWhenUserDefinitionEmpty -> defaultWhenUserDefinitionEmpty.add(
                                new PaginatedQuerySorting.ExtraOrUniqueSort(
                                        table.CREATED_ON,
                                        SortOrder.DESC,
                                        // 'created_on' is in user-defined scope, retrieve one from it
                                        GetUserPagination.orderingVisitor.getOperations().get(table.CREATED_ON)
                                )
                        ),
                        /*
                         * extra ordering applies after user-defined ordering behind the scene
                         * leave 'null' if no extra
                         * */
                        null,
                        /*
                         * unique sort [i.e. primary key(s)]
                         * required
                         * */
                        uniqueSort -> uniqueSort.add(
                                new PaginatedQuerySorting.ExtraOrUniqueSort(
                                        table.ID,
                                        SortOrder.DESC,
                                        OrderingVisitor.Seeking.builder()
                                                .whenSeeking(Long::valueOf)
                                                .build()
                                )
                        )
                ).fetch();
        /*
         * casting result into needed POJO
         * */
        var paginationResponse = PaginationResponse.fromResult(result, GetUserResponse.class);
        return UnifiedResponse.of(paginationResponse);
        // or
//        return UnifiedResponse.of(
//                PaginationResponse.asResult(result, tagMapper.toResponse(result.getResult().into(DbTag.Result.class)))
//        );
    }
}
```

### Pagination Visitors (handlers)
```java
public class GetUserPagination {
    public static VehicleTable TABLE = DefaultCatalog.DEFAULT_CATALOG.PUBLIC.VEHICLE;

    /**
     * Conditions Visitor
     * Defining which condition fields (& operations) that API supports
     * Defining each supported condition & operation Java transformation
     */
    public static ConditionsVisitor conditionVisitor = PaginationParser.conditionVisitor(
            m -> {
                /*
                 * meaning that searching for 'year' in this table only supports 'between' operation
                 */
                m.put(TABLE.YEAR, b ->
                        b.onCondition(x -> {
                            x.put(ConditionOperators.BETWEEN, input -> TABLE.YEAR.between(Short.valueOf(input.get(0)), Short.valueOf(input.get(1))));
                        }));
                /*
                 * meaning that searching for 'price' in this table only supports 'greater than' | 'less than' operation
                 */
                m.put(TABLE.PRICE, b ->
                        b.onCondition(x -> {
                            x.put(ConditionOperators.GREATER_THAN, input -> TABLE.PRICE.gt(new BigDecimal(input.get(0))));
                            x.put(ConditionOperators.LESS_THAN, input -> TABLE.PRICE.lt(new BigDecimal(input.get(0))));
                        }));
            }
    );

    /**
     * Sorting Visitor
     * Defining which sorting fields (& operations) that API supports
     * Defining each supported condition & operation Java transformation
     */
    public static OrderingVisitor orderingVisitor = PaginationParser.orderingVisitor(
            m -> {
                /*
                 * meaning that API supports ordering by 'price'
                 */
                m.put(TABLE.PRICE, b -> b.whenSeeking(BigDecimal::new));
                /*
                 * meaning that API supports ordering by 'created_on' with alias 'list_date'
                 */
                m.put(TABLE.CREATED_ON, b -> b.queryAlias("list_date").whenSeeking(OffsetDateTime::parse));
            }
    );
}
```

### Response POJO
```java
@Data
@SuperBuilder
@NoArgsConstructor
public class GetUserResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Short year;
    private Long engineSize;
    private Long mileage;
    private Short lotSize;
    private OffsetDateTime createdOn;
    private String createdBy;
    private OffsetDateTime updatedOn;
    private String updatedBy;
}
```