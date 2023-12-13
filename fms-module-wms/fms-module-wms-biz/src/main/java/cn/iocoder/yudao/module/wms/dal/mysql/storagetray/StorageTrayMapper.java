package cn.iocoder.yudao.module.wms.dal.mysql.storagetray;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.wms.dal.dataobject.storagetray.StorageTrayDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.wms.controller.admin.storagetray.vo.*;

/**
 * 库位托盘 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface StorageTrayMapper extends BaseMapperX<StorageTrayDO> {

    default PageResult<StorageTrayDO> selectPage(StorageTrayPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StorageTrayDO>()
                .eqIfPresent(StorageTrayDO::getStorageId, reqVO.getStorageId())
                .eqIfPresent(StorageTrayDO::getTrayId, reqVO.getTrayId())
                .betweenIfPresent(StorageTrayDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StorageTrayDO::getId));
    }

}