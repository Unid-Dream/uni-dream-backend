package unid.monoServerApp.api.passionfinder;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwh.coreRsqlJooq.model.PaginationRequest;
import unid.jooqMono.model.tables.pojos.I18nPojo;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.database.table.i18n.DbI18N;
import unid.monoServerApp.mapper.I18nMapper;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.I18n;
import unid.monoServerMeta.model.PassionMajor;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PassionFinderService {
    private final DSLContext dslContext;
    private final DbI18N dbI18N;
    private final I18nMapper i18nMapper;


    /**
     * 批量创建
     * 保护策略:
     *  1.如果异常事务回滚;
     *  2.如果循环保存的时候出现异常,则
     */
    public void create(List<PassionMajorRequest> req){
        req.forEach(obj-> dslContext.transaction(configuration -> {
            DSL.using(configuration);
            try{
                create(obj);
            }catch (Exception e){
                StaticLog.error("insert major failure",e);
            }
        }));
    }

    public void create(PassionMajorRequest req){
        //1.创建major对象,并且针对name,desc保存到i18n,返回nameI18nId,descI18NId,并且设置到major对象中,然后保存到public.passion_major中。获取major_id
        // func insert name into i18n
        // func insert desc into i18n
        // func set name_i18n_id,desc_i18n_id, then insert passion_major
        // return passion_major.id
        I18nPojo nameI18N = i18nMapper.toPojo(req.getNameI18n());
        dbI18N.getDao().insert(nameI18N);

        I18nPojo descI18N = i18nMapper.toPojo(req.getDescriptionI18n());
        dbI18N.getDao().insert(descI18N);

        Record record = dslContext
                .insertInto(
                        DSL.table(DSL.name("passion_major")),
                        DSL.field(DSL.name("name_i18n_id")),
                        DSL.field(DSL.name("desc_i18n_id")))
                .values(nameI18N.getId(),
                        descI18N.getId())
                .returning(DSL.field(DSL.name("id"))) // 假设'id'是自动生成的UUID
                .fetchOne();
        if (record == null) {
            throw Exceptions.unknownError("insert passion_major fail");
        }
        UUID majorId = record.getValue(DSL.field(DSL.name("id"), UUID.class)); // 获取生成的UUID

        //2.save public.subject,逻辑基本与上面一致,但是需要设置majorId
        for(PassionSubjectRequest subject: req.getSubjects()){
            UUID subjectId = create(subject,majorId);

            Optional.ofNullable(subject.getBooks())
                    .orElse(Lists.newArrayList())
                    .forEach((obj)-> create(obj,subjectId));

            Optional.ofNullable(subject.getPodcasts())
                    .orElse(Lists.newArrayList())
                    .forEach((obj)-> create(obj,subjectId));

            Optional.ofNullable(subject.getVideos())
                    .orElse(Lists.newArrayList())
                    .forEach((obj)-> create(obj,subjectId));

            Optional.ofNullable(subject.getAnswers())
                    .orElse(Lists.newArrayList())
                    .forEach((obj)-> create(obj,subjectId));

        }
    }


    public UUID create(PassionSubjectBookRequest req,UUID subjectId){
        UUID nameI18NId = null,authorI18nId = null;
        if(StrUtil.isNotEmpty(req.getNameI18n().getEnglish())){
            I18nPojo nameI18N = i18nMapper.toPojo(req.getNameI18n());
            dbI18N.getDao().insert(nameI18N);
            nameI18NId = nameI18N.getId();
        }

        if(StrUtil.isNotEmpty(req.getAuthorI18n().getEnglish())){
            I18nPojo authorI18N = i18nMapper.toPojo(req.getAuthorI18n());
            dbI18N.getDao().insert(authorI18N);
            authorI18nId = authorI18N.getId();
        }



        Record record = dslContext
                .insertInto(
                        DSL.table(DSL.name("passion_subject_book")),
                        DSL.field(DSL.name("name_i18n_id")),
                        DSL.field(DSL.name("author_i18n_id")),
                        DSL.field(DSL.name("subject_id")),
                        DSL.field(DSL.name("image")))
                .values(nameI18NId,
                        authorI18nId,
                        subjectId,
                        req.getImage())
                .returning(DSL.field(DSL.name("id"))) // 假设'id'是自动生成的UUID
                .fetchOne();
        if (record == null) {
            throw Exceptions.unknownError("insert passion_major fail");
        }
        return record.getValue(DSL.field(DSL.name("id"), UUID.class)); // 获取生成的UUID
    }




    public UUID create(PassionSubjectPodcastRequest req,UUID subjectId){
        UUID nameI18nId = null,authorI18nId = null;
        if(StrUtil.isNotEmpty(req.getNameI18n().getEnglish())){
            I18nPojo nameI18n = i18nMapper.toPojo(req.getNameI18n());
            dbI18N.getDao().insert(nameI18n);
            nameI18nId = nameI18n.getId();
        }

        if(StrUtil.isNotEmpty(req.getAuthorI18n().getEnglish())){
            I18nPojo authorI18n = i18nMapper.toPojo(req.getAuthorI18n());
            dbI18N.getDao().insert(authorI18n);
            authorI18nId = authorI18n.getId();
        }

        Record record = dslContext
                .insertInto(
                        DSL.table(DSL.name("passion_subject_podcast")),
                        DSL.field(DSL.name("name_i18n_id")),
                        DSL.field(DSL.name("author_i18n_id")),
                        DSL.field(DSL.name("subject_id")),
                        DSL.field(DSL.name("url")))
                .values(nameI18nId,
                        authorI18nId,
                        subjectId,
                        req.getUrl())
                .returning(DSL.field(DSL.name("id"))) // 假设'id'是自动生成的UUID
                .fetchOne();
        if (record == null) {
            throw Exceptions.unknownError("insert passion_major fail");
        }
        return record.getValue(DSL.field(DSL.name("id"), UUID.class)); // 获取生成的UUID
    }

    public UUID create(PassionSubjectVideoRequest req,UUID subjectId){
        UUID nameI18nId = null,authorI18nId = null;

        if(StrUtil.isNotEmpty(req.getNameI18n().getEnglish())){
            I18nPojo nameI18n = i18nMapper.toPojo(req.getNameI18n());
            dbI18N.getDao().insert(nameI18n);
            nameI18nId = nameI18n.getId();
        }

        if(StrUtil.isNotEmpty(req.getAuthorI18n().getEnglish())){
            I18nPojo authorI18n = i18nMapper.toPojo(req.getAuthorI18n());
            dbI18N.getDao().insert(authorI18n);
            authorI18nId = authorI18n.getId();
        }




        Record record = dslContext
                .insertInto(
                        DSL.table(DSL.name("passion_subject_video")),
                        DSL.field(DSL.name("name_i18n_id")),
                        DSL.field(DSL.name("author_i18n_id")),
                        DSL.field(DSL.name("subject_id")),
                        DSL.field(DSL.name("url")),
                        DSL.field(DSL.name("type")))
                .values(nameI18nId,
                        authorI18nId,
                        subjectId,
                        req.getUrl(),
                        req.getType())
                .returning(DSL.field(DSL.name("id"))) // 假设'id'是自动生成的UUID
                .fetchOne();
        if (record == null) {
            throw Exceptions.unknownError("insert passion_major fail");
        }
        return record.getValue(DSL.field(DSL.name("id"), UUID.class)); // 获取生成的UUID
    }

    public void create(I18n answer,UUID subjectId){
        UUID answerI18nId = null;
        if(StrUtil.isNotEmpty(answer.getEnglish())){
            I18nPojo answerI18n = i18nMapper.toPojo(answer);
            dbI18N.getDao().insert(answerI18n);
            answerI18nId = answerI18n.getId();
        }

        Record record = dslContext
                .insertInto(
                        DSL.table(DSL.name("passion_subject_answer")),
                        DSL.field(DSL.name("answer_i18n_id")),
                        DSL.field(DSL.name("subject_id")))
                .values(answerI18nId,
                        subjectId)
                .returning(DSL.field(DSL.name("id"))) // 假设'id'是自动生成的UUID
                .fetchOne();
        if (record == null) {
            throw Exceptions.unknownError("insert passion_major fail");
        }
    }



    public UUID create(PassionSubjectRequest req,UUID majorId){
        I18nPojo nameI18N = i18nMapper.toPojo(req.getNameI18n());
        dbI18N.getDao().insert(nameI18N);

        I18nPojo descI18N = i18nMapper.toPojo(req.getDescriptionI18n());
        dbI18N.getDao().insert(descI18N);

        Record record = dslContext
                .insertInto(
                        DSL.table(DSL.name("passion_subject")),
                        DSL.field(DSL.name("name_i18n_id")),
                        DSL.field(DSL.name("desc_i18n_id")),
                        DSL.field(DSL.name("major_id")))
                .values(nameI18N.getId(),
                        descI18N.getId(),
                        majorId)
                .returning(DSL.field(DSL.name("id"))) // 假设'id'是自动生成的UUID
                .fetchOne();
        if (record == null) {
            throw Exceptions.unknownError("insert passion_major fail");
        }
        return record.getValue(DSL.field(DSL.name("id"), UUID.class)); // 获取生成的UUID
    }


    //查询passion finder page
    public List<PassionMajor> list(PaginationRequest payload){
        //query passion major page list
        Result<Record> records = dslContext
                .resultQuery("select * from passion_major limit ?,?", payload.getPage(),payload.getLimit())
                .fetch();
        //based this list translate to i18n
        List<PassionMajor> list = Lists.newArrayList();
        for(Record record : records){
            PassionMajor major = new PassionMajor();
            Optional<I18n> nameI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("passion_name_i18n_id")).fetchOptionalInto(I18n.class);
            Optional<I18n> descriptionI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("passion_description_i18n_id")).fetchOptionalInto(I18n.class);
            nameI18n.ifPresent(major::setTitleI18n);
            descriptionI18n.ifPresent(major::setDescriptionI18n);
            list.add(major);
        }
        int totalRecords = dslContext
                .fetchCount(DSL.selectFrom("passion_major"));
        long totalPages = (totalRecords + payload.getLimit() - 1) / payload.getLimit();
        return list;
     }

     public void toResponse(){

     }
}
