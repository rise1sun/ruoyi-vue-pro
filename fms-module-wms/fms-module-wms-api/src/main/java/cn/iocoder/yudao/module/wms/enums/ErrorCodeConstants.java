package cn.iocoder.yudao.module.wms.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * @author jiangfeng
 * @date 2023/12/4
 */
public interface ErrorCodeConstants {
    ErrorCode BARCODE_NOT_EXISTS = new ErrorCode(1001001000, "条码不存在");
    ErrorCode TRAY_NOT_EXISTS = new ErrorCode(1001002000, "托盘不存在");
    ErrorCode SECURE_INVOKE_RECORD_NOT_EXISTS = new ErrorCode(1001003000, "托盘不存在");
}
