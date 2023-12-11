package cn.iocoder.yudao.module.wms.dal.mysql.processflow;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.wms.dal.dataobject.processflow.ProcessFlowItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工艺流程表 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface ProcessFlowItemMapper extends BaseMapperX<ProcessFlowItemDO> {

    default PageResult<ProcessFlowItemDO> selectPage(PageParam reqVO, Long processFlowId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProcessFlowItemDO>()
            .eq(ProcessFlowItemDO::getProcessFlowId, processFlowId)
            .orderByDesc(ProcessFlowItemDO::getId));
    }

    default int deleteByProcessFlowId(Long processFlowId) {
        return delete(ProcessFlowItemDO::getProcessFlowId, processFlowId);
    }

}