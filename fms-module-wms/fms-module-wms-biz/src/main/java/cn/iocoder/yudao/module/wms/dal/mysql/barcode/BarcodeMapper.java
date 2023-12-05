package cn.iocoder.yudao.module.wms.dal.mysql.barcode;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcode.BarcodeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.wms.controller.admin.barcode.vo.*;

/**
 * 条码 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BarcodeMapper extends BaseMapperX<BarcodeDO> {

    default PageResult<BarcodeDO> selectPage(BarcodePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BarcodeDO>()
                .eqIfPresent(BarcodeDO::getBarcode, reqVO.getBarcode())
                .eqIfPresent(BarcodeDO::getDataSource, reqVO.getDataSource())
                .eqIfPresent(BarcodeDO::getSpec, reqVO.getSpec())
                .eqIfPresent(BarcodeDO::getType, reqVO.getType())
                .eqIfPresent(BarcodeDO::getArea, reqVO.getArea())
                .eqIfPresent(BarcodeDO::getTray, reqVO.getTray())
                .eqIfPresent(BarcodeDO::getBarcodeStatus, reqVO.getBarcodeStatus())
                .eqIfPresent(BarcodeDO::getFormulaId, reqVO.getFormulaId())
                .likeIfPresent(BarcodeDO::getFormulaName, reqVO.getFormulaName())
                .eqIfPresent(BarcodeDO::getFormulaItemId, reqVO.getFormulaItemId())
                .likeIfPresent(BarcodeDO::getFormulaItemName, reqVO.getFormulaItemName())
                .eqIfPresent(BarcodeDO::getNgSite, reqVO.getNgSite())
                .eqIfPresent(BarcodeDO::getRetestMarkers, reqVO.getRetestMarkers())
                .eqIfPresent(BarcodeDO::getChannelIndex, reqVO.getChannelIndex())
                .eqIfPresent(BarcodeDO::getBatchId, reqVO.getBatchId())
                .eqIfPresent(BarcodeDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(BarcodeDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(BarcodeDO::getStorage, reqVO.getStorage())
                .orderByDesc(BarcodeDO::getId));
    }

}