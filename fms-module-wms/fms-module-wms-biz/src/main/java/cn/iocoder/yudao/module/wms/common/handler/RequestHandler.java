package cn.iocoder.yudao.module.wms.common.handler;

/**
 *
 * @author jiangfeng
 * @date 2023/11/16
 */
public class RequestHandler {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void set(Long uid) {
        threadLocal.set(uid);
    }

    public static Long get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
