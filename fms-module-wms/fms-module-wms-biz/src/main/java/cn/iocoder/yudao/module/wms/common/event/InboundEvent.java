package cn.iocoder.yudao.module.wms.common.event;

import cn.iocoder.yudao.module.wms.dal.dataobject.barcode.BarcodeDO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.List;

/**
 * @author jiangfeng
 * @date 2023/12/20
 */
@Getter
public class InboundEvent extends ApplicationEvent {
    private final List<String> barcodeList;
    private final HashMap<String,Object> params;

    public InboundEvent(Object source, List<String> list, HashMap<String,Object> map) {
        super(source);
        this.barcodeList = list;
        this.params =map;
    }
}
