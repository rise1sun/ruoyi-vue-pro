package cn.iocoder.yudao.module.wms.service.barcode;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.wms.controller.admin.barcode.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcode.BarcodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.wms.dal.mysql.barcode.BarcodeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.BARCODE_NOT_EXISTS;

/**
 * 条码 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BarcodeServiceImpl implements BarcodeService {

    @Resource
    private BarcodeMapper barcodeMapper;

    @Override
    public Long createBarcode(BarcodeSaveReqVO createReqVO) {
        // 插入
        BarcodeDO barcode = BeanUtils.toBean(createReqVO, BarcodeDO.class);
        barcodeMapper.insert(barcode);
        // 返回
        return barcode.getId();
    }

    @Override
    public void updateBarcode(BarcodeSaveReqVO updateReqVO) {
        // 校验存在
        validateBarcodeExists(updateReqVO.getId());
        // 更新
        BarcodeDO updateObj = BeanUtils.toBean(updateReqVO, BarcodeDO.class);
        barcodeMapper.updateById(updateObj);
    }

    @Override
    public void deleteBarcode(Long id) {
        // 校验存在
        validateBarcodeExists(id);
        // 删除
        barcodeMapper.deleteById(id);
    }

    private void validateBarcodeExists(Long id) {
        if (barcodeMapper.selectById(id) == null) {
            throw exception(BARCODE_NOT_EXISTS);
        }
    }

    @Override
    public BarcodeDO getBarcode(Long id) {
        return barcodeMapper.selectById(id);
    }

    @Override
    public PageResult<BarcodeDO> getBarcodePage(BarcodePageReqVO pageReqVO) {
        return barcodeMapper.selectPage(pageReqVO);
    }

}