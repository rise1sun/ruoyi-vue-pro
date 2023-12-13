package cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord;

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

import cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcodemobilerecord.BarcodeMobileRecordDO;
import cn.iocoder.yudao.module.wms.service.barcodemobilerecord.BarcodeMobileRecordService;

@Tag(name = "管理后台 - 条码移动记录")
@RestController
@RequestMapping("/wms/barcode-mobile-record")
@Validated
public class BarcodeMobileRecordController {

    @Resource
    private BarcodeMobileRecordService barcodeMobileRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建条码移动记录")
    @PreAuthorize("@ss.hasPermission('wms:barcode-mobile-record:create')")
    public CommonResult<Long> createBarcodeMobileRecord(@Valid @RequestBody BarcodeMobileRecordSaveReqVO createReqVO) {
        return success(barcodeMobileRecordService.createBarcodeMobileRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新条码移动记录")
    @PreAuthorize("@ss.hasPermission('wms:barcode-mobile-record:update')")
    public CommonResult<Boolean> updateBarcodeMobileRecord(@Valid @RequestBody BarcodeMobileRecordSaveReqVO updateReqVO) {
        barcodeMobileRecordService.updateBarcodeMobileRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除条码移动记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:barcode-mobile-record:delete')")
    public CommonResult<Boolean> deleteBarcodeMobileRecord(@RequestParam("id") Long id) {
        barcodeMobileRecordService.deleteBarcodeMobileRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得条码移动记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:barcode-mobile-record:query')")
    public CommonResult<BarcodeMobileRecordRespVO> getBarcodeMobileRecord(@RequestParam("id") Long id) {
        BarcodeMobileRecordDO barcodeMobileRecord = barcodeMobileRecordService.getBarcodeMobileRecord(id);
        return success(BeanUtils.toBean(barcodeMobileRecord, BarcodeMobileRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得条码移动记录分页")
    @PreAuthorize("@ss.hasPermission('wms:barcode-mobile-record:query')")
    public CommonResult<PageResult<BarcodeMobileRecordRespVO>> getBarcodeMobileRecordPage(@Valid BarcodeMobileRecordPageReqVO pageReqVO) {
        PageResult<BarcodeMobileRecordDO> pageResult = barcodeMobileRecordService.getBarcodeMobileRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BarcodeMobileRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出条码移动记录 Excel")
    @PreAuthorize("@ss.hasPermission('wms:barcode-mobile-record:export')")
    @OperateLog(type = EXPORT)
    public void exportBarcodeMobileRecordExcel(@Valid BarcodeMobileRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BarcodeMobileRecordDO> list = barcodeMobileRecordService.getBarcodeMobileRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "条码移动记录.xls", "数据", BarcodeMobileRecordRespVO.class,
                        BeanUtils.toBean(list, BarcodeMobileRecordRespVO.class));
    }

}