package unid.monoServerApp.api.user.tag;//package unid.monoServerApp.api.country;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pwh.springWebStarter.response.UnifiedResponse;
import unid.monoServerApp.api.ACL;
import unid.monoServerApp.mapper.TagMapper;
import unid.monoServerMeta.api.TagResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user/{userId}/tags")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@Tag(name = "User Tags")
@Slf4j
@Hidden
public class UserTagsController {
    private final UserTagsService userTagsService;
    private final TagMapper tagMapper;

    @GetMapping
    @ACL(
            authed = true
    )
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get Tags"
    )
    public @Valid UnifiedResponse<List<TagResponse>> getTags(
            @PathVariable("userId") UUID userId
    ) {
        var result = userTagsService.getTags(userId);
        return UnifiedResponse.of(
                tagMapper.toResponse(result)
        );
    }
}
