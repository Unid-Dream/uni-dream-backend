package unid.monoServerApp.database.table.eventLog;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unid.jooqMono.model.Public;
import unid.jooqMono.model.enums.BookingStatusEnum;
import unid.jooqMono.model.enums.SessionOpTypeEnum;
import unid.jooqMono.model.tables.SessionOpLogTable;
import unid.jooqMono.model.tables.daos.SessionOpLogDao;
import unid.jooqMono.model.tables.pojos.EducatorCalendarPojo;
import unid.jooqMono.model.tables.pojos.SessionOpLogPojo;
import unid.monoServerApp.database.Db;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.user.DbUser;

import java.time.OffsetDateTime;
import java.util.UUID;

import static unid.jooqMono.model.Tables.EDUCATOR_PROFILE;
import static unid.jooqMono.model.Tables.STUDENT_PROFILE;

@Component
public class DbSessionOpLog extends Db<SessionOpLogTable, SessionOpLogDao> {
    private final DbStudentProfile dbStudentProfile;
    private final DbEducatorProfile dbEducatorProfile;

    @Autowired
    public DbSessionOpLog(DSLContext dslContext, DbStudentProfile dbStudentProfile,DbEducatorProfile dbEducatorProfile) {
        super(dslContext, Public.PUBLIC.SESSION_OP_LOG, new SessionOpLogDao(dslContext.configuration()));
        this.dbStudentProfile = dbStudentProfile;
        this.dbEducatorProfile = dbEducatorProfile;
    }

    @Override
    public SelectJoinStep<Record> getQuery(SessionOpLogTable alias) {
        return null;
    }

    @Override
    public Condition validateCondition(SessionOpLogTable table) {
        return null;
    }


//    public void saveSessionOpLogFromStudent(UUID studentProfileId,
//                                            UUID transactionId,
//                                            BookingStatusEnum fromStatus,
//                                            SessionOpTypeEnum opType,
//                                            BookingStatusEnum toStatus
//    ) {
//        dbStudentProfile.getQuery()
//                .where(STUDENT_PROFILE.ID.eq(studentProfileId))
//                .fetchOptionalInto(DbStudentProfile.Result.class)
//                .ifPresentOrElse(
//                        profile -> saveSessionLog(profile.getUser(),opType,transactionId,fromStatus,toStatus),
//                        () -> {}
//                );
//    }
//
//    public void saveSessionOpLogFromEducator(UUID educatorProfileId,
//                                             UUID transactionId,
//                                             BookingStatusEnum fromStatus,
//                                             SessionOpTypeEnum opType,
//                                             BookingStatusEnum toStatus
//    ) {
//        dbEducatorProfile.getQuery()
//                .where(EDUCATOR_PROFILE.ID.eq(educatorProfileId))
//                .fetchOptionalInto(DbEducatorProfile.Result.class)
//                .ifPresentOrElse(
//                        profile -> saveSessionLog(profile.getUser(),opType,transactionId,fromStatus,toStatus),
//                        () -> {}
//                );
//    }
//
//    public void saveSessionLog(DbUser.Result user,
//                                SessionOpTypeEnum opType,
//                                UUID transactionId,
//                                BookingStatusEnum fromStatus,
//                                BookingStatusEnum toStatus){
//        getDao().insert(new SessionOpLogPojo()
//                .setOpTimeUtc(OffsetDateTime.now())
//                .setOpUserId(user.getId())
//                .setOpType(opType)
//                .setFromStatus(fromStatus)
//                .setToStatus(toStatus)
//                .setOpUserRole(user.getUserRole())
//                .setTransactionId(transactionId));
//    }


}
