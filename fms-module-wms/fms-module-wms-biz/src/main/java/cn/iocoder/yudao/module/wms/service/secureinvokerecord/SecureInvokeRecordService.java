package cn.iocoder.yudao.module.wms.service.secureinvokerecord;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.wms.controller.admin.secureinvokerecord.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.secureinvokerecord.SecureInvokeRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 本地消息 Service 接口
 *
 * @author 超级管理员
 */
public interface SecureInvokeRecordService {

    /**
     * 创建本地消息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSecureInvokeRecord(@Valid SecureInvokeRecordSaveReqVO createReqVO);

    /**
     * 更新本地消息
     *
     * @param updateReqVO 更新信息
     */
    void updateSecureInvokeRecord(@Valid SecureInvokeRecordSaveReqVO updateReqVO);

    /**
     * 删除本地消息
     *
     * @param id 编号
     */
    void deleteSecureInvokeRecord(Long id);

    /**
     * 获得本地消息
     *
     * @param id 编号
     * @return 本地消息
     */
    SecureInvokeRecordDO getSecureInvokeRecord(Long id);

    /**
     * 获得本地消息分页
     *
     * @param pageReqVO 分页查询
     * @return 本地消息分页
     */
    PageResult<SecureInvokeRecordDO> getSecureInvokeRecordPage(SecureInvokeRecordPageReqVO pageReqVO);

}