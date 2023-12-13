package cn.iocoder.yudao.module.wms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author jiangfeng
 * @date 2023/12/13
 */
@Getter
@AllArgsConstructor
public enum TrayEnum {
    free(1, "空闲"),
    bind(2, "绑定"),
    deprecated(3, "废弃"),;

    private final Integer status;
    private final String desc;
}
