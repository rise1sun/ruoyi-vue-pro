package cn.iocoder.yudao.module.wms.service.processflow;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.wms.controller.admin.processflow.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.processflow.ProcessFlowDO;
import cn.iocoder.yudao.module.wms.dal.dataobject.processflow.ProcessFlowItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.wms.dal.mysql.processflow.ProcessFlowMapper;
import cn.iocoder.yudao.module.wms.dal.mysql.processflow.ProcessFlowItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.*;

/**
 * 工艺流程表 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class ProcessFlowServiceImpl implements ProcessFlowService {

    @Resource
    private ProcessFlowMapper processFlowMapper;
    @Resource
    private ProcessFlowItemMapper processFlowItemMapper;

    @Override
    public Long createProcessFlow(ProcessFlowSaveReqVO createReqVO) {
        // 插入
        ProcessFlowDO processFlow = BeanUtils.toBean(createReqVO, ProcessFlowDO.class);
        processFlowMapper.insert(processFlow);
        // 返回
        return processFlow.getId();
    }

    @Override
    public void updateProcessFlow(ProcessFlowSaveReqVO updateReqVO) {
        // 校验存在
        validateProcessFlowExists(updateReqVO.getId());
        // 更新
        ProcessFlowDO updateObj = BeanUtils.toBean(updateReqVO, ProcessFlowDO.class);
        processFlowMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProcessFlow(Long id) {
        // 校验存在
        validateProcessFlowExists(id);
        // 删除
        processFlowMapper.deleteById(id);

        // 删除子表
        deleteProcessFlowItemByProcessFlowId(id);
    }

    private void validateProcessFlowExists(Long id) {
        if (processFlowMapper.selectById(id) == null) {
            throw exception(PROCESS_FLOW_NOT_EXISTS);
        }
    }

    @Override
    public ProcessFlowDO getProcessFlow(Long id) {
        return processFlowMapper.selectById(id);
    }

    @Override
    public PageResult<ProcessFlowDO> getProcessFlowPage(ProcessFlowPageReqVO pageReqVO) {
        return processFlowMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（工艺流程表） ====================

    @Override
    public PageResult<ProcessFlowItemDO> getProcessFlowItemPage(PageParam pageReqVO, Long processFlowId) {
        return processFlowItemMapper.selectPage(pageReqVO, processFlowId);
    }

    @Override
    public Long createProcessFlowItem(ProcessFlowItemDO processFlowItem) {
        processFlowItemMapper.insert(processFlowItem);
        return processFlowItem.getId();
    }

    @Override
    public void updateProcessFlowItem(ProcessFlowItemDO processFlowItem) {
        // 校验存在
        validateProcessFlowItemExists(processFlowItem.getId());
        // 更新
        processFlowItemMapper.updateById(processFlowItem);
    }

    @Override
    public void deleteProcessFlowItem(Long id) {
        // 校验存在
        validateProcessFlowItemExists(id);
        // 删除
        processFlowItemMapper.deleteById(id);
    }

    @Override
    public ProcessFlowItemDO getProcessFlowItem(Long id) {
        return processFlowItemMapper.selectById(id);
    }

    private void validateProcessFlowItemExists(Long id) {
        if (processFlowItemMapper.selectById(id) == null) {
            throw exception(PROCESS_FLOW_ITEM_NOT_EXISTS);
        }
    }

    private void deleteProcessFlowItemByProcessFlowId(Long processFlowId) {
        processFlowItemMapper.deleteByProcessFlowId(processFlowId);
    }

}