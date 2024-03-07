package unid.monoServerApp.config;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import unid.jooqMono.model.enums.UserRoleEnum;
import unid.monoServerApp.api.ACL;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OpenAPI {
    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openAPI -> {
            openAPI.setInfo(
                    new Info()
                            .title("Uni-D OpenAPI Definition")
                            .description("Uni-D OpenAPI Documentation")
            );
        };
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
            var acl = Optional.ofNullable(handlerMethod.getMethod().getAnnotation(ACL.class));
            if (acl.isPresent()) {
                var annotation = acl.get();
                appendDescription(
                        operation,
                        new LinkedHashMap<>() {{
                            put("Allow Unauthenticated", List.of(String.valueOf(annotation.noAuthed())));
                            put("Allow Authenticated", List.of(String.valueOf(annotation.authed())));
                            put("Allowed Roles", ObjectUtils.isEmpty(annotation.allowedRoles())
                                    ? List.of("ALL")
                                    : Arrays.stream(annotation.allowedRoles())
                                    .map(UserRoleEnum::getLiteral)
                                    .collect(Collectors.toList())
                            );
                            put("Allow Owner Only", List.of(
                                            String.valueOf(annotation.matchingSessionProfileId()
                                                    || annotation.matchingSessionUserId())
                                    )
                            );
                            put("Administrative Roles Override", List.of(
                                            String.valueOf(annotation.skipMatchingForAdministrative())
                                    )
                            );
                        }}
                );
            }
            return operation;
        };
    }

    private void appendDescription(Operation operation, LinkedHashMap<String, List<String>> descriptions) {
        var originalDescription = StringUtils.isNotBlank(operation.getDescription())
                ? String.format("%s\n\n", operation.getDescription())
                : "";
        operation.setDescription(
                String.format(
                        "%s%s",
                        originalDescription,
                        descriptions.entrySet().stream()
                                .map(desc -> String.format("**%s**: %s", desc.getKey(), String.join(", ", desc.getValue())))
                                .collect(Collectors.joining("\n\n"))
                )
        );
    }
}
