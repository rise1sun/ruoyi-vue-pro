package cn.iocoder.yudao.module.wms.transaction.aspect;

import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.module.wms.common.utils.JsonUtils;
import cn.iocoder.yudao.module.wms.transaction.annotation.SecureInvoke;
import cn.iocoder.yudao.module.wms.transaction.domain.dto.SecureInvokeDTO;
import cn.iocoder.yudao.module.wms.transaction.domain.entity.SecureInvokeRecord;
import cn.iocoder.yudao.module.wms.transaction.service.SecureInvokeHolder;
import cn.iocoder.yudao.module.wms.transaction.service.SecureInvokeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE + 1)//确保最先执行
@Component
public class SecureInvokeAspect {
    @Autowired
    private SecureInvokeService secureInvokeService;

    @Around("@annotation(secureInvoke)")
    public Object around(ProceedingJoinPoint joinPoint, SecureInvoke secureInvoke) throws Throwable {
        boolean async = secureInvoke.async();
        boolean inTransaction = TransactionSynchronizationManager.isActualTransactionActive();
        //非事务状态，直接执行，不做任何保证。
        if (SecureInvokeHolder.isInvoking() || !inTransaction) {
            return joinPoint.proceed();
        }
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        List<String> parameters = Stream.of(method.getParameterTypes()).map(Class::getName).collect(Collectors.toList());
        SecureInvokeDTO dto = SecureInvokeDTO.builder()
                .args(JsonUtils.toStr(joinPoint.getArgs()))
                .className(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(JsonUtils.toStr(parameters))
                .build();
        SecureInvokeRecord record = SecureInvokeRecord.builder()
                .secureInvokeDTO(dto)
                .maxRetryTimes(secureInvoke.maxRetryTimes())
                .nextRetryTime(DateUtil.offsetMinute(new Date(), (int) SecureInvokeService.RETRY_INTERVAL_MINUTES))
                .build();
        secureInvokeService.invoke(record, async);
        return null;
    }
}
