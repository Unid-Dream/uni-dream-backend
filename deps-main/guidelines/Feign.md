# Feign (HTTP client)

- Ref
  - https://cloud.spring.io/spring-cloud-openfeign/reference/html/#spring-cloud-feign-overriding-defaults
  - https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html

## Request Client
```java
@Component
@FeignClient(
        name = "TestApi",
        url = "https://jsonplaceholder.typicode.com",
        // allow multiple configs
        configuration = {TestApiConfig.class} // individual config
)
@Validated
public interface TestApi {

    // query as pojo for better code
    // others like request body or path variable could just use ordinary spring annotation
    @GetMapping("posts")
    @FeignAction(actions = Actions.BASIC_AUTH) // custom annotation for additional actions during interceptor
    List<TestApiResponse> getPosts(@Valid @SpringQueryMap(encoded = true) TestApiQuery query);
}
```

## Request Client Config
```java
// create your own "Actions" strings instead of using just plain text
public class Actions {
    public static final String BASIC_AUTH = "BASIC_AUTH";
}

// individual config
// @Configuration // annotate with this will become a global config
public class TestApiConfig {
    
  // normal interceptor
  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
        // do any normal request intercepts
    };
  }
  
  // or use a custom powerful interceptor, works with any custom features
  @Bean
  public UltraRequestInterceptor requestInterceptor() {
    return new UltraRequestInterceptor() {
      @Override
      public void onRequest(RequestTemplate requestTemplate) {
        // do any normal request intercepts
      }

      // to work with @FeignAction
      @Override
      public void feignActions(HashMap<String, Consumer<RequestTemplate>> map) {
        map.put(Actions.BASIC_AUTH, requestTemplate -> {
          new BasicAuthRequestInterceptor(
                  properties.getAppKey(),
                  properties.getAppSecrets().get(0)
          ).apply(requestTemplate);
        });
      }
    };
  }
  
  // this one usually will not work, just to showing there is a such kind option
  @Bean ResponseInterceptor responseInterceptor() {
    return invocationContext -> {
      return invocationContext.proceed();
    };
  }
  
  // any other standard Feign Config here
}
```

## Request Client Response(s)
```java
@Data
@SuperBuilder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestApiResponse {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
```

## Request Client Query (if applicable)
```java
@Data
@SuperBuilder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestApiQuery {
    @NotNull
    @Param("post_id")
    private Integer postId;
}
```