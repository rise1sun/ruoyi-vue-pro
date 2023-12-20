package cn.iocoder.yudao.module.wms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jiangfeng
 * @date 2023/12/20
 */

@Getter
@AllArgsConstructor
public enum BarcodeTypeEnum {

    SCAN_BARCODE(0, "扫码"),
    INBOUND(1, "入库"),
    OUTBOUND(2, "出库"),
    IN_TRANSIT(3, "途中"),
    ;


    private final Integer status;
    private final String desc;
}

