package cn.iocoder.yudao.module.wms.common.event.listener;

import cn.iocoder.yudao.module.wms.common.constant.MapKey;
import cn.iocoder.yudao.module.wms.common.event.InboundEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;

/**
 * @author jiangfeng
 * @date 2023/12/20
 */
@Slf4j
@Component
public class InboundListener {

    @Async
    @TransactionalEventListener(classes = InboundEvent.class, phase = TransactionPhase.BEFORE_COMMIT, fallbackExecution = true)
    public void Inbound(InboundEvent event) {
        System.out.println(event.getBarcodeList());
        HashMap<String, Object> params = event.getParams();
        String tray = (String) params.get(MapKey.TRAY);
        System.out.println(tray);

    }
}
