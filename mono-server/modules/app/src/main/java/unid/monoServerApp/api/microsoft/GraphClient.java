package unid.monoServerApp.api.microsoft;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import unid.monoServerApp.Properties;

import java.util.Arrays;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class GraphClient {
    private final Properties properties;

    @Bean
    public GraphServiceClient graphServiceClient() {
        var scopes = Arrays.asList(
                "https://graph.microsoft.com/.default"
        );

        var credential = new ClientSecretCredentialBuilder()
                .clientId(properties.getMsClientId())
                .tenantId(properties.getMsTenantId())
                .clientSecret(properties.getMsSecretValue())
                .build();

        var authProvider = new TokenCredentialAuthProvider(
                scopes,
                credential
        );
        return GraphServiceClient
                .builder()
                .authenticationProvider(authProvider)
                .buildClient();
    }
}
