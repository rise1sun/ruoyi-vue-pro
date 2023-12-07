package cn.iocoder.yudao.module.wms.dal.mysql.tray;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.wms.dal.dataobject.tray.TrayDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.wms.controller.admin.tray.vo.*;

/**
 * 托盘 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface TrayMapper extends BaseMapperX<TrayDO> {

    default PageResult<TrayDO> selectPage(TrayPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TrayDO>()
                .eqIfPresent(TrayDO::getTrayNo, reqVO.getTrayNo())
                .eqIfPresent(TrayDO::getType, reqVO.getType())
                .eqIfPresent(TrayDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(TrayDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TrayDO::getId));
    }

}