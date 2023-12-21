package cn.iocoder.yudao.module.wms.service.barcode;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.wms.common.constant.MapKey;
import cn.iocoder.yudao.module.wms.common.function.BFunction;
import cn.iocoder.yudao.module.wms.common.function.SFunction;
import cn.iocoder.yudao.module.wms.common.handler.RequestHandler;
import cn.iocoder.yudao.module.wms.controller.admin.barcode.vo.BarcodePageReqVO;
import cn.iocoder.yudao.module.wms.controller.admin.barcode.vo.BarcodeSaveReqVO;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcode.BarcodeDO;
import cn.iocoder.yudao.module.wms.dal.mysql.barcode.BarcodeMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.BARCODE_MAP_KEY_TO_SFUNCTION_ERROR;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.BARCODE_NOT_EXISTS;

/**
 * 条码 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class BarcodeServiceImpl implements BarcodeService {

    @Resource
    private BarcodeMapper barcodeMapper;

    @Override
    public Long createBarcode(BarcodeSaveReqVO createReqVO) {
        // 插入
        BarcodeDO barcode = BeanUtils.toBean(createReqVO, BarcodeDO.class);
        if(ObjectUtil.isNotNull(RequestHandler.get())){
            barcode.setCreator(String.valueOf(RequestHandler.get()));
            barcode.setUpdater(String.valueOf(RequestHandler.get()));
            RequestHandler.remove();
        }
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

    @Override
    public Integer deleteBarcodeByBarcode(String barcode) {
        return barcodeMapper.delete(new LambdaUpdateWrapper<BarcodeDO>().eq(BarcodeDO::getBarcode, barcode));
    }

    @Override
    public void batchUpdateBarcodes(List<String> barcodes,Map<String,Object> params) {

        Map<BFunction<BarcodeDO, ?>, Object> convertedParams = new HashMap<>();

        // 遍历原始 Map，将键和值添加到新的 Map 中
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            BFunction<BarcodeDO, ?> key = mapKeyToSFunction(entry.getKey());
            convertedParams.put((BFunction<BarcodeDO, ?>) key, entry.getValue());
        }
        barcodeMapper.batchUpdateBarcode(barcodes,convertedParams);
    }

    private BFunction<BarcodeDO, ?> mapKeyToSFunction(String key) {
        if (key.equals(MapKey.TRAY)) {
            return BarcodeDO::getTray;
        }
        log.error("[mapKeyToSFunction][key({}) 不存在]", key);
        throw exception(BARCODE_MAP_KEY_TO_SFUNCTION_ERROR);
    }

}