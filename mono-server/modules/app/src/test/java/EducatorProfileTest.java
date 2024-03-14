import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import unid.jooqMono.model.enums.TagCategoryEnum;
import unid.monoServerApp.SpringApp;
import unid.monoServerApp.api.tag.TagService;
import unid.monoServerApp.api.user.profile.educator.EducatorProfileService;
import unid.monoServerApp.api.user.profile.educator.calendar.EducatorCalendarService;
import unid.monoServerMeta.api.EducatorProfileSimpleRequest;
import unid.monoServerMeta.api.EducatorProfileSimpleResponse;
import unid.monoServerMeta.api.TagResponse;

import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = SpringApp.class)
@AutoConfigureMockMvc
public class EducatorProfileTest {
    @Autowired
    @InjectMocks
    private EducatorProfileService educatorProfileService;
    @Autowired
    @InjectMocks
    private TagService tagService;


    @BeforeEach
    public void setup() {
        //添加Mock注解初始化
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOne(){
        UUID educatorProfileId = UUID.fromString("b927721e-d2bf-4bf2-ac59-ab1d9aa5cbb2");
        EducatorProfileSimpleResponse response = educatorProfileService.getSimpleCache(educatorProfileId);
        StaticLog.info("{}",response);
    }

    @Test
    public void testUpdateOne(){
        UUID educatorProfileId = UUID.fromString("b927721e-d2bf-4bf2-ac59-ab1d9aa5cbb2");
        EducatorProfileSimpleResponse responseOne = educatorProfileService.getSimpleCache(educatorProfileId);
        EducatorProfileSimpleRequest request = JSONUtil.toBean(JSONUtil.toJsonStr(responseOne),EducatorProfileSimpleRequest.class);
//        educatorProfileService.update();
//        universityId = {UUID@21760} "6d1d3cfb-c4cd-4c7d-ab2f-f6922d46bdcd"
//        degreeId = {UUID@21761} "4b1f6898-cf42-4f71-b2de-6ed3d780d629"
        //查询一个大学
        List<TagResponse> schools = tagService.list(TagCategoryEnum.SCHOOL);
        //查询一个学历
        List<TagResponse> degree = tagService.list(TagCategoryEnum.EDUCATION_LEVEL);
        EducatorProfileSimpleRequest.EducationLevel level = new EducatorProfileSimpleRequest.EducationLevel();
        level.setDegreeId(degree.stream().findAny().get().getId());
        level.setUniversityId(schools.stream().findAny().get().getId());
        StaticLog.info("{}",JSONUtil.toJsonStr(level));
        request.setEducationLevel(CollUtil.newArrayList(level));
        educatorProfileService.update(educatorProfileId,request);
        EducatorProfileSimpleResponse responseTwo = educatorProfileService.getSimpleCache(educatorProfileId);
        StaticLog.info("{}",responseTwo);
    }
}
