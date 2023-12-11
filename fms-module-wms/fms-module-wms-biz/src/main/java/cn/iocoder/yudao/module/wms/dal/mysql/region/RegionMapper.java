package cn.iocoder.yudao.module.wms.dal.mysql.region;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.wms.controller.admin.region.vo.*;

/**
 * 库位管理 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface RegionMapper extends BaseMapperX<RegionDO> {

    default PageResult<RegionDO> selectPage(RegionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RegionDO>()
                .betweenIfPresent(RegionDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(RegionDO::getName, reqVO.getName())
                .eqIfPresent(RegionDO::getPrefix, reqVO.getPrefix())
                .orderByDesc(RegionDO::getId));
    }

}