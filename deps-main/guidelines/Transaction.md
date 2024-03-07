# Database Transaction

## Custom Hook Action
```java
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionConfig {
    private final DSLContext dslContext;
    @PostConstruct
    void pc() {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void beforeCommit(boolean readOnly) {
                TransactionSynchronization.super.beforeCommit(readOnly);
                if (RequestHolder.get().isAuthed()) {
                    Routines.setCurrentAppUser(
                            dslContext.configuration(),
                            String.format("exampleServerName_%s", RequestHolder.get().getUser().getUserId())
                    );
                }
            }
        });
    }
}
```