package cn.iocoder.yudao.module.wms.common.aspect;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.wms.common.annotation.RedissonLock;
import cn.iocoder.yudao.module.wms.common.service.RedissonLockService;
import cn.iocoder.yudao.module.wms.common.utils.SpElUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 *
 * @author jiangfeng
 * @date 2023/11/17
 */
@Component
@Order(0)
@Aspect
public class RedissonLockAspect{


    @Resource
    private RedissonLockService redissonLockService;

    @Around("@annotation(cn.iocoder.yudao.module.wms.common.annotation.RedissonLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);
        String prefix = StrUtil.isBlank(redissonLock.prefixKey()) ? SpElUtils.getMethodKey(method) : redissonLock.prefixKey();//默认方法限定名+注解排名（可能多个）
        String key = SpElUtils.parseSpEl(method, joinPoint.getArgs(), redissonLock.key());
        return redissonLockService.executeWithLock(prefix + ":" + key, redissonLock.waitTime(), redissonLock.unit(), joinPoint::proceed);
    }

}
