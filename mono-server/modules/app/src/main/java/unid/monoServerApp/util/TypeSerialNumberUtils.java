package unid.monoServerApp.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import pwh.coreStarter.type.NamedEnum;
import unid.monoServerMeta.model.SerialNumberType;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TypeSerialNumberUtils {
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

    public static String generate(SerialNumberType type, RedisTemplate<String, String> redisTemplate) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        // 使用 Redis 的 INCR 命令实现序列号的自增
        Long increment = ops.increment("serial:" + type.getValue());
        // 生成六位的序列号字符串，不足六位时左侧补零
        return type.getValue() + String.format("%06d", increment);
    }

    private static String generateRandomString(int length) {
        return IntStream.range(0, length)
                .map(i -> CHARACTERS.charAt(random.nextInt(CHARACTERS.length())))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    


}
