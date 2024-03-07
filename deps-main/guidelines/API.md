# API Design & Implementation

## Documentation

### POJO
```java
@Data
@SuperBuilder
@NoArgsConstructor
public class SomeResponse {
    // an annotation for OpenAPI documentation
    @Schema(description = "Error String")
    private String error;
    @Schema(description = "Data Body")
    private Object data;
}
```

### Controller

```java
```

## Implementation

```java
@RestController
@RequestMapping({"/api/user"})
@Tag(name = "User") // OpenAPI Documentation
@Validated // enable validation for this controller
@Slf4j
public class UserController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // OpenAPI Documentation
    @Operation(
            summary = "Create"
    )
    // validate response
    public @Valid UnifiedResponse<CreateUserResponse> createUser(
            @Valid // validate request | @Validated(groups)
            @RequestBody
            CreateUserRequest payload
    ) {
        return UnifiedResponse.of(new CreateUserResponse());
    }
}
```

### ACL

```java
/**
 * Project Independent Unified ACL Validator to be extended by its modules
 * */
public abstract class FieldACLValidate<T> extends FieldACLValidation<T, UserRole> {
    @Override
    public Optional<UserRole> currentUserRole() {
        // get current user logic
        var currentUser = WebContextHolder.getCurrentUser();
        return currentUser;
    }
}
```

### POJO

```java
@Data
@SuperBuilder
@NoArgsConstructor
public class CreateUserRequest {

    // firstly check for field's value is not blank
    @NotBlank
    // then field level of ACL control
    // throw errors on insufficient permission
    @FieldACL(handlers = {
            QuestionACL.class
    })
    private String question;

    public static class QuestionACL extends FieldACLValidate<String> {
        @Override
        public FieldACLValidation<String, UserRole>.ExecutionStep permissions(String input, FieldACLValidation<String, UserRole>.ExecutionStep func) {
            return func
                    /*
                     * conditionally allows non signed-in user to pass this field on specific values
                     */
                    .nonUser(() -> input.contains("disallowedValue"))
                    /*
                     * conditionally allows admin user to pass this field on specific values
                     */
                    .user(UserRole.ADMIN, () -> !input.contains("askingAboutRootUser"))
                    /*
                     * allow root user on all values
                     */
                    .user(UserRole.ROOT, () -> true);
        }
    }
}
```

```java
@Data
@SuperBuilder
@NoArgsConstructor
public class CreateUserResponse {
    // if response field is blank, internal error then
    @NotBlank
    // then field level of ACL control
    // will remove this field to the response on insufficient permission
    @FieldACL(handlers = {
            QuestionACL.class
    })
    private String respondAnswer;
}
```

### Custom Unified Response Body

#### Custom POJO

```java
@Data
@SuperBuilder
@NoArgsConstructor
public class AlternativeResponse<DataBody> {
    @Schema(description = "Error String")
    private String error;
    @Schema(description = "Data Body")
    @Valid
    private DataBody data;

    public static <DataBody> AlternativeResponse<DataBody> of(DataBody data) {
        return AlternativeResponse.<DataBody>builder().data(data).build();
    }
}
```

#### Custom Customizer (override the default)

```java
@Component
@Slf4j
public class AlternativeResponseBodyCustomizer extends ResponseBodyCustomizer<AlternativeResponse> {

    @Override
    public Supplier<@NotNull Class<AlternativeResponse>> responseType() {
        return () -> AlternativeResponse.class;
    }

    @Override
    public Supplier<AlternativeResponse> initialResponseBody() {
        return AlternativeResponse::new;
    }

    @Override
    public BiConsumer<AlternativeResponse, Object> onBodyData() {
        return (b, d) -> {
            log.info("Setting {} to {}", d, b);
            b.setData(d);
        };
    }

    @Override
    public BiConsumer<AlternativeResponse, UnifiedException> onError() {
        return (b, e) -> {
            b.setError(e.getMessage());
        };
    }

    @Override
    public Consumer<AlternativeResponse> atFinally() {
        return (b) -> {
        };
    }
}
```

#### In Action

```java
@RestController
@RequestMapping({"/api/user"})
@Validated
@Slf4j
public class UserController {
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    // this will trigger your customizer for `AlternativeResponse`
    public @ValidResponse AlternativeResponse<CreateUserResponse> createUser(
            @Valid // ensure a validated request
            @RequestBody
            CreateUserRequest payload
    ) {
        return AlternativeResponse.of(new CreateUserResponse());
    }
}
```