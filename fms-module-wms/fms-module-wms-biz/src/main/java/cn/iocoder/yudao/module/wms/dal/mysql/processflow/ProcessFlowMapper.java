package cn.iocoder.yudao.module.wms.dal.mysql.processflow;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.wms.dal.dataobject.processflow.ProcessFlowDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.wms.controller.admin.processflow.vo.*;

/**
 * 工艺流程表 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface ProcessFlowMapper extends BaseMapperX<ProcessFlowDO> {

    default PageResult<ProcessFlowDO> selectPage(ProcessFlowPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProcessFlowDO>()
                .likeIfPresent(ProcessFlowDO::getName, reqVO.getName())
                .eqIfPresent(ProcessFlowDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ProcessFlowDO::getType, reqVO.getType())
                .eqIfPresent(ProcessFlowDO::getDescription, reqVO.getDescription())
                .betweenIfPresent(ProcessFlowDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProcessFlowDO::getId));
    }

}