package cn.iocoder.yudao.module.wms.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * @author jiangfeng
 * @date 2023/12/4
 */
public interface ErrorCodeConstants {
    ErrorCode ACCESS_DENIED = new ErrorCode(401, "登录失效，请重新登录");
    ErrorCode BARCODE_NOT_EXISTS = new ErrorCode(1001001000, "条码不存在");
    ErrorCode TRAY_NOT_EXISTS = new ErrorCode(1001002000, "托盘不存在");
    ErrorCode SECURE_INVOKE_RECORD_NOT_EXISTS = new ErrorCode(1001003000, "安全调用记录不存在");
    ErrorCode PROCESS_FLOW_NOT_EXISTS = new ErrorCode(1001004000, "工艺流程表不存在");
    ErrorCode PROCESS_FLOW_ITEM_NOT_EXISTS = new ErrorCode(1001005000, "工艺流程表不存在");
    ErrorCode REGION_NOT_EXISTS = new ErrorCode(1001006000, "区域不存在");
    ErrorCode REGION_STORAGE_NOT_EXISTS = new ErrorCode(1001007000, "库位不存在");
    ErrorCode BARCODE_MOBILE_RECORD_NOT_EXISTS = new ErrorCode(1001008000, "移动记录不存在");
    ErrorCode STORAGE_TRAY_NOT_EXISTS = new ErrorCode(1001009000, "库位托盘绑定记录不存在");
    ErrorCode EMPTY_TRAY_WAREHOUSING_ERROR = new ErrorCode(1001009001, "空托盘入库失败");
    ErrorCode TRAY_ABSENT_ERROR = new ErrorCode(1001009002, "托盘不存在({})");
    ErrorCode TRAY_STATUS_ERROR = new ErrorCode(1001009003, "托盘状态不为空闲,不允许使用空托盘入库功能");
    ErrorCode STORAGE_ABSENT_ERROR = new ErrorCode(1001009004, "库位不存在({})");
    ErrorCode STORAGE_STATUS_ERROR = new ErrorCode(1001009005, "库位状态不为空闲,不允许入库");



}
