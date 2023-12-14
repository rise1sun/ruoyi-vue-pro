package cn.iocoder.yudao.module.wms.service.adapter;

import cn.iocoder.yudao.module.wms.common.enums.BarcodeMoveTypeEnum;
import cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo.BarcodeMobileRecordSaveReqVO;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionStorageDO;
import cn.iocoder.yudao.module.wms.dal.dataobject.tray.TrayDO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangfeng
 * @date 2023/12/14
 */
@Slf4j
public class BarcodeMobileRecodeAdapter {
    public static BarcodeMobileRecordSaveReqVO buildBarcodeMobileRecordDO(TrayDO trayDO, RegionStorageDO regionStorageDO) {
        return new BarcodeMobileRecordSaveReqVO()
                .setTrayNo(trayDO.getTrayNo())
                .setRegion(regionStorageDO.getRegionId().toString())
                .setStorage(regionStorageDO.getCode())
                .setMoveType(String.valueOf(BarcodeMoveTypeEnum.EMPTY_TRAY_INBOUND.getStatus()))
                .setRemark(BarcodeMoveTypeEnum.EMPTY_TRAY_INBOUND.getDesc());
    }
}
