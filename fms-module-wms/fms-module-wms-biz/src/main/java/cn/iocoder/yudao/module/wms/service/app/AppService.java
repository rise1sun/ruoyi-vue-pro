package cn.iocoder.yudao.module.wms.service.app;

import cn.iocoder.yudao.module.wms.controller.app.vo.CreateBarcodeReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.EmptyTrayWarehousingReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.ManualBlankingReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.groupTray.*;

/**
 * @author jiangfeng
 * @date 2023/12/13
 */
public interface AppService {
    /**
     * 空托盘入库
     *
     * @param emptyTrayWarehousingReqVO
     */
    void emptyTrayWarehousing(EmptyTrayWarehousingReqVO emptyTrayWarehousingReqVO);

    /**
     * 手动下料
     *
     * @param manualBlankingReqVO
     */
    void manualBlanking(ManualBlankingReqVO manualBlankingReqVO);

    /**
     * 组盘数据前置校验
     *
     * @param checkGroupTrayDataReqVO
     * @return
     */
    CheckGroupTrayDataRepVO checkGroupTrayData(CheckGroupTrayDataReqVO checkGroupTrayDataReqVO);

    /**
     * 校验库位数据
     *
     * @param checkStorageDataReqVO
     * @return
     */
    CheckStorageDataRespVO checkStorageData(CheckStorageDataReqVO checkStorageDataReqVO);

    /**
     * 校验托盘数据
     *
     * @param checkTrayDataReqVO
     * @return
     */
    CheckTrayDataRespVO checkTrayData(CheckTrayDataReqVO checkTrayDataReqVO);

    Long createBarcode(CreateBarcodeReqVO createBarcodeReqVO);
}
