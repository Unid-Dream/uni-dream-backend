package unid.monoServerApp.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import unid.jooqMono.model.Routines;
import unid.monoServerApp.http.RequestHolder;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class Transaction {
    private final DSLContext dslContext;

    @PostConstruct
    void pc() {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void beforeCommit(boolean readOnly) {
                    log.info("Database Appending User Session | ReadOnly: {}", readOnly);
                    TransactionSynchronization.super.beforeCommit(readOnly);
                    if (RequestHolder.get().isAuthed()) {
                        Routines.setCurrentAppUser(
                                dslContext.configuration(),
                                String.format("mono_%s", RequestHolder.get().getUser().getUserId())
                        );
                    }
                }
            });
        }
    }
}
