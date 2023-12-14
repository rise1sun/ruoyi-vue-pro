package cn.iocoder.yudao.module.wms.service.app;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.wms.common.enums.StorageEnum;
import cn.iocoder.yudao.module.wms.common.enums.TrayEnum;
import cn.iocoder.yudao.module.wms.common.utils.AssertUtil;
import cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo.BarcodeMobileRecordSaveReqVO;
import cn.iocoder.yudao.module.wms.controller.admin.storagetray.vo.StorageTraySaveReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.EmptyTrayWarehousingReqVO;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionStorageDO;
import cn.iocoder.yudao.module.wms.dal.dataobject.tray.TrayDO;
import cn.iocoder.yudao.module.wms.service.barcodemobilerecord.BarcodeMobileRecordService;
import cn.iocoder.yudao.module.wms.service.region.RegionService;
import cn.iocoder.yudao.module.wms.service.storagetray.StorageTrayService;
import cn.iocoder.yudao.module.wms.service.tray.TrayService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.MAIL_SEND_TEMPLATE_PARAM_MISS;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.*;

/**
 * @author jiangfeng
 * @date 2023/12/13
 */
@Service
@Validated
@Slf4j
public class AppServiceImpl implements AppService {


    @Resource
    private TrayService trayService;

    @Resource
    private RegionService regionService;

    @Resource
    private StorageTrayService storageTrayService;

    @Resource
    private BarcodeMobileRecordService barcodeMobileRecordService;

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void emptyTrayWarehousing(EmptyTrayWarehousingReqVO emptyTrayWarehousingReqVO) {
        //这里本来要查托盘库位信息,参数校验就不单独做了
        TrayDO tray = getTrayAndCheck(emptyTrayWarehousingReqVO);
        String storage = emptyTrayWarehousingReqVO.getStorage();
        RegionStorageDO regionStorageDO = getRegionStorageAndCheck(storage);
        //执行入库逻辑
        try {
            //更新库位信息
            regionService.updateRegionStorageStatusByCode(new RegionStorageDO()
                    .setCode(storage)
                    .setStatus(StorageEnum.occupy.getStatus())
            );

            //托盘 库位关系绑定
            storageTrayService.createStorageTray(new StorageTraySaveReqVO()
                    .setTrayId(tray.getId())
                    .setStorageId(regionStorageDO.getId()));

            //新增托盘移动记录
            barcodeMobileRecordService.createBarcodeMobileRecord(new BarcodeMobileRecordSaveReqVO());
        } catch (Exception e) {
            log.error("doEmptyTrayWarehousing 数据库更新失败", e);

            throw exception(EMPTY_TRAY_WAREHOUSING_ERROR);
        }
    }

    @NotNull
    private RegionStorageDO getRegionStorageAndCheck(String storage) {
        RegionStorageDO regionStorageDO = regionService.slectByStorage(storage);
        AssertUtil.notNull(regionStorageDO,STORAGE_ABSENT_ERROR,storage);
//        if(ObjectUtil.isNull(regionStorageDO)){
//            throw exception(STORAGE_ABSENT_ERROR, storage);
//        }
//        if(!StorageEnum.free.getStatus().equals(regionStorageDO.getStatus())){
//            throw exception(STORAGE_STATUS_ERROR);
//        }
        AssertUtil.equal(StorageEnum.free.getStatus(),regionStorageDO.getStatus(),STORAGE_STATUS_ERROR);
        return regionStorageDO;
    }

    @NotNull
    private TrayDO getTrayAndCheck(EmptyTrayWarehousingReqVO emptyTrayWarehousingReqVO) {
        String trayNo = emptyTrayWarehousingReqVO.getTrayNo();
        TrayDO tray = trayService.selectByTrayNo(trayNo);
        AssertUtil.notNull(tray,TRAY_ABSENT_ERROR,trayNo);
//        if(ObjectUtil.isNull(tray)){
//            throw exception(TRAY_ABSENT_ERROR, trayNo);
//        }
        AssertUtil.equal(TrayEnum.free.getStatus(),tray.getStatus(),TRAY_STATUS_ERROR);
//        if (!TrayEnum.free.getStatus().equals(tray.getStatus())){
//            throw exception(TRAY_STATUS_ERROR);
//        }
        return tray;
    }

}
