package pwh.springAws.queue;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.autoconfigure.messaging.SqsAutoConfiguration;
import io.awspring.cloud.core.region.RegionProvider;
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import pwh.springAws.annotation.QueueEnabled;

import java.util.Collections;

@Configuration
@AutoConfigureBefore({SqsAutoConfiguration.class})
@QueueEnabled
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class QueueConfig {
    private final ObjectMapper objectMapper;

    @Bean
    @QueueEnabled
    ClientConfiguration awsClientConfiguration() {
        return new ClientConfiguration();
    }

    @Bean
    @Primary
    @QueueEnabled
    AmazonSQSAsync amazonSQSAsync(
            AWSCredentialsProvider awsCredentialsProvider,
            RegionProvider regionProvider,
            ClientConfiguration clientConfiguration
    ) {
        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withClientConfiguration(clientConfiguration)
                .withRegion(regionProvider.getRegion().getName())
                .build();
    }


    @Bean
    @QueueEnabled
    public QueueMessageHandlerFactory queueMessageHandlerFactory() {
        var factory = new QueueMessageHandlerFactory();
        var messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setObjectMapper(objectMapper);
        messageConverter.setSerializedPayloadClass(String.class);
        messageConverter.setStrictContentTypeMatch(false);
        factory.setMessageConverters(Collections.singletonList(messageConverter));
        return factory;
    }

    @Bean
    @QueueEnabled
    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
        var notificationMessageTemplate = new NotificationMessagingTemplate(amazonSNS);
        var messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setSerializedPayloadClass(String.class);
        messageConverter.setObjectMapper(objectMapper);
        notificationMessageTemplate.setMessageConverter(messageConverter);
        return notificationMessageTemplate;
    }
}
