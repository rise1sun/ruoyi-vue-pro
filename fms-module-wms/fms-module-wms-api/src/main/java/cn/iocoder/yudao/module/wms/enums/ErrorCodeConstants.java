package cn.iocoder.yudao.module.wms.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * @author jiangfeng
 * @date 2023/12/4
 */
public interface ErrorCodeConstants {
    ErrorCode BARCODE_NOT_EXISTS = new ErrorCode(1001001000, "条码不存在");
    ErrorCode TRAY_NOT_EXISTS = new ErrorCode(1001002000, "托盘不存在");
    ErrorCode SECURE_INVOKE_RECORD_NOT_EXISTS = new ErrorCode(1001003000, "安全调用记录不存在");
    ErrorCode PROCESS_FLOW_NOT_EXISTS = new ErrorCode(1001004000, "工艺流程表不存在");
    ErrorCode PROCESS_FLOW_ITEM_NOT_EXISTS = new ErrorCode(1001005000, "工艺流程表不存在");
    ErrorCode REGION_NOT_EXISTS = new ErrorCode(1001006000, "库位管理不存在");
    ErrorCode REGION_STORAGE_NOT_EXISTS = new ErrorCode(1001007000, "库位管理不存在");

}
