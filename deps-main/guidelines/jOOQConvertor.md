# Sample Code

## Using preconfigured converter that requires arguments passed in
```java
@Slf4j
public class UserEncryptionConverter extends EncryptionConverter {
    // extends base converter from parent

    public UserEncryptionConverter() {
        // get your own config from system's ENV
        super(getProp(pwh.springWebStarter.Constant.ENCRYPTION_KEY), getProp(pwh.springWebStarter.Constant.ENCRYPTION_SALT));
    }

    static String getProp(String name) {
        log.info("Sys ENV: {}", System.getenv());
        return StringUtils.defaultIfBlank(System.getenv(name), System.getenv(name));
    }
}
```