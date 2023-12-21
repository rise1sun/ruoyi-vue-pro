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

    UN_INBOUND(10, "未入库"),
    INBOUND(20, "入库"),
    OUTBOUND(30, "出库"),
    INVALID(40, "无效"),
    ;


    private final Integer status;
    private final String desc;
}

