package cn.iocoder.yudao.module.wms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jiangfeng
 * @date 2023/12/14
 */
@Getter
@AllArgsConstructor
public enum BarcodeMoveTypeEnum {

    INBOUND(1, "入库"),
    OUTBOUND(2, "出库"),
    INTER_WAREHOUSE_TRANSFER(3, "库间移库"),
    GROUP_TRAY(4, "组盘"),
    REMOVE_TRAY(5, "移库"),
    EMPTY_TRAY_INBOUND(6, "空托盘入库"),
    BAITING(7,"下料");

    private final Integer status;
    private final String desc;
}
