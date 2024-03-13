package unid.monoServerMeta;

public class Pattern {
    public static final String USER_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    public static final String PHONE_NUMBER = "^[0-9]{5,}$";
    public static final String COUNTRY_CODE = "^[+]{1}[0-9]{1,}[-]*[0-9]{1,}$";
    public static final String DATE = "yyyy-MM-dd";
    public static final String COD_NOTIFICATION_DATE = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String TIME_CALENDAR = "HH:00:00";
    public static final String TEAM_BOOKING_DATE = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String YYYYMMDDHH = "yyyy-MM-dd'T'HH:00:00";

    public static final String LONGITUDE = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    public static final String LATITUDE = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
}
