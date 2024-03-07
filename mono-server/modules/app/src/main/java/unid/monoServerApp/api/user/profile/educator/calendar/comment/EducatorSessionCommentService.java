package unid.monoServerApp.api.user.profile.educator.calendar.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.educatorCalendar.DbEducatorCalendar;
import unid.monoServerApp.database.table.educatorSessionNote.DbEducatorSessionNoteMap;
import unid.monoServerApp.database.table.studentPaymentTransaction.DbStudentPaymentTransaction;
import unid.monoServerApp.mapper.EducatorCalendarMapper;
import unid.monoServerApp.mapper.EducatorSessionNoteMapper;
import unid.monoServerApp.service.SessionService;
import unid.monoServerMeta.api.EducatorSessionCommentRequest;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EducatorSessionCommentService {
    private final DbEducatorCalendar dbEducatorCalendar;
    private final SessionService sessionService;
    private final EducatorCalendarMapper educatorCalendarMapper;
    private final DbStudentPaymentTransaction dbStudentPaymentTransaction;
    private final DbEducatorSessionNoteMap dbEducatorSessionNoteMap;
    private final EducatorSessionNoteMapper educatorSessionNoteMapper;

    public DbEducatorSessionNoteMap.Result get(UUID calendarId, UUID commentId) {
        var table = dbEducatorSessionNoteMap.getTable();
        return dbEducatorSessionNoteMap.getQuery(table)
                .where(table.EDUCATOR_CALENDAR_ID.eq(calendarId))
                .and(table.ID.eq(commentId))
                .and(dbEducatorSessionNoteMap.validateCondition(table))
                .fetchOptionalInto(DbEducatorSessionNoteMap.Result.class)
                .orElseThrow(() -> Exceptions.notFound("Comment Not Found"));
    }

    List<DbEducatorSessionNoteMap.Map> getComments(UUID calendarId) {
        var table = dbEducatorSessionNoteMap.getTable();
        return dbEducatorSessionNoteMap.getMap(table, sql -> sql.where(table.EDUCATOR_CALENDAR_ID.eq(calendarId)));
    }

//    List<DbEducatorSessionNoteMap.Result> makeComments(UUID calendarId, UUID commentId, EducatorSessionCommentRequest payload) {
//        var comment = get(calendarId, commentId);
//
//    }

}
