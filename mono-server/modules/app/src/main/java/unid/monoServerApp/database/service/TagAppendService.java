package unid.monoServerApp.database.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import unid.jooqMono.model.enums.TagTargetEnum;
import unid.jooqMono.model.tables.pojos.TaggingPojo;
import unid.monoServerApp.cache.CacheTags;
import unid.monoServerApp.database.table.educatorProfile.DbEducatorProfile;
import unid.monoServerApp.database.table.studentProfile.DbStudentProfile;
import unid.monoServerApp.database.table.tag.DbTag;
import unid.monoServerApp.database.table.tagging.DbTagging;
import unid.monoServerApp.queue.MessageProducer;
import unid.monoServerApp.queue.model.TaggingRequestPayload;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TagAppendService {
    private final DbTagging dbTagging;
    private final ObjectMapper objectMapper;
    private final MessageProducer messageProducer;

    @CacheEvict(value = CacheTags.USER_TAGS, key = "#data.user.id")
    public void refresh(DbStudentProfile.Result data) {
        var tags = new HashSet<DbTag.Result>();
        processJsonNode(toJsonNode(data), tags);
        messageProducer.sendTaggingRequest(
                TaggingRequestPayload.builder()
                        .target(TagTargetEnum.STUDENT_PROFILE)
                        .targetId(data.getId())
                        .tags(tags)
                        .build()
        );
    }

    @CacheEvict(value = CacheTags.USER_TAGS, key = "#data.user.id")
    public void refresh(DbEducatorProfile.Result data) {
        var tags = new HashSet<DbTag.Result>();
        processJsonNode(toJsonNode(data), tags);
        messageProducer.sendTaggingRequest(
                TaggingRequestPayload.builder()
                        .target(TagTargetEnum.EDUCATOR_PROFILE)
                        .targetId(data.getId())
                        .tags(tags)
                        .build()
        );
    }

    public void refresh(TagTargetEnum target, UUID targetId, Collection<DbTag.Result> tags) {
        var table = dbTagging.getTable();
        dbTagging.getDsl().deleteFrom(table)
                .where(table.TARGET.eq(target).and(table.TARGET_ID.eq(targetId)))
                .execute();
        dbTagging.getDao().insert(
                tags.stream()
                        .map(tag -> new TaggingPojo()
                                .setTagId(tag.getId())
                                .setTarget(target)
                                .setTargetId(targetId)
                        ).collect(Collectors.toList())
        );
    }

    @SneakyThrows
    private JsonNode toJsonNode(Object data) {
        var jsonString = objectMapper.writeValueAsString(data);
        return objectMapper.readTree(jsonString);
    }

    private void processJsonNode(
            JsonNode jsonNode,
            Collection<DbTag.Result> targetObjectOutput
    ) {
        var targetType = DbTag.Result.class;
        if (jsonNode.isArray()) {
            jsonNode.elements().forEachRemaining(element -> {
                if (element.isObject() || element.isArray()) {
                    processJsonNode(element, targetObjectOutput);
                }
            });
        } else if (jsonNode.isObject()) {
            jsonNode.fields().forEachRemaining(entry -> {
                var valueNode = entry.getValue();
                if (valueNode != null) {
                    var targetField = DbTag.Result.Fields.INTERNAL_IDENTIFIER_A12345;
                    if (
                            valueNode.get(targetField) != null
                                    && valueNode.get(targetField).asText()
                                    .equals(DbTag.Result.INTERNAL_IDENTIFIER_A12345_VALUE)
                    ) {
                        targetObjectOutput.add(objectMapper.convertValue(valueNode, targetType));
                    } else if (valueNode.isObject() || valueNode.isArray()) {
                        processJsonNode(valueNode, targetObjectOutput);
                    }
                }
            });
        }
    }
}
