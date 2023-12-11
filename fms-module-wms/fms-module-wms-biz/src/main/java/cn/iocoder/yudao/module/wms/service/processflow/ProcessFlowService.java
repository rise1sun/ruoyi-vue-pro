package cn.iocoder.yudao.module.wms.service.processflow;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.wms.controller.admin.processflow.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.processflow.ProcessFlowDO;
import cn.iocoder.yudao.module.wms.dal.dataobject.processflow.ProcessFlowItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 工艺流程表 Service 接口
 *
 * @author 超级管理员
 */
public interface ProcessFlowService {

    /**
     * 创建工艺流程表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProcessFlow(@Valid ProcessFlowSaveReqVO createReqVO);

    /**
     * 更新工艺流程表
     *
     * @param updateReqVO 更新信息
     */
    void updateProcessFlow(@Valid ProcessFlowSaveReqVO updateReqVO);

    /**
     * 删除工艺流程表
     *
     * @param id 编号
     */
    void deleteProcessFlow(Long id);

    /**
     * 获得工艺流程表
     *
     * @param id 编号
     * @return 工艺流程表
     */
    ProcessFlowDO getProcessFlow(Long id);

    /**
     * 获得工艺流程表分页
     *
     * @param pageReqVO 分页查询
     * @return 工艺流程表分页
     */
    PageResult<ProcessFlowDO> getProcessFlowPage(ProcessFlowPageReqVO pageReqVO);

    // ==================== 子表（工艺流程表） ====================

    /**
     * 获得工艺流程表分页
     *
     * @param pageReqVO 分页查询
     * @param processFlowId 主表id
     * @return 工艺流程表分页
     */
    PageResult<ProcessFlowItemDO> getProcessFlowItemPage(PageParam pageReqVO, Long processFlowId);

    /**
     * 创建工艺流程表
     *
     * @param processFlowItem 创建信息
     * @return 编号
     */
    Long createProcessFlowItem(@Valid ProcessFlowItemDO processFlowItem);

    /**
     * 更新工艺流程表
     *
     * @param processFlowItem 更新信息
     */
    void updateProcessFlowItem(@Valid ProcessFlowItemDO processFlowItem);

    /**
     * 删除工艺流程表
     *
     * @param id 编号
     */
    void deleteProcessFlowItem(Long id);

	/**
	 * 获得工艺流程表
	 *
	 * @param id 编号
     * @return 工艺流程表
	 */
    ProcessFlowItemDO getProcessFlowItem(Long id);

}