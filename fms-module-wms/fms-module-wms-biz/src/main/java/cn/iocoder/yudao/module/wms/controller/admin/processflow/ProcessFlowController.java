package cn.iocoder.yudao.module.wms.controller.admin.processflow;

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

import cn.iocoder.yudao.module.wms.controller.admin.processflow.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.processflow.ProcessFlowDO;
import cn.iocoder.yudao.module.wms.dal.dataobject.processflow.ProcessFlowItemDO;
import cn.iocoder.yudao.module.wms.service.processflow.ProcessFlowService;

@Tag(name = "管理后台 - 工艺流程表")
@RestController
@RequestMapping("/wms/process-flow")
@Validated
public class ProcessFlowController {

    @Resource
    private ProcessFlowService processFlowService;

    @PostMapping("/create")
    @Operation(summary = "创建工艺流程表")
    @PreAuthorize("@ss.hasPermission('wms:process-flow:create')")
    public CommonResult<Long> createProcessFlow(@Valid @RequestBody ProcessFlowSaveReqVO createReqVO) {
        return success(processFlowService.createProcessFlow(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工艺流程表")
    @PreAuthorize("@ss.hasPermission('wms:process-flow:update')")
    public CommonResult<Boolean> updateProcessFlow(@Valid @RequestBody ProcessFlowSaveReqVO updateReqVO) {
        processFlowService.updateProcessFlow(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工艺流程表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:process-flow:delete')")
    public CommonResult<Boolean> deleteProcessFlow(@RequestParam("id") Long id) {
        processFlowService.deleteProcessFlow(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工艺流程表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:process-flow:query')")
    public CommonResult<ProcessFlowRespVO> getProcessFlow(@RequestParam("id") Long id) {
        ProcessFlowDO processFlow = processFlowService.getProcessFlow(id);
        return success(BeanUtils.toBean(processFlow, ProcessFlowRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得工艺流程表分页")
    @PreAuthorize("@ss.hasPermission('wms:process-flow:query')")
    public CommonResult<PageResult<ProcessFlowRespVO>> getProcessFlowPage(@Valid ProcessFlowPageReqVO pageReqVO) {
        PageResult<ProcessFlowDO> pageResult = processFlowService.getProcessFlowPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProcessFlowRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工艺流程表 Excel")
    @PreAuthorize("@ss.hasPermission('wms:process-flow:export')")
    @OperateLog(type = EXPORT)
    public void exportProcessFlowExcel(@Valid ProcessFlowPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ProcessFlowDO> list = processFlowService.getProcessFlowPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "工艺流程表.xls", "数据", ProcessFlowRespVO.class,
                        BeanUtils.toBean(list, ProcessFlowRespVO.class));
    }

    // ==================== 子表（工艺流程表） ====================

    @GetMapping("/process-flow-item/page")
    @Operation(summary = "获得工艺流程表分页")
    @Parameter(name = "processFlowId", description = "主表id")
    @PreAuthorize("@ss.hasPermission('wms:process-flow:query')")
    public CommonResult<PageResult<ProcessFlowItemDO>> getProcessFlowItemPage(PageParam pageReqVO,
                                                                                        @RequestParam("processFlowId") Long processFlowId) {
        return success(processFlowService.getProcessFlowItemPage(pageReqVO, processFlowId));
    }

    @PostMapping("/process-flow-item/create")
    @Operation(summary = "创建工艺流程表")
    @PreAuthorize("@ss.hasPermission('wms:process-flow:create')")
    public CommonResult<Long> createProcessFlowItem(@Valid @RequestBody ProcessFlowItemDO processFlowItem) {
        return success(processFlowService.createProcessFlowItem(processFlowItem));
    }

    @PutMapping("/process-flow-item/update")
    @Operation(summary = "更新工艺流程表")
    @PreAuthorize("@ss.hasPermission('wms:process-flow:update')")
    public CommonResult<Boolean> updateProcessFlowItem(@Valid @RequestBody ProcessFlowItemDO processFlowItem) {
        processFlowService.updateProcessFlowItem(processFlowItem);
        return success(true);
    }

    @DeleteMapping("/process-flow-item/delete")
    @Parameter(name = "id", description = "编号", required = true)
    @Operation(summary = "删除工艺流程表")
    @PreAuthorize("@ss.hasPermission('wms:process-flow:delete')")
    public CommonResult<Boolean> deleteProcessFlowItem(@RequestParam("id") Long id) {
        processFlowService.deleteProcessFlowItem(id);
        return success(true);
    }

	@GetMapping("/process-flow-item/get")
	@Operation(summary = "获得工艺流程表")
	@Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:process-flow:query')")
	public CommonResult<ProcessFlowItemDO> getProcessFlowItem(@RequestParam("id") Long id) {
	    return success(processFlowService.getProcessFlowItem(id));
	}

}