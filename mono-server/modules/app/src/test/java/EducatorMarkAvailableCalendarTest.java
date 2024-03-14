import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.monoServerApp.SpringApp;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarController;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarService;
import unid.monoServerMeta.api.EducatorAvailableScheduleResponse;
import unid.monoServerMeta.api.EducatorCalendarRequest;
import unid.monoServerMeta.api.EducatorCalendarTimeSlotPayload;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 测试老师设置 空闲的日历
 */
@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class EducatorMarkAvailableCalendarTest extends BaseTest{

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    @InjectMocks
    private EducatorCalendarService educatorCalendarService;

    @BeforeEach
    public void setup() {
        //添加Mock注解初始化
        MockitoAnnotations.openMocks(this);
    }



    //测试用例：预定可用时间槽
    //1. 教师 设置了可用时间槽
    //2. 学生 可以查看到 对应的时间槽
    //3. 学生 预定这个时间槽
    //4. 教师 接收 这个请求
    @Test
    public void testMarkAvailableTimeslot() {
        UUID educatorProfileId = UUID.fromString("b927721e-d2bf-4bf2-ac59-ab1d9aa5cbb2");

        EducatorCalendarRequest request = new EducatorCalendarRequest();
        EducatorCalendarTimeSlotPayload slot = new EducatorCalendarTimeSlotPayload();

        slot.setStartDateTimeUtc("2023-03-15T10:00:00Z");
        slot.setEndDateTimeUtc("2023-03-15T11:00:00Z");

        List<EducatorCalendarTimeSlotPayload> slots = CollUtil.newArrayList(slot);
        request.setSlots(slots);


        educatorCalendarService.markAvailable(educatorProfileId,slot);
    }

    @Test
    public void testGetEducatorAvailableTimeOne() throws Exception{
        StaticLog.info("老师查看自己的空闲时间槽");
        UUID educatorProfileId = UUID.fromString("b927721e-d2bf-4bf2-ac59-ab1d9aa5cbb2");
        OffsetDateTime startTimeUtc = OffsetDateTime.parse("2023-03-10T10:00:00Z");
        OffsetDateTime endTimeUtc = OffsetDateTime.parse("2023-03-20T10:00:00Z");

        EducatorAvailableScheduleResponse response = educatorCalendarService.getAllAvailableFromNow(
                educatorProfileId,
                startTimeUtc,
                endTimeUtc
        );
        StaticLog.info("{}",JSONUtil.toJsonPrettyStr(response));
    }


    @Test
    public void testMarkUnavailableTimeSlot(){
        StaticLog.info("老师移除空闲时间槽");
        UUID educatorProfileId = UUID.fromString("b927721e-d2bf-4bf2-ac59-ab1d9aa5cbb2");
        UUID educatorCalendarId = UUID.fromString("3e77e502-2841-4cfb-99de-41983a7c037e");
        educatorCalendarService.unmarkAvailable(educatorProfileId,educatorCalendarId);
    }

    public void testQueryEducatorAvailableTimeTwo(){
        StaticLog.info("老师移除空闲时间槽后的时间");
    }


}
