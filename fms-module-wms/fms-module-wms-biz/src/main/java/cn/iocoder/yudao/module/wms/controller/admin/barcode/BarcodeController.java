package cn.iocoder.yudao.module.wms.controller.admin.barcode;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.wms.controller.admin.barcode.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcode.BarcodeDO;
import cn.iocoder.yudao.module.wms.service.barcode.BarcodeService;

@Tag(name = "管理后台 - 条码")
@RestController
@RequestMapping("/wms/barcode")
@Validated
public class BarcodeController {

    @Resource
    private BarcodeService barcodeService;

    @PostMapping("/create")
    @Operation(summary = "创建条码")
    @PreAuthorize("@ss.hasPermission('wms:barcode:create')")
    public CommonResult<Long> createBarcode(@Valid @RequestBody BarcodeSaveReqVO createReqVO) {
        return success(barcodeService.createBarcode(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新条码")
    @PreAuthorize("@ss.hasPermission('wms:barcode:update')")
    public CommonResult<Boolean> updateBarcode(@Valid @RequestBody BarcodeSaveReqVO updateReqVO) {
        barcodeService.updateBarcode(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除条码")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:barcode:delete')")
    public CommonResult<Boolean> deleteBarcode(@RequestParam("id") Long id) {
        barcodeService.deleteBarcode(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得条码")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:barcode:query')")
    public CommonResult<BarcodeRespVO> getBarcode(@RequestParam("id") Long id) {
        BarcodeDO barcode = barcodeService.getBarcode(id);
        return success(BeanUtils.toBean(barcode, BarcodeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得条码分页")
    @PreAuthorize("@ss.hasPermission('wms:barcode:query')")
    public CommonResult<PageResult<BarcodeRespVO>> getBarcodePage(@Valid BarcodePageReqVO pageReqVO) {
        PageResult<BarcodeDO> pageResult = barcodeService.getBarcodePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BarcodeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出条码 Excel")
    @PreAuthorize("@ss.hasPermission('wms:barcode:export')")
    @OperateLog(type = EXPORT)
    public void exportBarcodeExcel(@Valid BarcodePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BarcodeDO> list = barcodeService.getBarcodePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "条码.xls", "数据", BarcodeRespVO.class,
                        BeanUtils.toBean(list, BarcodeRespVO.class));
    }

}