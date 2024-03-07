package unid.monoServerApp.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeZoneUtils {

    //获取香港时间
    public static LocalDateTime getHongKongLocalDateTime(LocalDateTime localDateTime){
        // 定义香港时区
        ZoneId hongKongZoneId = ZoneId.of("Asia/Hong_Kong");
        // 将当前时间转换为香港时区的时间
        ZonedDateTime hongKongZonedTime = localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(hongKongZoneId);
        // 从ZonedDateTime转换回LocalDateTime获取不带时区的时间
        return hongKongZonedTime.toLocalDateTime();
    }
}
