package pwh.coreRsqlJooq.jooq;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@ToString
public class PaginationCursor {
    @Getter
    private final List<String> seeks;
    @Getter
    private final List<String> firstSeeks;
    @Getter
    private final long limit;
    @Getter
    private final String query;
    @Getter
    private final String sort;
    @Getter
    private final Direction direction;
    @Getter
    private final String optionalOwnerIdentifier;
    @Getter
    private final Long totalCount;

    @JsonCreator
    public PaginationCursor(
            @JsonProperty("seeks") List<String> seeks,
            @JsonProperty("firstSeeks") List<String> firstSeeks,
            @JsonProperty("limit") long limit,
            @JsonProperty("query") String query,
            @JsonProperty("sort") String sort,
            @JsonProperty("direction") Direction direction,
            @JsonProperty("ownerIdentifier") String optionalOwnerIdentifier,
            @JsonProperty("totalCount") Long totalCount
    ) {
        this.seeks = seeks;
        this.firstSeeks = firstSeeks;
        this.limit = limit;
        this.query = query;
        this.sort = sort;
        this.direction = direction;
        this.optionalOwnerIdentifier = optionalOwnerIdentifier;
        this.totalCount = totalCount;
    }

    private static StandardPBEStringEncryptor getEncryptor(String seed) {
        var encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(seed);
        return encryptor;
    }

    @SneakyThrows
    public static PaginationCursor fromEncodedString(
            String encodedCursor,
            String keySet,
            ObjectMapper objectMapper) {
        var decoded = Base64.getDecoder().decode(encodedCursor.getBytes(StandardCharsets.UTF_8));
        var decrypted = getEncryptor(keySet).decrypt(new String(decoded, StandardCharsets.UTF_8));
        return objectMapper.readValue(decrypted, new TypeReference<>() {
        });
    }

    @SneakyThrows
    @JsonIgnore
    public String getEncodedString(
            String keySet,
            ObjectMapper objectMapper
    ) {
        var plain = objectMapper.writeValueAsString(this);
        var encrypted = getEncryptor(keySet).encrypt(plain);
        var encoded = Base64.getEncoder().encode(encrypted.getBytes(StandardCharsets.UTF_8));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public enum Direction {
        NEXT,
        PREVIOUS
    }
}
