package unid.monoServerApp.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SerialNumberUtils {
    private static final String ORDER_ID_KEY_PREFIX = "order:";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();


    public static String generateOrderNumber(String prefix, RedisTemplate<String, String> redisTemplate) {
        String orderId;
        do {
            orderId = prefix + generateRandomString(8);
        } while (redisTemplate.opsForValue().get(ORDER_ID_KEY_PREFIX + orderId) != null);

        // 将生成的订单号存储到Redis中，避免重复，这里可以设置一个适当的过期时间
        redisTemplate.opsForValue().set(ORDER_ID_KEY_PREFIX + orderId, "used");

        return orderId;
    }

    private static String generateRandomString(int length) {
        return IntStream.range(0, length)
                .map(i -> CHARACTERS.charAt(random.nextInt(CHARACTERS.length())))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }
}
