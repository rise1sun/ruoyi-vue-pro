package cn.iocoder.yudao.module.wms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jiangfeng
 * @date 2023/12/20
 */
@Getter
@AllArgsConstructor
public enum BarcodeSourceEnum {

        WMS_PC(1, "WMS_PC"),
        WMS_PDA(2, "WMS_PDA"),
        MES(3, "MES"),
        ;

        private Integer type;
        private String desc;

}
