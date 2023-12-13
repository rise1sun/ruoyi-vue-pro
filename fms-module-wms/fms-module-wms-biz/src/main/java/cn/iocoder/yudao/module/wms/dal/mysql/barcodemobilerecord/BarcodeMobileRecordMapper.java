package cn.iocoder.yudao.module.wms.dal.mysql.barcodemobilerecord;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcodemobilerecord.BarcodeMobileRecordDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo.*;

/**
 * 条码移动记录 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface BarcodeMobileRecordMapper extends BaseMapperX<BarcodeMobileRecordDO> {

    default PageResult<BarcodeMobileRecordDO> selectPage(BarcodeMobileRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BarcodeMobileRecordDO>()
                .eqIfPresent(BarcodeMobileRecordDO::getTrayNo, reqVO.getTrayNo())
                .eqIfPresent(BarcodeMobileRecordDO::getSuNo, reqVO.getSuNo())
                .eqIfPresent(BarcodeMobileRecordDO::getBarcode, reqVO.getBarcode())
                .eqIfPresent(BarcodeMobileRecordDO::getNumber, reqVO.getNumber())
                .eqIfPresent(BarcodeMobileRecordDO::getSpec, reqVO.getSpec())
                .eqIfPresent(BarcodeMobileRecordDO::getBatch, reqVO.getBatch())
                .eqIfPresent(BarcodeMobileRecordDO::getRegion, reqVO.getRegion())
                .eqIfPresent(BarcodeMobileRecordDO::getStorage, reqVO.getStorage())
                .eqIfPresent(BarcodeMobileRecordDO::getBarcodeStatus, reqVO.getBarcodeStatus())
                .eqIfPresent(BarcodeMobileRecordDO::getMoveType, reqVO.getMoveType())
                .eqIfPresent(BarcodeMobileRecordDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(BarcodeMobileRecordDO::getRestTime, reqVO.getRestTime())
                .betweenIfPresent(BarcodeMobileRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BarcodeMobileRecordDO::getId));
    }

}