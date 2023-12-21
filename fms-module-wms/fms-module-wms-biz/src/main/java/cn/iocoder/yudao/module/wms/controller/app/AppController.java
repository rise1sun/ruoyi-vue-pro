package cn.iocoder.yudao.module.wms.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.wms.common.annotation.RedissonLock;
import cn.iocoder.yudao.module.wms.controller.app.vo.CreateBarcodeReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.DeleteBarcodeReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.EmptyTrayWarehousingReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.ManualBlankingReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.groupTray.*;
import cn.iocoder.yudao.module.wms.service.app.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * @author jiangfeng
 * @date 2023/12/13
 */
@Tag(name = "管理后台 - app")
@RestController
@RequestMapping("/wms/app")
@Validated
public class AppController {

    @Resource
    private AppService appService;

    @PostMapping("/emptyTrayWarehousing")
    @Operation(summary = "空托盘入库")
    @PreAuthorize("@ss.hasPermission('wms:app:emptyTrayWarehousing')")
    @RedissonLock(prefixKey = "emptyTrayWarehousing", key = "#emptyTrayWarehousingReqVO.trayNo")
    public CommonResult<Boolean> emptyTrayWarehousing(@Valid @RequestBody EmptyTrayWarehousingReqVO emptyTrayWarehousingReqVO) {
        appService.emptyTrayWarehousing(emptyTrayWarehousingReqVO);
        return success(true);
    }

    @PostMapping("/manualBlanking")
    @Operation(summary = "手工下料")
    @PreAuthorize("@ss.hasPermission('wms:app:manualBlanking')")
    @RedissonLock(prefixKey = "manualBlanking", key = "#manualBlankingReqVO.trayNo")
    public CommonResult<Boolean> manualBlanking(@Valid @RequestBody ManualBlankingReqVO manualBlankingReqVO) {
        appService.manualBlanking(manualBlankingReqVO);
        return success(true);
    }

    @PostMapping("/groupTray")
    @Operation(summary = "组盘")
    @PreAuthorize("@ss.hasPermission('wms:app:groupTray')")
    @RedissonLock(prefixKey = "groupTray", key = "#groupTrayReqVO.tray")
    public CommonResult<Boolean> groupTray(@Valid @RequestBody GroupTrayReqVO groupTrayReqVO) {
        appService.groupTray(groupTrayReqVO);
        return success(true);
    }

//    @PostMapping("/checkGroupTrayData")
//    @Operation(summary = "校验组盘数据")
//    public CommonResult<CheckGroupTrayDataRepVO> checkGroupTrayData(@Valid @RequestBody CheckGroupTrayDataReqVO checkGroupTrayDataReqVO) {
//        CheckGroupTrayDataRepVO checkGroupTrayDataRep =appService.checkGroupTrayData(checkGroupTrayDataReqVO);
//        return success(checkGroupTrayDataRep);
//    }

    @PostMapping("/checkStorageData")
    @Operation(summary = "校验库位数据")
    public CommonResult<CheckStorageDataRespVO> checkStorageData(@Valid @RequestBody CheckStorageDataReqVO checkStorageDataReqVO) {
        CheckStorageDataRespVO checkStorageData =appService.checkStorageData(checkStorageDataReqVO);
        return success(checkStorageData);
    }

    @PostMapping("/checkTrayData")
    @Operation(summary = "校验托盘数据")
    public CommonResult<CheckTrayDataRespVO> checkTrayData(@Valid @RequestBody CheckTrayDataReqVO checkTrayDataReqVO) {
        CheckTrayDataRespVO checkTrayDataRespVO =appService.checkTrayData(checkTrayDataReqVO);
        return success(checkTrayDataRespVO);
    }

    @PostMapping("/createBarcode")
    @Operation(summary = "创建条码")
    public CommonResult<Long> createBarcode(@Valid @RequestBody CreateBarcodeReqVO createBarcodeReqVO) {
        return success(appService.createBarcode(createBarcodeReqVO));
    }


    @PostMapping("/deleteBarcode")
    @Operation(summary = "删除条码")
    public CommonResult<Integer> deleteBarcode(@Valid @RequestBody DeleteBarcodeReqVO deleteBarcodeReqVO) {
        return success(appService.deleteBarcode(deleteBarcodeReqVO));
    }

}
