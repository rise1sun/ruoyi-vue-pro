package cn.iocoder.yudao.module.wms.service.adapter;

import cn.iocoder.yudao.module.wms.common.enums.BarcodeTypeEnum;
import cn.iocoder.yudao.module.wms.controller.admin.barcode.vo.BarcodeSaveReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.CreateBarcodeReqVO;

/**
 * @author jiangfeng
 * @date 2023/12/20
 */
public class CreateBarcodeAdapter {
    public static BarcodeSaveReqVO buildBarcodeSaveReqVO(CreateBarcodeReqVO createBarcodeReqVO) {
        return new BarcodeSaveReqVO()
                .setBarcode(createBarcodeReqVO.getBarcode())
                .setChannelIndex(createBarcodeReqVO.getChannel())
                .setBarcodeStatus(BarcodeTypeEnum.SCAN_BARCODE.getStatus());
    }
}
