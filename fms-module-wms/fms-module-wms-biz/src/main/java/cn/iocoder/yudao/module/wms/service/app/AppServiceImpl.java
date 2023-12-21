package cn.iocoder.yudao.module.wms.service.app;

import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.wms.common.constant.MapKey;
import cn.iocoder.yudao.module.wms.common.enums.StorageEnum;
import cn.iocoder.yudao.module.wms.common.enums.TrayEnum;
import cn.iocoder.yudao.module.wms.common.event.InboundEvent;
import cn.iocoder.yudao.module.wms.common.handler.RequestHandler;
import cn.iocoder.yudao.module.wms.common.utils.AssertUtil;
import cn.iocoder.yudao.module.wms.controller.admin.storagetray.vo.StorageTraySaveReqVO;
import cn.iocoder.yudao.module.wms.controller.admin.tray.vo.TraySaveReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.*;
import cn.iocoder.yudao.module.wms.controller.app.vo.groupTray.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcode.BarcodeDO;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionStorageDO;
import cn.iocoder.yudao.module.wms.dal.dataobject.tray.TrayDO;
import cn.iocoder.yudao.module.wms.service.adapter.*;
import cn.iocoder.yudao.module.wms.service.barcode.BarcodeService;
import cn.iocoder.yudao.module.wms.service.barcodemobilerecord.BarcodeMobileRecordService;
import cn.iocoder.yudao.module.wms.service.region.RegionService;
import cn.iocoder.yudao.module.wms.service.storagetray.StorageTrayService;
import cn.iocoder.yudao.module.wms.service.tray.TrayService;
import cn.iocoder.yudao.module.wms.transaction.service.MQProducer;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
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

    @Resource
    private MQProducer mqProducer;

    @Resource
    private BarcodeService barcodeService ;

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void emptyTrayWarehousing(EmptyTrayWarehousingReqVO emptyTrayWarehousingReqVO) {
        //这里本来要查托盘库位信息,参数校验就不单独做了
        TrayDO tray = getTrayAndCheckStatus(emptyTrayWarehousingReqVO.getTrayNo());
        String storage = emptyTrayWarehousingReqVO.getStorage();
        RegionStorageDO regionStorageDO = getRegionStorageAndCheckStatus(storage);
        //执行入库逻辑
        try {
            //更新库位信息
            RegionStorageDO regionStorageUpdate = RegionStorageAdapter
                    .buildRegionStorageDO(storage, StorageEnum.OCCUPY.getStatus());
            regionService.updateRegionStorageStatusByCode(regionStorageUpdate);
            //托盘 库位关系绑定
            StorageTraySaveReqVO storageTrayUpdate = StorageTrayAdapter
                    .buildStorageTrayDO(regionStorageDO.getId(), tray.getId());
            storageTrayService.createStorageTray(storageTrayUpdate);
            //新增托盘移动记录
            barcodeMobileRecordService.createBarcodeMobileRecord(BarcodeMobileRecodeAdapter
                    .buildEmptyTrayWarehousingBarcodeMobileRecordDO(tray, regionStorageDO));
            //发送消息
            mqProducer.sendSecureMsg("WCS-TASK", "调度消息",
                    tray.getTrayNo()+"-"+storage+ DateUtil.nanosToMillis(System.nanoTime()));
        } catch (Exception e) {
            log.error("doEmptyTrayWarehousing 数据库更新失败", e);
            throw exception(EMPTY_TRAY_WAREHOUSING_ERROR);
        }
    }

    @Override
    public void manualBlanking(ManualBlankingReqVO manualBlankingReqVO) {
        //校验托盘库位信息
        TrayDO tray = getTray(manualBlankingReqVO.getTrayNo());
        String storage = manualBlankingReqVO.getStorage();
        RegionStorageDO regionStorageDO = getRegionStorage(storage);
        Boolean isExist = storageTrayService.isExistStorageTrayByTrayIdAndStorageId(tray.getId(), regionStorageDO.getId());
        AssertUtil.isTrue(isExist, TRAY_STORAGE_NOT_MATCH_ERROR);
        //执行下料逻辑
        try {
            //更新库位信息
            RegionStorageDO regionStorageUpdate = RegionStorageAdapter
                    .buildRegionStorageDO(storage, StorageEnum.FREE.getStatus());
            regionService.updateRegionStorageStatusByCode(regionStorageUpdate);
            //托盘 库位关系解绑
            storageTrayService.deleteStorageTrayByTrayIdAndStorageId(tray.getId(),regionStorageDO.getId());
            //新增托盘移动记录
            barcodeMobileRecordService.createBarcodeMobileRecord(BarcodeMobileRecodeAdapter
                    .buildManualBlankingBarcodeMobileRecordDO(tray, regionStorageDO));

            //todo 工艺流程状态流转
            //发送消息
            mqProducer.sendSecureMsg("WCS-TASK", "调度消息",
                    tray.getTrayNo()+"-"+storage+ DateUtil.nanosToMillis(System.nanoTime()));
        } catch (Exception e) {
            log.error("doManualBlanking 数据库更新失败", e);
            throw exception(MANUAL_BLANKING_ERROR);
        }
    }

    @Override
    public CheckGroupTrayDataRepVO checkGroupTrayData(CheckGroupTrayDataReqVO checkGroupTrayDataReqVO) {
        //校验托盘
        TrayDO tray = getTrayAndCheckStatus(checkGroupTrayDataReqVO.getTrayNo());
        //校验当前库位
        RegionStorageDO regionStorageDO = getRegionStorageAndCheckStatus(checkGroupTrayDataReqVO.getStorage());
        //校验目标库位
        RegionStorageDO targetRegionStorageDO = getRegionStorageAndCheckStatus(checkGroupTrayDataReqVO.getTargetStorage());

        //buildCheckGroupTrayDataRepVO(tray, regionStorageDO, targetRegionStorageDO);
        return null;
    }

    @Override
    public CheckStorageDataRespVO checkStorageData(CheckStorageDataReqVO checkStorageDataReqVO) {
        RegionStorageDO regionStorageDO = getRegionStorageAndCheckStatus(checkStorageDataReqVO.getStorage());
        return CheckStorageDataAdapter.buildCheckStorageDataRepVO(checkStorageDataReqVO.getStorage());
    }

    @Override
    public CheckTrayDataRespVO checkTrayData(CheckTrayDataReqVO checkTrayDataReqVO) {
        TrayDO trayAndCheckStatus = getTrayAndCheckStatus(checkTrayDataReqVO.getTrayNo());
        return CheckTrayDataAdapter.buildCheckTrayDataRespVO(checkTrayDataReqVO.getTrayNo());
    }

    @Override
    public Long createBarcode(CreateBarcodeReqVO createBarcodeReqVO) {
        if(SecurityFrameworkUtils.getLoginUser() == null){
             throw exception(UNAUTHORIZED);
        }
        Long uid = SecurityFrameworkUtils.getLoginUser().getId();
        RequestHandler.set(uid);
        Long barcodeId = barcodeService.createBarcode(CreateBarcodeAdapter.buildBarcodeSaveReqVO(createBarcodeReqVO));
        return barcodeId;
    }

    @Override
    public Integer deleteBarcode(DeleteBarcodeReqVO deleteBarcodeReqVO) {
        return barcodeService.deleteBarcodeByBarcode(deleteBarcodeReqVO.getBarcode());
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void groupTray(GroupTrayReqVO groupTrayReqVO) {
        //托盘状态更新
        trayService.updateTrayStatusByTrayNo(groupTrayReqVO.getTray(), TrayEnum.BIND.getStatus());
        List<String> barcodeLists = groupTrayReqVO.getBarcodes().stream().map(BarcodeVO::getBatteriesBarcode)
                .collect(Collectors.toList());
        HashMap<String, Object> map = new HashMap<>();
        map.put(MapKey.TRAY,groupTrayReqVO.getTray());
        barcodeService.batchUpdateBarcodes(barcodeLists,map);

        applicationEventPublisher.publishEvent(new InboundEvent(InboundEvent.class,barcodeLists,map));
        //事件触发入库

        //启动工艺流程
        //1 初始化条码工艺流程
        //2 缓存托盘的工艺流程（如果条码工艺变了 在产线内的托盘 还是走缓存的工艺 正常结束才会清除 如果是NG了不会清这个缓存 如果重测 默认会）
        //MQ发送消息

    }

    private TrayDO getTray(String trayNo) {
        TrayDO tray = trayService.selectByTrayNo(trayNo);
        AssertUtil.notNull(tray, TRAY_ABSENT_ERROR, trayNo);
        return tray;
    }

    @NotNull
    private RegionStorageDO getRegionStorageAndCheckStatus(String storage) {
        RegionStorageDO regionStorageDO = getRegionStorage(storage);
        AssertUtil.notNull(regionStorageDO, STORAGE_ABSENT_ERROR, storage);
        AssertUtil.equal(StorageEnum.FREE.getStatus(), regionStorageDO.getStatus(), STORAGE_STATUS_ERROR);
        return regionStorageDO;
    }

    private RegionStorageDO getRegionStorage(String storage) {
        RegionStorageDO regionStorageDO = regionService.slectByStorage(storage);
        AssertUtil.notNull(regionStorageDO, STORAGE_ABSENT_ERROR, storage);
        return regionStorageDO;
    }

    @NotNull
    private TrayDO getTrayAndCheckStatus(String trayNo) {
        TrayDO tray = getTray(trayNo);
        AssertUtil.equal(TrayEnum.FREE.getStatus(), tray.getStatus(), TRAY_STATUS_ERROR);
        return tray;
    }

}
