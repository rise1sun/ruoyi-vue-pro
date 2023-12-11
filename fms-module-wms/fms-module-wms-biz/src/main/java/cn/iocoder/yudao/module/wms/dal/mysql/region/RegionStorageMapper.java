package cn.iocoder.yudao.module.wms.dal.mysql.region;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionStorageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库位 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface RegionStorageMapper extends BaseMapperX<RegionStorageDO> {

    default PageResult<RegionStorageDO> selectPage(PageParam reqVO, Long regionId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RegionStorageDO>()
            .eq(RegionStorageDO::getRegionId, regionId)
            .orderByDesc(RegionStorageDO::getId));
    }

    default int deleteByRegionId(Long regionId) {
        return delete(RegionStorageDO::getRegionId, regionId);
    }

}