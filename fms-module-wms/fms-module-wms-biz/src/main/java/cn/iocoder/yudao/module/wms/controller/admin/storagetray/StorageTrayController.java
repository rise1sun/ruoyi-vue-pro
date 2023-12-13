package cn.iocoder.yudao.module.wms.controller.admin.storagetray;

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

import cn.iocoder.yudao.module.wms.controller.admin.storagetray.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.storagetray.StorageTrayDO;
import cn.iocoder.yudao.module.wms.service.storagetray.StorageTrayService;

@Tag(name = "管理后台 - 库位托盘")
@RestController
@RequestMapping("/wms/storage-tray")
@Validated
public class StorageTrayController {

    @Resource
    private StorageTrayService storageTrayService;

    @PostMapping("/create")
    @Operation(summary = "创建库位托盘")
    @PreAuthorize("@ss.hasPermission('wms:storage-tray:create')")
    public CommonResult<Long> createStorageTray(@Valid @RequestBody StorageTraySaveReqVO createReqVO) {
        return success(storageTrayService.createStorageTray(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新库位托盘")
    @PreAuthorize("@ss.hasPermission('wms:storage-tray:update')")
    public CommonResult<Boolean> updateStorageTray(@Valid @RequestBody StorageTraySaveReqVO updateReqVO) {
        storageTrayService.updateStorageTray(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除库位托盘")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:storage-tray:delete')")
    public CommonResult<Boolean> deleteStorageTray(@RequestParam("id") Long id) {
        storageTrayService.deleteStorageTray(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得库位托盘")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:storage-tray:query')")
    public CommonResult<StorageTrayRespVO> getStorageTray(@RequestParam("id") Long id) {
        StorageTrayDO storageTray = storageTrayService.getStorageTray(id);
        return success(BeanUtils.toBean(storageTray, StorageTrayRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得库位托盘分页")
    @PreAuthorize("@ss.hasPermission('wms:storage-tray:query')")
    public CommonResult<PageResult<StorageTrayRespVO>> getStorageTrayPage(@Valid StorageTrayPageReqVO pageReqVO) {
        PageResult<StorageTrayDO> pageResult = storageTrayService.getStorageTrayPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StorageTrayRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出库位托盘 Excel")
    @PreAuthorize("@ss.hasPermission('wms:storage-tray:export')")
    @OperateLog(type = EXPORT)
    public void exportStorageTrayExcel(@Valid StorageTrayPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StorageTrayDO> list = storageTrayService.getStorageTrayPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "库位托盘.xls", "数据", StorageTrayRespVO.class,
                        BeanUtils.toBean(list, StorageTrayRespVO.class));
    }

}