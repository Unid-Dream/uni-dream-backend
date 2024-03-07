# API ACL Control

## Global Session
```java
public class ACL {
    // to globally get current session user
    // hence no need to repeat provide method to get current session user
    public static <Value>pwh.coreStarter.acl.ACL<Value> of(Value value) {
        return pwh.coreStarter.acl.ACL.of(value, () -> SomeMethodToGetCurrentUser());
    }
}
```

## Request
```java
@Data
@SuperBuilder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserRequest {
    @NotNull
    @Valid
    private List<Question> questions;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Question {
        private String question;

        @JsonSetter(Fields.question)
        @Schema(
                name = Fields.question,
                description = "ACL: admin"
        )
        public void setQ(String value) {
            // remove field(s) base on user role when receiving request
            // or throw exceptions
            this.question = ACL.of(value)
                    .onDefault(i -> {
                        // should be a global-referenced exception instead of inline
                        throw Exceptions.InsufficientInput(null, Fields.question);
                    })
                    // remove field
                    .onNotUser(i -> null)
                    .on(UserRole.ADMIN.toString(), i -> i)
                    .get();
        }
    }
}
```

## Response

```java
@Data
@SuperBuilder
@NoArgsConstructor
@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserResponse {
    @NotBlank
    private String respondAnswer;

    @NotNull
    @Valid
    private List<@NotNull UserRoleDetail> userRoleDetail;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @FieldNameConstants
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UserRoleDetail {
        @NotBlank
        private String shortDescription;

        @NotNull
        private List<@NotBlank String> description;

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
        private List<LocalDateTime> another;

        @JsonGetter(Fields.description)
        @Schema(
                name = Fields.description,
                description = "ACL: admin"
        )
        public List<String> acl() {
            // remove field(s) base on user role when output response
            // or throw exceptions
            return ACL.of(description)
                    .onDefault(i -> List.of())
                    .onNotUser(i -> null)
                    .on(UserRole.ADMIN.toString(), i -> i)
                    .get();
        }
    }
}
```