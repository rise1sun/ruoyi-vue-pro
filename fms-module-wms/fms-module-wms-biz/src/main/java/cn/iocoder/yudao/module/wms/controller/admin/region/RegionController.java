package cn.iocoder.yudao.module.wms.controller.admin.region;

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

import cn.iocoder.yudao.module.wms.controller.admin.region.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionDO;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionStorageDO;
import cn.iocoder.yudao.module.wms.service.region.RegionService;

@Tag(name = "管理后台 - 库位管理")
@RestController
@RequestMapping("/wms/region")
@Validated
public class RegionController {

    @Resource
    private RegionService regionService;

    @PostMapping("/create")
    @Operation(summary = "创建库位管理")
    @PreAuthorize("@ss.hasPermission('wms:region:create')")
    public CommonResult<Long> createRegion(@Valid @RequestBody RegionSaveReqVO createReqVO) {
        return success(regionService.createRegion(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新库位管理")
    @PreAuthorize("@ss.hasPermission('wms:region:update')")
    public CommonResult<Boolean> updateRegion(@Valid @RequestBody RegionSaveReqVO updateReqVO) {
        regionService.updateRegion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除库位管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:region:delete')")
    public CommonResult<Boolean> deleteRegion(@RequestParam("id") Long id) {
        regionService.deleteRegion(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得库位管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:region:query')")
    public CommonResult<RegionRespVO> getRegion(@RequestParam("id") Long id) {
        RegionDO region = regionService.getRegion(id);
        return success(BeanUtils.toBean(region, RegionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得库位管理分页")
    @PreAuthorize("@ss.hasPermission('wms:region:query')")
    public CommonResult<PageResult<RegionRespVO>> getRegionPage(@Valid RegionPageReqVO pageReqVO) {
        PageResult<RegionDO> pageResult = regionService.getRegionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RegionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出库位管理 Excel")
    @PreAuthorize("@ss.hasPermission('wms:region:export')")
    @OperateLog(type = EXPORT)
    public void exportRegionExcel(@Valid RegionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RegionDO> list = regionService.getRegionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "库位管理.xls", "数据", RegionRespVO.class,
                        BeanUtils.toBean(list, RegionRespVO.class));
    }

    // ==================== 子表（库位） ====================

    @GetMapping("/region-storage/page")
    @Operation(summary = "获得库位分页")
    @Parameter(name = "regionId", description = "区域ID")
    @PreAuthorize("@ss.hasPermission('wms:region:query')")
    public CommonResult<PageResult<RegionStorageDO>> getRegionStoragePage(PageParam pageReqVO,
                                                                                        @RequestParam("regionId") Long regionId) {
        return success(regionService.getRegionStoragePage(pageReqVO, regionId));
    }

    @PostMapping("/region-storage/create")
    @Operation(summary = "创建库位")
    @PreAuthorize("@ss.hasPermission('wms:region:create')")
    public CommonResult<Long> createRegionStorage(@Valid @RequestBody RegionStorageDO regionStorage) {
        return success(regionService.createRegionStorage(regionStorage));
    }

    @PutMapping("/region-storage/update")
    @Operation(summary = "更新库位")
    @PreAuthorize("@ss.hasPermission('wms:region:update')")
    public CommonResult<Boolean> updateRegionStorage(@Valid @RequestBody RegionStorageDO regionStorage) {
        regionService.updateRegionStorage(regionStorage);
        return success(true);
    }

    @DeleteMapping("/region-storage/delete")
    @Parameter(name = "id", description = "编号", required = true)
    @Operation(summary = "删除库位")
    @PreAuthorize("@ss.hasPermission('wms:region:delete')")
    public CommonResult<Boolean> deleteRegionStorage(@RequestParam("id") Long id) {
        regionService.deleteRegionStorage(id);
        return success(true);
    }

	@GetMapping("/region-storage/get")
	@Operation(summary = "获得库位")
	@Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:region:query')")
	public CommonResult<RegionStorageDO> getRegionStorage(@RequestParam("id") Long id) {
	    return success(regionService.getRegionStorage(id));
	}

}