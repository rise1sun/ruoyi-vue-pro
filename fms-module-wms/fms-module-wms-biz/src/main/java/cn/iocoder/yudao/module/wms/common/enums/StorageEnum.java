package cn.iocoder.yudao.module.wms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jiangfeng
 * @date 2023/12/13
 */
@AllArgsConstructor
@Getter
public enum StorageEnum {
    FREE(0, "空闲"),
    PREOCCUPY(1, "预占用"),
    OCCUPY(2, "占用"),;

    private final Integer status;
    private final String desc;
}
