package unid.monoServerApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.tables.pojos.SessionOpLogPojo;
import unid.monoServerApp.database.table.eventLog.DbSessionOpLog;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.user.DbUser;
import unid.monoServerApp.mapper.SessionOpLogMapper;
import unid.monoServerApp.model.SessionLogger;

import static unid.jooqMono.model.Tables.USER;
import static unid.monoServerApp.cache.CacheTags.STUDENT_PROFILE;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SessionLoggerListener {

    private final DbSessionOpLog dbSessionOpLog;
    private final SessionOpLogMapper sessionOpLogMapper;

    @EventListener
    public void handleSessionOpEvent(SessionLogger logger) {
        dbSessionOpLog.getDao().insert(
                sessionOpLogMapper.toPojo(logger.getEvent())
        );
    }

}
