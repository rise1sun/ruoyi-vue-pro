package cn.iocoder.yudao.module.wms.controller.admin.tray;

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

import cn.iocoder.yudao.module.wms.controller.admin.tray.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.tray.TrayDO;
import cn.iocoder.yudao.module.wms.service.tray.TrayService;

@Tag(name = "管理后台 - 托盘")
@RestController
@RequestMapping("/wms/tray")
@Validated
public class TrayController {

    @Resource
    private TrayService trayService;

    @PostMapping("/create")
    @Operation(summary = "创建托盘")
    @PreAuthorize("@ss.hasPermission('wms:tray:create')")
    public CommonResult<Long> createTray(@Valid @RequestBody TraySaveReqVO createReqVO) {
        return success(trayService.createTray(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新托盘")
    @PreAuthorize("@ss.hasPermission('wms:tray:update')")
    public CommonResult<Boolean> updateTray(@Valid @RequestBody TraySaveReqVO updateReqVO) {
        trayService.updateTray(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除托盘")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:tray:delete')")
    public CommonResult<Boolean> deleteTray(@RequestParam("id") Long id) {
        trayService.deleteTray(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得托盘")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:tray:query')")
    public CommonResult<TrayRespVO> getTray(@RequestParam("id") Long id) {
        TrayDO tray = trayService.getTray(id);
        return success(BeanUtils.toBean(tray, TrayRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得托盘分页")
    @PreAuthorize("@ss.hasPermission('wms:tray:query')")
    public CommonResult<PageResult<TrayRespVO>> getTrayPage(@Valid TrayPageReqVO pageReqVO) {
        PageResult<TrayDO> pageResult = trayService.getTrayPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TrayRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出托盘 Excel")
    @PreAuthorize("@ss.hasPermission('wms:tray:export')")
    @OperateLog(type = EXPORT)
    public void exportTrayExcel(@Valid TrayPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TrayDO> list = trayService.getTrayPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "托盘.xls", "数据", TrayRespVO.class,
                        BeanUtils.toBean(list, TrayRespVO.class));
    }

}