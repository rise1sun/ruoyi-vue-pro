package cn.iocoder.yudao.module.wms.service.tray;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.wms.controller.admin.tray.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.tray.TrayDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 托盘 Service 接口
 *
 * @author 超级管理员
 */
public interface TrayService {

    /**
     * 创建托盘
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTray(@Valid TraySaveReqVO createReqVO);

    /**
     * 更新托盘
     *
     * @param updateReqVO 更新信息
     */
    void updateTray(@Valid TraySaveReqVO updateReqVO);

    /**
     * 删除托盘
     *
     * @param id 编号
     */
    void deleteTray(Long id);

    /**
     * 获得托盘
     *
     * @param id 编号
     * @return 托盘
     */
    TrayDO getTray(Long id);

    /**
     * 获得托盘分页
     *
     * @param pageReqVO 分页查询
     * @return 托盘分页
     */
    PageResult<TrayDO> getTrayPage(TrayPageReqVO pageReqVO);

}