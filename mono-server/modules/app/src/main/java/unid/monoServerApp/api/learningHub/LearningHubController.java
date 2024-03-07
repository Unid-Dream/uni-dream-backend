package unid.monoServerApp.api.learningHub;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.jooqMono.model.enums.EventTypeEnum;
import unid.monoServerApp.Exceptions;
import unid.monoServerApp.api.ACL;
import unid.monoServerMeta.api.EducatorResponse;
import unid.monoServerMeta.api.LearningHubResponse;
import unid.monoServerMeta.api.UniPageResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/learning-hub/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "Learning Hub")
@Slf4j
public class LearningHubController {
    private final LearningHubService learningHubService;

    @GetMapping("/page")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Query Page"
    )
    public @Valid UnifiedResponse<JSONObject> page(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) EventTypeEnum eventType) {
        if(pageSize == null || pageNumber == null){
            throw Exceptions.badRequest(" Page Parameter Must Not Empty");
        }
        return UnifiedResponse.of(
                learningHubService.page(
                        pageNumber,
                        pageSize,
                        eventType)
        );
    }


    @GetMapping("{id}")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get One"
    )
    public @Valid UnifiedResponse<LearningHubResponse> getOne(
            @PathVariable UUID id) {
        return UnifiedResponse.of(
                learningHubService.getOneBy(id)
        );
    }


    @PostMapping("{id}/{studentProfileId}/enroll")
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Enroll One"
    )
    public @Valid UnifiedResponse<UUID> enroll(
            @PathVariable UUID id,
            @PathVariable UUID studentProfileId) {
        return UnifiedResponse.of(learningHubService.enroll(id,studentProfileId));
    }
}
