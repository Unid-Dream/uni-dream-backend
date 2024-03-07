package unid.monoServerApp.api.passionfinder;


import cn.hutool.core.util.StrUtil;
import cn.hutool.db.PageResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.coreRsqlJooq.jooq.PaginatedQuery;
import pwh.coreRsqlJooq.jooq.PaginatedQuerySorting;
import pwh.coreRsqlJooq.model.PaginationRequest;
import pwh.coreRsqlJooq.model.PaginationResponse;
import pwh.coreRsqlJooq.model.PaginationResult;
import pwh.coreRsqlJooq.rsql.OrderingVisitor;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.monoServerApp.Constant;
import unid.monoServerApp.api.schoolIdentity.SchoolIdentityPagination;
import unid.monoServerApp.database.table.academicMajor.DbAcademicMajor;
import unid.monoServerApp.http.RequestHolder;
import unid.monoServerApp.mapper.CountryMapper;
import unid.monoServerMeta.api.*;
import unid.monoServerMeta.model.*;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;
import static org.jooq.TableOptions.table;
import static org.jooq.impl.DSL.*;

@RestController
@RequestMapping("api/academicMajor")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Passion Finder")
@Slf4j
public class PassionFinderController {



    private final PassionFinderService passionFinderService;
    private final ObjectMapper objectMapper;
    private final DSLContext dslContext;
    private final CountryMapper countryMapper;

    @PostMapping("list")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create By List"
    )
    public @Valid UnifiedResponse<PassionMajorResponse> create(
            @RequestBody @Valid
            List<PassionMajorRequest> req
    ) {
        passionFinderService.create(req);
        return UnifiedResponse.of(
               null
        );
    }


    @PostMapping()
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create By One"
    )
    public @Valid UnifiedResponse<PassionMajorResponse> create(
            @RequestBody @Valid
            PassionMajorRequest req
    ) {
        passionFinderService.create(req);
        return UnifiedResponse.of(
                null
        );
    }

    @GetMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query List"
    )
    public UnifiedResponse<List<PassionMajor>> list(){
        Result<Record> records = dslContext.resultQuery("select * from passion_major").fetch();
        List<PassionMajor> list = new ArrayList<>();
        for(Record record : records){
            PassionMajor major = new PassionMajor();
            Optional<I18n> majorNameI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("name_i18n_id")).fetchOptionalInto(I18n.class);
            Optional<I18n> majorDescriptionI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("desc_i18n_id")).fetchOptionalInto(I18n.class);
            majorNameI18n.ifPresent(major::setTitleI18n);
            majorDescriptionI18n.ifPresent(major::setDescriptionI18n);
            major.setId((UUID) record.getValue("id"));
            //query subject
            List<PassionSubject> subjects = dslContext.resultQuery("select * from passion_subject where major_id = ?",record.getValue("id")).fetchInto(PassionSubject.class);
            for(PassionSubject subject: subjects){
                Optional<I18n> subjectNameI18n = dslContext.resultQuery("select * from i18n where id = ? ",subject.getNameI18nId()).fetchOptionalInto(I18n.class);
                Optional<I18n> subjectDescriptionI18n = dslContext.resultQuery("select * from i18n where id = ? ",subject.getDescI18nId()).fetchOptionalInto(I18n.class);
                subjectNameI18n.ifPresent(subject::setTitleI18n);
                subjectDescriptionI18n.ifPresent(subject::setDescriptionI18n);
            }
            major.setSubjects(subjects);
            major.setIconPath(record.getValue("icon_path").toString());
            list.add(major);
        }
        return UnifiedResponse.of(list);
    }


    @GetMapping("{majorId}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Subject List"
    )
    public UnifiedResponse<PassionMajor> list(@PathVariable UUID majorId){
        Record record = dslContext.resultQuery("select * from passion_major where id = ?",majorId).fetchOne();
        Optional<I18n> majorNameI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("name_i18n_id")).fetchOptionalInto(I18n.class);
        Optional<I18n> majorDescriptionI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("desc_i18n_id")).fetchOptionalInto(I18n.class);
        PassionMajor major = new PassionMajor();
        majorNameI18n.ifPresent(major::setTitleI18n);
        majorDescriptionI18n.ifPresent(major::setDescriptionI18n);
        major.setId((UUID) record.getValue("id"));
        major.setIconPath(record.getValue("icon_path").toString());

        //query subject
        List<PassionSubject> subjects = dslContext.resultQuery("select * from passion_subject where major_id = ?",record.getValue("id")).fetchInto(PassionSubject.class);
        for(PassionSubject subject: subjects){
            Optional<I18n> subjectNameI18n = dslContext.resultQuery("select * from i18n where id = ? ",subject.getNameI18nId()).fetchOptionalInto(I18n.class);
            Optional<I18n> subjectDescriptionI18n = dslContext.resultQuery("select * from i18n where id = ? ",subject.getDescI18nId()).fetchOptionalInto(I18n.class);
            subjectNameI18n.ifPresent(subject::setTitleI18n);
            subjectDescriptionI18n.ifPresent(subject::setDescriptionI18n);
        }
        major.setSubjects(subjects);
        return UnifiedResponse.of(major);
    }


    @GetMapping("/{majorId}/subject/{subjectId}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query One Subject"
    )
    public UnifiedResponse<PassionMajor> getOne(@PathVariable UUID majorId,@PathVariable UUID subjectId){
        Record majorRecord = dslContext.resultQuery("select * from passion_major where id = ?",majorId).fetchOne();
        PassionMajor major = new PassionMajor();
        Optional<I18n> majorNameI18n = dslContext.resultQuery("select * from i18n where id = ? ",majorRecord.getValue("name_i18n_id")).fetchOptionalInto(I18n.class);
        Optional<I18n> majorDescriptionI18n = dslContext.resultQuery("select * from i18n where id = ? ",majorRecord.getValue("desc_i18n_id")).fetchOptionalInto(I18n.class);
        majorNameI18n.ifPresent(major::setTitleI18n);
        majorDescriptionI18n.ifPresent(major::setDescriptionI18n);
        major.setId((UUID) majorRecord.getValue("id"));
        major.setIconPath(majorRecord.getValue("icon_path").toString());
        //query subject
        List<PassionSubject> subjects = dslContext.resultQuery("select * from passion_subject where id = ?",subjectId).fetchInto(PassionSubject.class);
        for(PassionSubject obj: subjects){
            Consumer<PassionSubject> subjectConsumer = (e)->{
                I18n nameI18n = dslContext.resultQuery("select * from i18n where id = ? ",e.getNameI18nId()).fetchOneInto(I18n.class);
                I18n descI18n = dslContext.resultQuery("select * from i18n where id = ? ",e.getDescI18nId()).fetchOneInto(I18n.class);
                obj.setTitleI18n(nameI18n);
                obj.setDescriptionI18n(descI18n);
            };

            Consumer<PassionSubject> bookConsumer = (e)->{
                Result<Record> records = dslContext.resultQuery("select * from passion_subject_book where subject_id = ? ",obj.getId()).fetch();
                List<PassionSubjectBook> books = Lists.newArrayList();
                records.forEach(record->{
                    PassionSubjectBook book = new PassionSubjectBook();
                    I18n nameI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("name_i18n_id")).fetchOneInto(I18n.class);
                    I18n authorI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("author_i18n_id")).fetchOneInto(I18n.class);
                    book.setTitleI18n(nameI18n);
                    book.setAuthorI18n(authorI18n);
                    book.setImage(record.getValue("image").toString());
                    books.add(book);
                });
                e.setBooks(books);
            };

            Consumer<PassionSubject> podcastConsumer = (e)->{
                Result<Record> records = dslContext.resultQuery("select * from passion_subject_podcast where subject_id = ? ",obj.getId()).fetch();
                List<PassionSubjectPodcast> podcasts = Lists.newArrayList();
                records.forEach(record->{
                    PassionSubjectPodcast podcast = new PassionSubjectPodcast();
                    I18n nameI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("name_i18n_id")).fetchOneInto(I18n.class);
                    I18n authorI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("author_i18n_id")).fetchOneInto(I18n.class);
                    podcast.setTitleI18n(nameI18n);
                    podcast.setAuthorI18n(authorI18n);
                    podcast.setUrl(StrUtil.trim(record.getValue("url").toString()));
                    podcasts.add(podcast);
                });
                e.setPodcasts(podcasts);
            };

            Consumer<PassionSubject> videoConsumer = (e)->{
                Result<Record> records = dslContext.resultQuery("select * from passion_subject_video where subject_id = ? ",obj.getId()).fetch();
                List<PassionSubjectVideo> videos = Lists.newArrayList();
                records.forEach(record->{
                    PassionSubjectVideo video = new PassionSubjectVideo();
                    I18n nameI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("name_i18n_id")).fetchOneInto(I18n.class);
                    I18n authorI18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("author_i18n_id")).fetchOneInto(I18n.class);
                    video.setTitleI18n(nameI18n);
                    video.setAuthorI18n(authorI18n);
                    video.setUrl(record.getValue("url") == null?"":record.getValue("url").toString());
                    video.setType(record.getValue("type").toString());
                    videos.add(video);
                });
                e.setVideos(videos);
            };

            Consumer<PassionSubject> answerConsumer = (e)->{
                Result<Record> records = dslContext.resultQuery("select * from passion_subject_answer where subject_id = ? ",obj.getId()).fetch();
                List<I18n> list = Lists.newArrayList();
                records.forEach(record->{
                    PassionSubjectAnswer answer = new PassionSubjectAnswer();
                    I18n i18n = dslContext.resultQuery("select * from i18n where id = ? ",record.getValue("answer_i18n_id")).fetchOneInto(I18n.class);
                    list.add(i18n);
                });
                e.setAnswers(list);
            };
            subjectConsumer.andThen(bookConsumer)
                    .andThen(podcastConsumer)
                    .andThen(videoConsumer)
                    .andThen(answerConsumer).accept(obj);
        }
        major.setSubjects(subjects);
        return UnifiedResponse.of(major);
    }
}
