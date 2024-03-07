package unid.monoServerApp.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.Lists;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.*;
import unid.jooqMono.model.tables.daos.PassionSubjectVideoDao;
import unid.jooqMono.model.tables.pojos.*;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.api.tag.TagService;
import unid.monoServerApp.api.user.profile.educator.EducatorProfileService;
import unid.monoServerApp.database.table.country.DbCountry;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.database.table.school.DbSchool;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerMeta.api.EducatorProfileResponse;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.PassionSubject;
import unid.monoServerMeta.model.PassionSubjectVideo;

import javax.annotation.PostConstruct;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.insertInto;
import static org.jooq.util.postgres.PostgresDSL.arrayAppend;
import static unid.jooqMono.model.Tables.*;

/**
 * 基础数据
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class BasicInfraService {

    private final DSLContext dslContext;
    private final TagService tagService;
    private final I18nMapper i18nMapper;
    private final DbI18N dbI18N;
    private final DbSchool dbSchool;
    private final DbCountry dbCountry;

//    @PostConstruct
    public void loadMilestone(){
        String jsonStr = "[{\n" +
                "    \"id\": \"619991eb-8970-41db-af1d-ac08f083a478\",\n" +
                "    \"answerItems\": [{\n" +
                "        \"id\": \"ff072a97-0b66-4806-825d-905fe1f15408\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"US\",\n" +
                "            \"chineseSimplified\": \"美国\",\n" +
                "            \"chineseTraditional\": \"美國\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"6c2c6c66-6b72-4ae2-9ab4-408b034c8aca\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"UK\",\n" +
                "            \"chineseSimplified\": \"英国\",\n" +
                "            \"chineseTraditional\": \"英國\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"20dfe181-f991-4534-8fb0-b7e7905ae5f4\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Canada\",\n" +
                "            \"chineseSimplified\": \"加拿大\",\n" +
                "            \"chineseTraditional\": \"加拿大\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"94f38cf6-8062-4815-a502-2ad230f1afae\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Australia\",\n" +
                "            \"chineseSimplified\": \"澳大利亚\",\n" +
                "            \"chineseTraditional\": \"澳大利亞\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"7f8d2021-f0ab-4077-965f-4fd5a8a1af71\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Hong Kong, China\",\n" +
                "            \"chineseSimplified\": \"中国香港\",\n" +
                "            \"chineseTraditional\": \"中國香港\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"0542b021-0b9b-4a70-a4f4-11c9956bbe5d\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Mainland, China\",\n" +
                "            \"chineseSimplified\": \"中国大陆\",\n" +
                "            \"chineseTraditional\": \"中國大陸\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"d2fe34ed-5892-4d80-a44d-7fda290aaf34\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Singapore\",\n" +
                "            \"chineseSimplified\": \"新加坡\",\n" +
                "            \"chineseTraditional\": \"新加坡\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"cd2bcdeb-1c47-4cfb-93b4-6e79866ac386\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Japan\",\n" +
                "            \"chineseSimplified\": \"日本\",\n" +
                "            \"chineseTraditional\": \"日本\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"d617ca49-0ac2-4b60-b35b-e0d8c79300c6\",\n" +
                "        \"type\": \"USER_INPUT\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Others\",\n" +
                "            \"chineseSimplified\": \"其他(请说明)\",\n" +
                "            \"chineseTraditional\": \"其他(請說明)\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }],\n" +
                "    \"questionI18n\": {\n" +
                "        \"english\": \"Which of these regions would you consider pursuing your university education in\",\n" +
                "        \"chineseSimplified\": \"你会考虑在以下哪个国家攻读大学学位？\",\n" +
                "        \"chineseTraditional\": \"你會考慮在以下哪個國家攻讀大學學位？\"\n" +
                "    },\n" +
                "    \"singleChoice\": false\n" +
                "}, {\n" +
                "    \"id\": \"99b75b2f-113e-40e3-a616-82e86ab78ca2\",\n" +
                "    \"answerItems\": [{\n" +
                "        \"id\": \"23753217-0258-42d3-bb47-b950997c388c\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Business\",\n" +
                "            \"chineseSimplified\": \"商业管理\",\n" +
                "            \"chineseTraditional\": \"商業管理\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"3618dbe3-d158-46aa-ad59-e430f10a982c\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Engineering\",\n" +
                "            \"chineseSimplified\": \"工程学\",\n" +
                "            \"chineseTraditional\": \"工程學\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"de26d6f0-a544-449f-934a-d67919727268\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Natural Sciences\",\n" +
                "            \"chineseSimplified\": \"自然科学\",\n" +
                "            \"chineseTraditional\": \"自然科學\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"9c403dd0-21e6-4fd4-8c48-b3a323aeae73\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Humanities/ Social Sciences\",\n" +
                "            \"chineseSimplified\": \"人文社会科学\",\n" +
                "            \"chineseTraditional\": \"人文社會科學\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"0dfb5ad3-143c-4d53-8d78-eea2e87d32c4\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Media and Communication\",\n" +
                "            \"chineseSimplified\": \"媒体与传播\",\n" +
                "            \"chineseTraditional\": \"媒體與傳播\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"5c1133b6-ab04-4727-b190-b490199a38bc\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Arts, Performing Arts & Fine Arts\",\n" +
                "            \"chineseSimplified\": \"艺术、表演艺术与美术\",\n" +
                "            \"chineseTraditional\": \"藝術、表演藝術與美術\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"d458f202-9972-40bc-b5a8-b4d9010dd3e4\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Medicine and Healthcare\",\n" +
                "            \"chineseSimplified\": \"医学与医疗保健\",\n" +
                "            \"chineseTraditional\": \"醫學與醫療保健\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"a2e69f1a-8322-47de-8a65-5ba51f751b85\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Computer Science\",\n" +
                "            \"chineseSimplified\": \"计算机科学\",\n" +
                "            \"chineseTraditional\": \"計算機科學\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"6330b2c3-21bd-46a0-8324-5519be027fc7\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Law\",\n" +
                "            \"chineseSimplified\": \"法律\",\n" +
                "            \"chineseTraditional\": \"法律\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"86c0dea5-abb2-4b58-ba7c-abea42385f91\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Architecture\",\n" +
                "            \"chineseSimplified\": \"建筑学\",\n" +
                "            \"chineseTraditional\": \"建築學\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"102f5464-1389-4723-babc-a7a3ab61f513\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Education\",\n" +
                "            \"chineseSimplified\": \"教育学\",\n" +
                "            \"chineseTraditional\": \"教育學\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"80f76553-0dfc-4bd0-8c48-15746ddd3858\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Aviation\",\n" +
                "            \"chineseSimplified\": \"航空航天学\",\n" +
                "            \"chineseTraditional\": \"航空航天學\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }],\n" +
                "    \"questionI18n\": {\n" +
                "        \"english\": \"What are some university major(s) you would like to explore? \",\n" +
                "        \"chineseSimplified\": \"你未来意向修读哪个大学专业？\",\n" +
                "        \"chineseTraditional\": \"你未來意向修讀哪個大學專業？\"\n" +
                "    },\n" +
                "    \"singleChoice\": false\n" +
                "}, {\n" +
                "    \"id\": \"2e95c9ac-4730-4047-be34-7a6507586c2a\",\n" +
                "    \"answerItems\": [{\n" +
                "        \"id\": \"eac07018-da9e-4795-9b6a-99fc1650612f\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"My writing skills\",\n" +
                "            \"chineseSimplified\": \"提升我的写作技能\",\n" +
                "            \"chineseTraditional\": \"提升我的寫作技能\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"a66e3112-8474-49cb-818c-ad425b325314\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"My interview skills\",\n" +
                "            \"chineseSimplified\": \"提高我的面试技巧\",\n" +
                "            \"chineseTraditional\": \"提高我的面試技巧\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"5897cce7-1dd4-4299-b78f-75c0f02cb962\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Natural Sciences\",\n" +
                "            \"chineseSimplified\": \"深入学习自然科学\",\n" +
                "            \"chineseTraditional\": \"深入學習自然科學\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"539b2f7c-5667-4a4a-a52b-d2cc7c542054\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Improving my academics\",\n" +
                "            \"chineseSimplified\": \"提高我的学术成绩\",\n" +
                "            \"chineseTraditional\": \"提高我的學術成績\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"f430310c-2387-49d4-ac45-71805929cce7\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Developing my extracurricular activities\",\n" +
                "            \"chineseSimplified\": \"开展更多课外活动\",\n" +
                "            \"chineseTraditional\": \"開展更多課外活動\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"78ebbae0-48fb-49db-a6dc-641ead08671d\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Planning out my possible career paths\",\n" +
                "            \"chineseSimplified\": \"规划我的职业发展路径\",\n" +
                "            \"chineseTraditional\": \"規劃我的職業發展路徑\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"4e7e1ced-85af-4c25-9f2d-76015e7e3ab7\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Exploring potential  undergraduate courses \",\n" +
                "            \"chineseSimplified\": \"探索潜在的本科课程\",\n" +
                "            \"chineseTraditional\": \"探索潛在的本科課程\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"bbc31b77-af2d-4ee5-8eba-23c9ec96ef3b\",\n" +
                "        \"type\": \"PROVIDED_CHOICE\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"Finding my right fit universities\",\n" +
                "            \"chineseSimplified\": \"找到适合我的大学\",\n" +
                "            \"chineseTraditional\": \"找到適合我的大學\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }, {\n" +
                "        \"id\": \"37e48abb-d3fd-4594-ada2-a68a658afe46\",\n" +
                "        \"type\": \"USER_INPUT\",\n" +
                "        \"answerI18n\": {\n" +
                "            \"english\": \"others\",\n" +
                "            \"chineseSimplified\": \"其他（请说明）\",\n" +
                "            \"chineseTraditional\": \"其他（請說明）\"\n" +
                "        },\n" +
                "        \"optionValue\": null\n" +
                "    }],\n" +
                "    \"questionI18n\": {\n" +
                "        \"english\": \"Which areas would you like to work on over the next 6 months? \",\n" +
                "        \"chineseSimplified\": \"在接下来的6个月内，你希望在哪些方面有所提升呢？\",\n" +
                "        \"chineseTraditional\": \"在接下來的六個月裡，你希望在哪些方面有所提升呢？\"\n" +
                "    },\n" +
                "    \"singleChoice\": false\n" +
                "}]";
        List<MilestoneItem> items = JSONUtil.toList(jsonStr,MilestoneItem.class);
        System.out.println(items);
        int questionSortId = 1;
        for(MilestoneItem item : items){
            I18nPojo i18nPojo = i18nMapper.toPojo(item.questionI18n);
            dbI18N.getDao().insert(i18nPojo);
            StudentMilestoneQuestionnairePojo questionnairePojo = new StudentMilestoneQuestionnairePojo();
            questionnairePojo.setQuestionI18nId(i18nPojo.getId());
            questionnairePojo.setSingleChoice(item.getSingleChoice());
            questionnairePojo.setSortId(questionSortId);

            questionSortId ++;

            StudentMilestoneQuestionnairePojo question = dslContext
                    .insertInto(STUDENT_MILESTONE_QUESTIONNAIRE)
                    .set(dslContext.newRecord(STUDENT_MILESTONE_QUESTIONNAIRE, questionnairePojo))
                    .returning(field(DSL.name(StudentMilestoneQuestionnairePojo.Columns.id))) // 假设'id'是自动生成的UUID
                    .fetchOneInto(StudentMilestoneQuestionnairePojo.class);
            int answerSortId = 1;
            for(AnswerItem answerItem: item.getAnswerItems()){
                StudentMilestoneOptionsPojo optionsPojo = new StudentMilestoneOptionsPojo();
                optionsPojo.setQuestionnaireId(question.getId());
                optionsPojo.setOptionType(answerItem.getType());
                optionsPojo.setSortId(answerSortId++);
                I18nPojo answerI18nPojo = i18nMapper.toPojo(answerItem.answerI18n);
                dbI18N.getDao().insert(answerI18nPojo);
                optionsPojo.setAnswerI18nId(answerI18nPojo.getId());
                dslContext
                        .insertInto(STUDENT_MILESTONE_OPTIONS)
                        .set(dslContext.newRecord(STUDENT_MILESTONE_OPTIONS, optionsPojo))
                        .execute();
                answerSortId++;

            }

         }
    }

    @Data
    public static class MilestoneItem{
        private I18n questionI18n;
        private List<AnswerItem> answerItems;
        private Boolean singleChoice;
    }

    @Data
    public static class AnswerItem{
        private MilestoneOptionTypeEnum type;
        private I18n answerI18n;
    }




//    @PostConstruct
    public void loadAcademic(){
        List<String> academicStrs = FileUtil.readUtf8Lines("/Users/apple/Documents/importdata.json");
        AtomicInteger videoCnt = new AtomicInteger();
        for(Object academicObj : JSONUtil.parseArray(academicStrs.get(0))){
            JSONObject academic = (JSONObject) academicObj;
             for(Object subjectObj : academic.getJSONArray("subjects")){
                 JSONObject subject = (JSONObject) subjectObj;
                 List<PassionSubjectPojo> subjectPojos = dslContext.select(PASSION_SUBJECT.fields()).from(PASSION_SUBJECT,I18N).where(PASSION_SUBJECT.NAME_I18N_ID.eq(I18N.ID).and(I18N.ENGLISH.eq(subject.getJSONObject("nameI18n").getStr("english")))).fetchInto(PassionSubjectPojo.class);
                 for(Object videoObj : subject.getJSONArray("videos")){
                     JSONObject video = (JSONObject) videoObj;
                     if(video.getStr("type").equals("youtube")&&!subjectPojos.isEmpty()){
                         for(PassionSubjectPojo subjectPojo : subjectPojos){
                             I18nPojo name = dslContext
                                     .insertInto(I18N)
                                     .set(dslContext.newRecord(I18N, new I18nPojo()
                                             .setEnglish(video.getJSONObject("nameI18n").getStr("english"))
                                     ))
                                     .returning(field(DSL.name("id"))) // 假设'id'是自动生成的UUID
                                     .fetchOneInto(I18nPojo.class);

                             I18nPojo author = dslContext
                                     .insertInto(I18N)
                                     .set(dslContext.newRecord(I18N, new I18nPojo()
                                             .setEnglish(video.getJSONObject("authorI18n").getStr("english"))
                                     ))
                                     .returning(field(DSL.name("id"))) // 假设'id'是自动生成的UUID
                                     .fetchOneInto(I18nPojo.class);

                             PassionSubjectVideoPojo videoPojo = new PassionSubjectVideoPojo();
                             videoPojo.setAuthorI18nId(author.getId());
                             videoPojo.setNameI18nId(name.getId());
                             videoPojo.setSubjectId(subjectPojo.getId());
                             videoPojo.setType(video.getStr("type"));
                             videoPojo.setUrl(video.getStr("url"));
                             dslContext.insertInto(PASSION_SUBJECT_VIDEO)
                                     .set(PASSION_SUBJECT_VIDEO.AUTHOR_I18N_ID, videoPojo.getAuthorI18nId())
                                     .set(PASSION_SUBJECT_VIDEO.NAME_I18N_ID, videoPojo.getNameI18nId())
                                     .set(PASSION_SUBJECT_VIDEO.SUBJECT_ID, videoPojo.getSubjectId())
                                     .set(PASSION_SUBJECT_VIDEO.TYPE, videoPojo.getType())
                                     .set(PASSION_SUBJECT_VIDEO.URL, videoPojo.getUrl())
                                     .execute();
                         }
                     }
                 }
             }
        }
    }


//    @PostConstruct
    public void loadStudentQuestionnaire(){
        StudentQuestionnaireNew studentQuestionnaireNew = new StudentQuestionnaireNew();
        I18nPojo titleI18n = insertI18nAndGetUUID(studentQuestionnaireNew.titleI18n);
        I18nPojo subTitleI18n = insertI18nAndGetUUID(studentQuestionnaireNew.subTitleI18n);
        I18nPojo descriptionI18n = insertI18nAndGetUUID(studentQuestionnaireNew.descriptionI18n);


        studentQuestionnaireNew.setTitleI18nId(titleI18n.getId());
        studentQuestionnaireNew.setSubTitleI18nId(subTitleI18n.getId());
        studentQuestionnaireNew.setDescriptionI18nId(descriptionI18n.getId());
        studentQuestionnaireNew.setVersion(OffsetDateTime.now());
        studentQuestionnaireNew.setMandatoryOnNewJoinStudent(true);

        StudentQuestionnairePojo createdStudentQuestionnaire = dslContext.select(STUDENT_QUESTIONNAIRE.ID,STUDENT_QUESTIONNAIRE.VERSION)
                .from(STUDENT_QUESTIONNAIRE)
                .where(STUDENT_QUESTIONNAIRE.TITLE_I18N_ID.eq(studentQuestionnaireNew.getTitleI18nId()))
                .fetchOptionalInto(StudentQuestionnairePojo.class)
                .orElseGet(()-> dslContext
                        .insertInto(STUDENT_QUESTIONNAIRE)
                        .set(dslContext.newRecord(STUDENT_QUESTIONNAIRE, studentQuestionnaireNew))
                        .returning(STUDENT_QUESTIONNAIRE.ID,STUDENT_QUESTIONNAIRE.VERSION) // 指定要返回的字段，这里是ID字段
                        .fetchOptionalInto(StudentQuestionnairePojo.class).orElseThrow(Exceptions::unknownError));

        StudentQuestionnaireSectionNew studentQuestionnaireSectionNew = new StudentQuestionnaireSectionNew();

        I18nPojo sectionTitleI18n = insertI18nAndGetUUID(studentQuestionnaireSectionNew.titleI18n);
        studentQuestionnaireSectionNew.setStudentQuestionnaireId(createdStudentQuestionnaire.getId());
        studentQuestionnaireSectionNew.setTitleI18nId(sectionTitleI18n.getId());
        studentQuestionnaireSectionNew.setOrder(1);
        studentQuestionnaireSectionNew.setStudentQuestionnaireVersion(createdStudentQuestionnaire.getVersion());

        StudentQuestionnaireSectionPojo createdStudentQuestionnaireSection = dslContext.select(STUDENT_QUESTIONNAIRE_SECTION.ID)
                .from(STUDENT_QUESTIONNAIRE_SECTION)
                .where(STUDENT_QUESTIONNAIRE_SECTION.TITLE_I18N_ID.eq(studentQuestionnaireSectionNew.getTitleI18nId()))
                .fetchOptionalInto(StudentQuestionnaireSectionPojo.class)
                .orElseGet(()-> dslContext
                        .insertInto(STUDENT_QUESTIONNAIRE_SECTION)
                        .set(dslContext.newRecord(STUDENT_QUESTIONNAIRE_SECTION, studentQuestionnaireSectionNew))
                        .returning(STUDENT_QUESTIONNAIRE_SECTION.ID) // 指定要返回的字段，这里是ID字段
                        .fetchOptionalInto(StudentQuestionnaireSectionPojo.class).orElseThrow(Exceptions::unknownError));

        StudentQuestionnaireQuestionNew studentQuestionnaireQuestionNew = new StudentQuestionnaireQuestionNew();
        I18nPojo questionDescriptionI18n = insertI18nAndGetUUID(studentQuestionnaireQuestionNew.descriptionI18n);
        studentQuestionnaireQuestionNew.setStudentQuestionnaireSectionId(createdStudentQuestionnaireSection.getId());
        studentQuestionnaireQuestionNew.setDescriptionI18nId(questionDescriptionI18n.getId());
        studentQuestionnaireQuestionNew.setMandatory(true);
        studentQuestionnaireQuestionNew.setType(QuestionnaireQuestionTypeEnum.MULTIPLE_CHOICE);
        studentQuestionnaireQuestionNew.setOrder(1);


        StudentQuestionnaireQuestionPojo createdStudentQuestionnaireQuestionNew = dslContext.select(STUDENT_QUESTIONNAIRE_QUESTION.ID)
                .from(STUDENT_QUESTIONNAIRE_QUESTION)
                .where(STUDENT_QUESTIONNAIRE_QUESTION.DESCRIPTION_I18N_ID.eq(studentQuestionnaireQuestionNew.getDescriptionI18nId()))
                .fetchOptionalInto(StudentQuestionnaireQuestionPojo.class)
                .orElseGet(()-> dslContext
                        .insertInto(STUDENT_QUESTIONNAIRE_QUESTION)
                        .set(dslContext.newRecord(STUDENT_QUESTIONNAIRE_QUESTION, studentQuestionnaireQuestionNew))
                        .returning(STUDENT_QUESTIONNAIRE_QUESTION.ID) // 指定要返回的字段，这里是ID字段
                        .fetchOptionalInto(StudentQuestionnaireQuestionPojo.class).orElseThrow(Exceptions::unknownError));


        StudentQuestionnaireAnswerNew studentQuestionnaireAnswerNew = new StudentQuestionnaireAnswerNew();
        I18nPojo answerDescriptionI18n = insertI18nAndGetUUID(studentQuestionnaireAnswerNew.descriptionI18n);
        studentQuestionnaireAnswerNew.setStudentQuestionnaireQuestionId(createdStudentQuestionnaireQuestionNew.getId());
        studentQuestionnaireAnswerNew.setDescriptionI18nId(answerDescriptionI18n.getId());
        studentQuestionnaireAnswerNew.setScore(new BigDecimal(9));
        studentQuestionnaireAnswerNew.setType(QuestionnaireAnswerTypeEnum.PROVIDED_CHOICE);
        studentQuestionnaireAnswerNew.setOrder(1);

        dslContext
                .insertInto(STUDENT_QUESTIONNAIRE_ANSWER)
                .set(dslContext.newRecord(STUDENT_QUESTIONNAIRE_ANSWER, studentQuestionnaireAnswerNew))
                .execute();



    }


//    @PostConstruct
    public void loadEcaCourse(){
        List<String> lines = FileUtil.readUtf8Lines("/Users/apple/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/4b6e2766a8b83b0b3b846c9837cd745f/Message/MessageTemp/5b65276218bc03384bd5844b0ae813b3/File/eca.json");
        List<EcaProfileNew> ecaProfileNews = JSONUtil.toList(lines.get(0),EcaProfileNew.class);
        for(EcaProfileNew ecaProfileNew: ecaProfileNews){
            I18nPojo titleI18n = insertI18nAndGetUUID(ecaProfileNew.getTitleI18n());
            I18nPojo descriptionI18n = insertI18nAndGetUUID(ecaProfileNew.getDescription());
            I18nPojo eligibilityI18n = insertI18nAndGetUUID(ecaProfileNew.getEligibility());

            List<UUID> opportunityIds = Lists.newArrayList();
            for(I18nPojo opportunity : ecaProfileNew.getOpportunities()){
                I18nPojo opportunityI18n = insertI18nAndGetUUID(opportunity);
                OpportunityPojo opportunityPojo = dslContext.select(OPPORTUNITY.ID)
                        .from(OPPORTUNITY)
                        .where(OPPORTUNITY.TITLE_I18N_ID.eq(opportunityI18n.getId()))
                        .fetchOptionalInto(OpportunityPojo.class)
                        .orElseGet(()-> dslContext
                                .insertInto(OPPORTUNITY)
                                .set(dslContext.newRecord(OPPORTUNITY, new OpportunityPojo().setTitleI18nId(opportunityI18n.getId())))
                                .returning(OPPORTUNITY.ID) // 指定要返回的字段，这里是ID字段
                                .fetchOptionalInto(OpportunityPojo.class).orElseThrow(Exceptions::unknownError));
                opportunityIds.add(opportunityPojo.getId());
            }
            //查询academic
            List<UUID> academicId = dslContext.select(PASSION_MAJOR.fields())
                    .from(I18N,PASSION_MAJOR)
                    .where(I18N.ID.eq(PASSION_MAJOR.NAME_I18N_ID))
                    .and(I18N.ENGLISH.in(ecaProfileNew.getMajors()))
                    .fetchInto(PassionMajorPojo.class)
                    .stream().map(PassionMajorPojo::getId).collect(Collectors.toList());

            EcaCoursePojo ecaCoursePojoNew = new EcaCoursePojo()
                    .setCoverImage(ecaProfileNew.coverImgage)
                            .setDescriptionI18nId(descriptionI18n.getId())
                                    .setTitleI18nId(titleI18n.getId())
                                            .setEligibilityI18nId(eligibilityI18n.getId())
                                                    .setOpportunityId(opportunityIds.toArray(new UUID[0]))
                                                            .setGrade(ecaProfileNew.getGrade())
                                                                    .setRefUrl(ecaProfileNew.getRefUrl())
                                                                            .setCoverImage(ecaProfileNew.getS3CoverImagePath())
                                                                                    .setAcademicId(academicId.toArray(new UUID[0]));

            dslContext
                    .insertInto(ECA_COURSE)
                    .set(dslContext.newRecord(ECA_COURSE, ecaCoursePojoNew))
                            .execute();
            System.out.println(ecaCoursePojoNew);
        }
    }

    private I18nPojo insertI18nAndGetUUID(I18nPojo pojo){
        return dslContext.select(I18N.ID)
                .from(I18N)
                .where(I18N.ENGLISH.eq(pojo.getEnglish()))
                .fetchOptionalInto(I18nPojo.class)
                .orElseGet(()-> dslContext
                        .insertInto(I18N)
                        .set(dslContext.newRecord(I18N, pojo))
                        .returning(I18N.ID) // 指定要返回的字段，这里是ID字段
                        .fetchOptionalInto(I18nPojo.class).orElseThrow(Exceptions::unknownError));
    }



//    @PostConstruct
    public void loadEducatorProfile(){
        List<String> lines = FileUtil.readUtf8Lines("/Users/apple/Documents/educators.json");
        for(Object profileObj : JSONUtil.parseArray(lines.get(0))){
            BasicInfraService.EducatorProfile profile = JSONUtil.toBean(JSONUtil.toJsonStr(profileObj),BasicInfraService.EducatorProfile.class);
            System.out.println(profile);

            EducatorProfilePojo pojo = dslContext.select(EDUCATOR_PROFILE.fields()).from(EDUCATOR_PROFILE,I18N,USER)
                    .where(EDUCATOR_PROFILE.USER_ID.eq(USER.ID).and(USER.FIST_NAME_I18N_ID.eq(I18N.ID))).and(I18N.ENGLISH.eq(profile.getFirstNameI18n().getEnglish()))
                    .fetchOptionalInto(EducatorProfilePojo.class)
                    .orElseThrow(Exceptions::unknownError);

            for(I18n i18n : profile.getExpertise_in()){
                I18nPojo i18nPojo = dslContext.select(I18N.ID)
                        .from(I18N)
                        .where(I18N.ENGLISH.eq(i18n.getEnglish()))
                        .fetchOptionalInto(I18nPojo.class)
                        .orElseGet(()-> dslContext
                                .insertInto(I18N)
                                .set(dslContext.newRecord(I18N, i18nMapper.toPojo(i18n)))
                                .returning(I18N.ID) // 指定要返回的字段，这里是ID字段
                                .fetchOptionalInto(I18nPojo.class).orElseThrow(Exceptions::unknownError));
                ExpertisePojo expertisePojo = dslContext.select(EXPERTISE.ID)
                        .from(EXPERTISE)
                        .where(EXPERTISE.DESCRIPTION_I18N_ID.eq(i18nPojo.getId()))
                        .fetchOptionalInto(ExpertisePojo.class)
                        .orElseGet(()-> dslContext
                                .insertInto(EXPERTISE)
                                .set(dslContext.newRecord(EXPERTISE, new ExpertisePojo().setDescriptionI18nId(i18nPojo.getId())))
                                .returning(EXPERTISE.ID) // 指定要返回的字段，这里是ID字段
                                .fetchOptionalInto(ExpertisePojo.class).orElseThrow(Exceptions::unknownError));
                dslContext.update(EDUCATOR_PROFILE_EXTENSION)
                        .set(EDUCATOR_PROFILE_EXTENSION.EXPERTISE_DESCRIPTION_ID, arrayAppend(EDUCATOR_PROFILE_EXTENSION.EXPERTISE_DESCRIPTION_ID, expertisePojo.getId()))
                        .where(EDUCATOR_PROFILE_EXTENSION.EDUCATOR_PROFILE_ID.eq(pojo.getId()))
                        .execute();
            }




            //整理基础数据
            //user数据
//            UserPojo user = dslContext.select(USER.ID)
//                    .from(USER)
//                    .leftJoin(I18N).on(USER.FIST_NAME_I18N_ID.eq(I18N.ID))
//                    .where(I18N.ENGLISH.eq(profile.getFirstNameI18n().getEnglish()))
//                    .fetchOptionalInto(UserPojo.class)
//                    .orElseGet(()->{
//                        I18nPojo firstNameI18n = dslContext
//                                .insertInto(I18N)
//                                .set(dslContext.newRecord(I18N, i18nMapper.toPojo(profile.firstNameI18n)))
//                                .returning(I18N.ID)
//                                .fetchOneInto(I18nPojo.class);
//                        I18nPojo lastNameI18n = dslContext
//                                .insertInto(I18N)
//                                .set(dslContext.newRecord(I18N, i18nMapper.toPojo(profile.firstNameI18n)))
//                                .returning(I18N.ID)
//                                .fetchOneInto(I18nPojo.class);
//                        return dslContext
//                                .insertInto(USER)
//                                .set(dslContext.newRecord(USER, new UserPojo().setFistNameI18nId(firstNameI18n.getId()).setLastNameI18nId(lastNameI18n.getId()).setUserRole(UserRoleEnum.EDUCATOR)))
//                                .returning(USER.ID)
//                                .fetchOneInto(UserPojo.class);
//                    });
//            //educator数据
//            EducatorProfilePojo educator = dslContext.select(EDUCATOR_PROFILE.ID)
//                    .from(EDUCATOR_PROFILE)
//                    .where(EDUCATOR_PROFILE.USER_ID.eq(user.getId()))
//                    .fetchOptionalInto(EducatorProfilePojo.class)
//                    .orElseGet(()-> dslContext
//                            .insertInto(EDUCATOR_PROFILE)
//                            .set(dslContext.newRecord(EDUCATOR_PROFILE, new EducatorProfilePojo().setUserId(user.getId()).setHourlyRate(profile.getHourly_rate())))
//                            .returning(EDUCATOR_PROFILE.ID)
//                            .fetchOneInto(EducatorProfilePojo.class));
//            //educator_extension
//            List<UUID> languages = Lists.newArrayList();
//            for(I18n language : profile.getLanguages()){
//                languages.addAll(load(language.getEnglish(),TagCategoryEnum.LANGUAGE));
//            }
//            // expertise
//            List<UUID> expertises = Lists.newArrayList();
//            for(I18n expertise : profile.getExpertises()){
//                expertises.addAll(load(expertise.getEnglish(),TagCategoryEnum.EXPERTISE));
//            }
//
//            List<UUID> subjectIds = Lists.newArrayList();
//            for(I18n subject : profile.getSubjects()){
//                subjectIds.addAll(load(subject.getEnglish(),TagCategoryEnum.ACADEMIC_MAJOR));
//            }
//
//            List<UUID> schoolIds = Lists.newArrayList();
//            List<UUID> educationIds = Lists.newArrayList();
//
//            for(Education education : profile.getEducations()){
//                UUID schoolId = load(education.getUniversity(),TagCategoryEnum.SCHOOL).get(0);
//                UUID educationId = load(education.getDegree(),TagCategoryEnum.EDUCATION_LEVEL).get(0);
//                schoolIds.add(schoolId);
//                educationIds.add(educationId);
//            }
//
//            UUID cityId = load(profile.getCityI18n().getEnglish(),TagCategoryEnum.CITY).get(0);
//            UUID countryId = load(profile.getCountryI18n().getEnglish(),TagCategoryEnum.COUNTRY).get(0);
//
//
//            EducatorProfileExtensionPojo extension = dslContext.select(EDUCATOR_PROFILE_EXTENSION.ID)
//                    .from(EDUCATOR_PROFILE_EXTENSION)
//                    .where(EDUCATOR_PROFILE_EXTENSION.EDUCATOR_PROFILE_ID.eq(educator.getId()))
//                    .fetchOptionalInto(EducatorProfileExtensionPojo.class)
//                    .orElseGet(()->{
//                        // language
//
//                        return dslContext
//                                .insertInto(EDUCATOR_PROFILE_EXTENSION)
//                                .set(dslContext.newRecord(EDUCATOR_PROFILE_EXTENSION, new EducatorProfileExtensionPojo()
//                                        .setEducatorProfileId(educator.getId())
//                                        .setLanguageId(languages.toArray(new UUID[0]))
//                                        .setExpertiseId(expertises.toArray(new UUID[0]))
//                                        .setDescription(profile.getDescriptionI18n().getEnglish())
//                                        .setCountryId(countryId)
//                                        .setCityId(cityId)
//                                        .setMajorId(subjectIds.toArray(new UUID[0]))
//                                        .setEducationId(educationIds.toArray(new UUID[0]))
//                                        .setEducationSchoolId(schoolIds.toArray(new UUID[0]))
//                                 ))
//                                .returning(EDUCATOR_PROFILE_EXTENSION.ID)
//                                .fetchOneInto(EducatorProfileExtensionPojo.class);
//                    });
//            System.out.println(educator);
//            break;
        }
    }



    private List<UUID> load(String name, TagCategoryEnum tagEnum){
       List<TagPojo> tags = dslContext.select(TAG.ID)
                .from(TAG)
                .leftJoin(I18N).on(TAG.DESCRIPTION_I18N_ID.eq(I18N.ID))
                .where(I18N.ENGLISH.like(name)).and(TAG.TAG_CATEGORY.eq(tagEnum))
                .fetchInto(TagPojo.class);
       if(tags.isEmpty()){
           tags = Lists.newArrayList();
           I18nPojo i18n = dslContext
                   .insertInto(I18N)
                   .set(dslContext.newRecord(I18N, new I18nPojo()
                           .setEnglish(name)
                   ))
                   .returning(I18N.ID)
                   .fetchOneInto(I18nPojo.class);
           TagPojo record = dslContext
                   .insertInto(TAG)
                   .set(dslContext.newRecord(TAG, new TagPojo()
                           .setTagCategory(tagEnum)
                           .setDescriptionI18nId(i18n.getId())
                   ))
                   .returning(TAG.ID)
                   .fetchOneInto(TagPojo.class);
           tags.add(record);
       }
        return     tags .stream()
                .map(TagPojo::getId)
               .collect(Collectors.toList());
    }


//    @PostConstruct
    public void loadLearningHub(){
        List<String> lines = FileUtil.readUtf8Lines("/Users/apple/Documents/lb.json");
        JSONArray lbJsonObjs = JSONUtil.parseArray(lines.get(0));
        for(Object lbObj : lbJsonObjs){
            JSONObject lbJsonObj = (JSONObject)lbObj ;
            LearningHubBO learningHubBO = JSONUtil.toBean(lbJsonObj.toJSONString(1),LearningHubBO.class);

            dslContext.select(I18N.ID)
                    .from(I18N)
                    .where(I18N.ENGLISH.eq(learningHubBO.getTitleI18n().getEnglish()))
                    .fetchInto(I18nPojo.class)
                    .forEach((e)->{
                        dslContext.update(I18N)
                                .set(I18N.CHINESE_TRADITIONAL, learningHubBO.getTitleI18n().getChineseTraditional())
                                .set(I18N.CHINESE_SIMPLIFIED, learningHubBO.getTitleI18n().getChineseSimplified())
                                // 可以继续用.set()添加更多字段
                                .where(I18N.ID.eq(e.getId()))
                                .execute();
                    });

            dslContext.select(I18N.ID)
                    .from(I18N)
                    .where(I18N.ENGLISH.eq(learningHubBO.getDescriptionI18n().getEnglish()))
                    .fetchInto(I18nPojo.class)
                    .forEach((e)->{
                        dslContext.update(I18N)
                                .set(I18N.CHINESE_TRADITIONAL, learningHubBO.getDescriptionI18n().getChineseTraditional())
                                .set(I18N.CHINESE_SIMPLIFIED, learningHubBO.getDescriptionI18n().getChineseSimplified())
                                // 可以继续用.set()添加更多字段
                                .where(I18N.ID.eq(e.getId()))
                                .execute();
                    });

            I18nPojo agendaI18n = new I18nPojo()
                    .setChineseTraditional(learningHubBO.getAgendaI18n().getChineseTraditional())
                    .setEnglish(learningHubBO.getAgendaI18n().getEnglish())
                    .setChineseSimplified(learningHubBO.getAgendaI18n().getChineseSimplified());
            dbI18N.getDao().insert(agendaI18n);

//            I18nPojo descriptionI18n = dslContext.select(I18N.ID)
//                    .from(I18N)
//                    .where(I18N.ENGLISH.eq(learningHubBO.getDescriptionI18n().getEnglish()))
//                    .fetchOptionalInto(I18nPojo.class)
//                    .orElseGet(()->{
//                        I18nPojo i18nPojo = i18nMapper.toPojo(learningHubBO.getDescriptionI18n());
//                        return dslContext
//                                .insertInto(I18N)
//                                .set(dslContext.newRecord(I18N, i18nPojo))
//                                .returning(I18N.ID) // 指定要返回的字段，这里是ID字段
//                                .fetchOptionalInto(I18nPojo.class).orElseThrow(Exceptions::unknownError);
//                    });
//
//            EducatorProfilePojo profile = dslContext.select(EDUCATOR_PROFILE.fields()).from(EDUCATOR_PROFILE,I18N,USER)
//                    .where(EDUCATOR_PROFILE.USER_ID.eq(USER.ID).and(USER.FIST_NAME_I18N_ID.eq(I18N.ID))).and(I18N.ENGLISH.eq(learningHubBO.educator.getEnglish()))
//                    .fetchOptionalInto(EducatorProfilePojo.class)
//                    .orElseThrow(Exceptions::unknownError);
//
//
//
//
//            EventPojo eventPojo = new EventPojo();
//            eventPojo.setTitleI18nId(titleI18n.getId());
//            eventPojo.setDescriptionI18nId(descriptionI18n.getId());
//            switch (learningHubBO.getEventType()){
//                case  "Webinar" :
//                    eventPojo.setEventType(EventTypeEnum.WEBINAR);
//                    break;
//                case  "Course":
//                    eventPojo.setEventType(EventTypeEnum.COURSE);
//                    break;
//            }
//            eventPojo.setPrice(learningHubBO.getPrice());
//            eventPojo.setMaxNumberOfStudent(learningHubBO.getMaxNumberOfStudent());
//            eventPojo.setPosterImage(learningHubBO.getPosterImage());
//            eventPojo.setStartTime(learningHubBO.getStartTime());
//            eventPojo.setEndTime(learningHubBO.getEndTime());
//
//            EventPojo create = dslContext
//                    .insertInto(EVENT)
//                    .set(dslContext.newRecord(EVENT, eventPojo))
//                    .returning(EVENT.ID) // 指定要返回的字段，这里是ID字段
//                    .fetchOptionalInto(EventPojo.class).orElseThrow(Exceptions::unknownError);
//
//            EventExtensionPojo eventExtensionPojo = new EventExtensionPojo();
//            eventExtensionPojo.setAgenda(learningHubBO.agendaI18n.getEnglish());
//            eventExtensionPojo.setEducatorProfileId(profile.getId());
//            eventExtensionPojo.setEventId(create.getId());
//            switch (learningHubBO.getEventStatus()){
//                case  "Open for Registration" :
//                    eventExtensionPojo.setEventStatus(EventStatusEnum.OPEN);
//                    break;
//                case  "Past Event":
//                    eventExtensionPojo.setEventStatus(EventStatusEnum.PAST);
//                    break;
//                default:
//                    eventExtensionPojo.setEventStatus(EventStatusEnum.FULL_CLOSED);
//                    break;
//            }
//            eventExtensionPojo.setEventDates(learningHubBO.getDates());
//
//            dslContext
//                    .insertInto(EVENT_EXTENSION)
//                    .set(dslContext.newRecord(EVENT_EXTENSION, eventExtensionPojo))
//                    .returning(EVENT_EXTENSION.ID) // 指定要返回的字段，这里是ID字段
//                    .fetchOptionalInto(EventExtensionPojo.class).orElseThrow(Exceptions::unknownError);

//            break;





        }

    }


//    @PostConstruct
    public void importSchoolData(){
        List<String> schoolStr = FileUtil.readUtf8Lines("/Users/apple/Downloads/school.json");
        List<School> schools = JSONUtil.toList(schoolStr.get(0),School.class);
        for(School school : schools){
            List<I18nPojo> nameI18nPojos =  dbI18N.getDao().fetchByEnglish(school.getNameI18n().getEnglish());
            I18nPojo nameI18nPojo = i18nMapper.toPojo(school.getNameI18n());

            if(nameI18nPojos.isEmpty()){
                dbI18N.getDao().insert(nameI18nPojo);
            }else{
                nameI18nPojo.setId(nameI18nPojos.get(0).getId());
                dbI18N.getDao().update(nameI18nPojo);
            }
            UUID nameI18nId = nameI18nPojo.getId();

            List<I18nPojo> addressI18nPojos =  dbI18N.getDao().fetchByEnglish(school.getDetailAddressI18n().getEnglish());
            I18nPojo addressI18nPojo = i18nMapper.toPojo(school.getDetailAddressI18n());
            if(addressI18nPojos.isEmpty()){
                dbI18N.getDao().insert(addressI18nPojo);
            }else{
                addressI18nPojo.setId(addressI18nPojos.get(0).getId());
                dbI18N.getDao().update(addressI18nPojo);
            }
            UUID addressI18nId = addressI18nPojo.getId();

            List<SchoolPojo> schoolPojos = dbSchool.getDao().fetchByNameI18nId(nameI18nId);
            SchoolPojo schoolPojo;
            if(schoolPojos.isEmpty()){
                schoolPojo = new SchoolPojo()
                        .setSchoolLevel(SchoolLevelEnum.SECONDARY_SCHOOL)
                        .setNameI18nId(nameI18nId)
                        .setDetailedAddressI18nId(addressI18nId)
                        .setLatitude(school.getLat())
                        .setLongitude(school.getLng());
                dbSchool.getDao().insert(schoolPojo);
            }else{
                schoolPojo = schoolPojos.get(0)
                        .setSchoolLevel(SchoolLevelEnum.SECONDARY_SCHOOL)
                        .setNameI18nId(nameI18nId)
                        .setDetailedAddressI18nId(addressI18nId)
                        .setLatitude(school.getLat())
                        .setLongitude(school.getLng());
                dbSchool.getDao().update(schoolPojo);
            }
        }
    }


//    @PostConstruct
    public void importEcaProfile(){
        String english = "Founder of a school club/ initiative/ NGO  (> 50 members with significant traction)\n" +
                "\n" +
                "Founder of a school club/ initiative/ NGO (> 15 but less than 50 members)\n" +
                "\n" +
                "Founder of a school club/ initiative/NGO  (< 15 members)\n" +
                "\n" +
                "President of Student Council (or equivalent)/ Head Boy/ Head Girl\n" +
                "\n" +
                "School Prefect Team or Key committee member of student government \n" +
                "\n" +
                "President or Vice President of a school club\n" +
                "\n" +
                "Editor in chief or Editor of School newspaper or publications\n" +
                "\n" +
                "Committee roles at school clubs\n" +
                "\n" +
                "Active Member of a School Club\n" +
                "\n" +
                "\n" +
                "None of the above \n";
        List<String> englishStrs = StrUtil.split(english,"\n")
                .stream()
                .filter(StrUtil::isNotEmpty)
                .collect(Collectors.toList());

        String simplifiedStr = "- 创办学校社团/倡议者/非政府组织（成员50人以上，具有显著影响力）\n" +
                "- 创办学校社团/倡议者/非政府组织（成员15人以上但不超过50人）\n" +
                "- 创办学校社团/倡议者/非政府组织（成员少于15人）\n" +
                "- 学生会主席（或同等职务）\n" +
                "- 学校总监团队成员或学生政府关键委员会成员\n" +
                "- 学校社团主席或副主席\n" +
                "- 学校报刊或出版物主编或编辑\n" +
                "- 学校社团委员会职务\n" +
                "- 学校社团积极成员\n" +
                "- 無上述情況\n";

        List<String> simplifiedStrs = StrUtil.split(simplifiedStr,"\n");

        String tranidtionalStr = "- 創辦學校社團/倡議者/非政府組織（成員50人以上，具有顯著影響力）\n" +
                "- 創辦學校社團/倡議者/非政府組織（成員15人以上但不超過50人）\n" +
                "- 創辦學校社團/倡議者/非政府組織（成員少於15人）\n" +
                "- 學生會主席（或同等職務）\n" +
                "- 學校總監團隊成員或學生政府關鍵委員會成員\n" +
                "- 學校社團主席或副主席\n" +
                "- 學校報刊或出版物主編或編輯\n" +
                "- 學校社團委員會職務\n" +
                "- 學校社團積極成員\n"+
                "- 無上述情況\n";

        List<String> traditionalStrs = StrUtil.split(tranidtionalStr,"\n");

        System.out.println("");



    }



//    @PostConstruct
    public void importCountryData(){
        List<String> strs =  FileUtil.readUtf8Lines(new File("/Users/apple/Documents/country.json"));
        String str = StrUtil.join("",strs);
//        System.out.println(str);
        List<Country> countryObjs = JSONUtil.toList(str,Country.class);
        for(Country countryObj : countryObjs){
            //查询I18n
            StaticLog.info("{}",countryObj);
            List<I18nPojo> i18nPojos = dbI18N.getQuery(I18N)
                    .where(I18N.ENGLISH.eq(countryObj.getEn()))
                    .fetchInto(I18nPojo.class);
//            if(i18nPojos.isEmpty()){
//                dbI18N.getDao().insert(new I18nPojo()
//                        .setEnglish(countryObj.getEn())
//                        .setChineseSimplified(countryObj.getCn())
//                );
//                continue;
//            }
            //查询country是否存在
//            List<CountryPojo> list = dbCountry.getDsl().select(COUNTRY.fields()).from(COUNTRY,I18N)
//                    .where(COUNTRY.NAME_I18N_ID.eq(I18N.ID))
//                    .and(I18N.ENGLISH.eq(countryObj.getEn()))
//                    .fetchInto(CountryPojo.class);
//            if(list.isEmpty()){
//                for(I18nPojo exist : i18nPojos){
//                    dbCountry.getDao().insert(new CountryPojo()
//                            .setNameI18nId(exist.getId()));
//                }
//            }


            for(I18nPojo exist : i18nPojos){
                dbI18N.getDao().update(
                        exist.setChineseSimplified(countryObj.getCn())
                );
            }





//            i18nPojo
//                    .ifPresentOrElse(
//                            exist -> {
//                                // ID 非 null，执行更新操作
//                                dbI18N.getDao().update(new I18nPojo()
//                                        .setEnglish(countryObj.getEn())
//                                        .setChineseSimplified(countryObj.getCn())
//                                        .setId(exist.getId())
//                                ); // 假设这是执行更新的方法
//                            },
//                            () -> {
//                                // ID 为 null，执行新增操作
//                                dbI18N.getDao().insert(new I18nPojo()
//                                        .setEnglish(countryObj.getEn())
//                                        .setChineseSimplified(countryObj.getCn())
//                                );
//                            }
//                    );
//            break;
        }
    }

    @FieldNameConstants
    @Data
    @ToString
    public static class Country{
        private String en;
        private String cn;
        private String code;
    }

    /**
     * json中school数据
     */
    @FieldNameConstants
    @Data
    public static class School {
        private I18n nameI18n;
        private String lng;
        private String lat;
        private I18n detailAddressI18n;
    }


    /**
     * excel中educator的数据
     */
    @Data
    static class EducatorProfile{
        private String email;
        private I18n firstNameI18n;
        private I18n lastNameI18n;
        private I18n countryI18n;
        private I18n cityI18n;
        private List<Education> educations;
        private I18n descriptionI18n;
        private Integer hourly_rate;
        private String photo;
        private Integer base_score;
        private List<I18n>  expertises;
        private List<I18n>  expertise_in;
        private List<I18n>  subjects;
        private List<I18n>  languages;
    }

    @Data
    static class Education{
        private String university;
        private String degree;
    }

    @Data
    @FieldNameConstants
    static class LearningHubBO {
        private String eventType;
        private I18n titleI18n;
        private I18n descriptionI18n;
        private I18n agendaI18n;
        private BigDecimal price;
        private BigDecimal maxNumberOfStudent;
        private I18n educator;
        private String eventStatus;
        private String posterImage;
        private LocalDate[] dates;
        private LocalTime startTime;
        private LocalTime endTime;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @FieldNameConstants
    static class StudentQuestionnaireNew extends StudentQuestionnairePojo{
        private I18nPojo titleI18n = new I18nPojo()
                .setEnglish("Milestone Questionnaire");
        private I18nPojo subTitleI18n = new I18nPojo()
                .setEnglish("Milestone Questionnaire");
        private I18nPojo descriptionI18n = new I18nPojo()
                .setEnglish("Milestone Questionnaire");
    }


    @Data
    @EqualsAndHashCode(callSuper = true)
    static class StudentQuestionnaireSectionNew extends StudentQuestionnaireSectionPojo{
        private I18nPojo titleI18n = new I18nPojo()
                .setEnglish("Milestone Questionnaire");
    }


    @Data
    @EqualsAndHashCode(callSuper = true)
    static class StudentQuestionnaireQuestionNew extends StudentQuestionnaireQuestionPojo{
        private I18nPojo descriptionI18n = new I18nPojo()
                .setEnglish("Which of there countries would you consider studying in?");

    }


    @Data
    @EqualsAndHashCode(callSuper = true)
    static class StudentQuestionnaireAnswerNew extends StudentQuestionnaireAnswerPojo{
        private I18nPojo descriptionI18n = new I18nPojo()
                .setEnglish("Which of there countries would you consider studying in?");

    }

    @Data
    static class EcaProfileNew {
        private I18nPojo titleI18n;
        private I18nPojo description;
        private I18nPojo eligibility;
        private String refUrl;
        private String coverImgage;
        private List<I18nPojo> opportunities;
        private String[] majors;
        private Integer[] grade;

        public String getS3CoverImagePath(){
            return "/eca-profile/"+this.coverImgage;
        }
    }

}
