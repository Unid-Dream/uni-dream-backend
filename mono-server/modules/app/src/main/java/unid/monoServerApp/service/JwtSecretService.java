package unid.monoServerApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import unid.monoServerApp.cache.CacheTags;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
class JwtSecretService {
    @CachePut(value = CacheTags.JWT_TOKEN_SECRET, key = "#userId")
    public String generateRandomSecret(UUID userId) {
        return String.format("%s-%s", UUID.randomUUID(), UUID.randomUUID());
    }

    @Cacheable(value = CacheTags.JWT_TOKEN_SECRET, key = "#userId")
    public String getSecret(UUID userId) {
        return "";
    }
}
