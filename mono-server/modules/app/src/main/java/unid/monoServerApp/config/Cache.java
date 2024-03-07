package unid.monoServerApp.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableCaching
@Slf4j
public class Cache {
    @Configuration
    @ConfigurationProperties("spring.redis")
    @Primary
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class RedisCacheProps extends RedisProperties {
        @Bean
        @Primary
        public RedisConnectionFactory redisConnectionFactoryCache() {
            var config = new RedisStandaloneConfiguration(getHost(), getPort());
            config.setDatabase(getDatabase());
            if(StringUtils.isNotEmpty(getUsername())){
                config.setUsername(getUsername());
            }

            if(StringUtils.isNotEmpty(getPassword())){
                config.setUsername(getPassword());
            }
            var clientConfig = LettuceClientConfiguration.builder();
            if (isSsl()) {
                clientConfig.useSsl().disablePeerVerification();
            }
            return new LettuceConnectionFactory(config, clientConfig.build());
        }

        @Bean
        public RedisTemplate<String, String> redisTemplateRefCache(
                RedisConnectionFactory redisConnectionFactoryCache
        ) {
            var redisTemplate = new RedisTemplate<String, String>();
            redisTemplate.setConnectionFactory(redisConnectionFactoryCache);
            return redisTemplate;
        }

        @Bean
        public KeyValueTemplate redisKeyValueTemplateRefCache(
                RedisTemplate<String, String> redisTemplateRefCache
        ) {
            return new KeyValueTemplate(
                    new RedisKeyValueAdapter(redisTemplateRefCache),
                    new RedisMappingContext()
            );
        }
    }

    @Configuration
    @EnableRedisRepositories(
            basePackages = "unid.monoServerApp",
            redisTemplateRef = "redisTemplateRefCache",
            keyValueTemplateRef = "redisKeyValueTemplateRefCache",
            // AWS conflict with SpringBoot, blank = SpringBoot do nothing
            // while on AWS, set the corresponding param to AKE
            enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP,
            keyspaceNotificationsConfigParameter = ""
    )
    public static class RedisCacheConfig {
    }
}
