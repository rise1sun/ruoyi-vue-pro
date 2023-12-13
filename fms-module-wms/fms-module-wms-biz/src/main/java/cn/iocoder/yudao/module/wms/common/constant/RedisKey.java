package cn.iocoder.yudao.module.wms.common.constant;


public class RedisKey {
    private static final String BASE_KEY = "wms";
    /**
     * 用户token的key
     */
    public static final String USER_TOKEN_STRING = "userToken:uid_%d";

    public static String getKey(String key, Object... o) {
        return BASE_KEY + String.format(key, o);
    }
}
