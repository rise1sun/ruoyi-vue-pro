package cn.iocoder.yudao.module.wms.service.storagetray;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.wms.controller.admin.storagetray.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.storagetray.StorageTrayDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 库位托盘 Service 接口
 *
 * @author 超级管理员
 */
public interface StorageTrayService {

    /**
     * 创建库位托盘
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStorageTray(@Valid StorageTraySaveReqVO createReqVO);

    /**
     * 更新库位托盘
     *
     * @param updateReqVO 更新信息
     */
    void updateStorageTray(@Valid StorageTraySaveReqVO updateReqVO);

    /**
     * 删除库位托盘
     *
     * @param id 编号
     */
    void deleteStorageTray(Long id);

    /**
     * 获得库位托盘
     *
     * @param id 编号
     * @return 库位托盘
     */
    StorageTrayDO getStorageTray(Long id);

    /**
     * 获得库位托盘分页
     *
     * @param pageReqVO 分页查询
     * @return 库位托盘分页
     */
    PageResult<StorageTrayDO> getStorageTrayPage(StorageTrayPageReqVO pageReqVO);

}