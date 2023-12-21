package cn.iocoder.yudao.module.wms.common.utils;

import cn.iocoder.yudao.module.wms.common.function.BFunction;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcode.BarcodeDO;

/**
 * @author jiangfeng
 * @date 2023/12/21
 */
public class BarcodeDOUtils {
    public static final BFunction<BarcodeDO, String> TRAY = BarcodeDO::getTray;
}
