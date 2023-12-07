package cn.iocoder.yudao.module.wms.controller.admin.secureinvokerecord;

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

import cn.iocoder.yudao.module.wms.controller.admin.secureinvokerecord.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.secureinvokerecord.SecureInvokeRecordDO;
import cn.iocoder.yudao.module.wms.service.secureinvokerecord.SecureInvokeRecordService;

@Tag(name = "管理后台 - 本地消息")
@RestController
@RequestMapping("/wms/secure-invoke-record")
@Validated
public class SecureInvokeRecordController {

    @Resource
    private SecureInvokeRecordService secureInvokeRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建本地消息")
    @PreAuthorize("@ss.hasPermission('wms:secure-invoke-record:create')")
    public CommonResult<Long> createSecureInvokeRecord(@Valid @RequestBody SecureInvokeRecordSaveReqVO createReqVO) {
        return success(secureInvokeRecordService.createSecureInvokeRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新本地消息")
    @PreAuthorize("@ss.hasPermission('wms:secure-invoke-record:update')")
    public CommonResult<Boolean> updateSecureInvokeRecord(@Valid @RequestBody SecureInvokeRecordSaveReqVO updateReqVO) {
        secureInvokeRecordService.updateSecureInvokeRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除本地消息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('wms:secure-invoke-record:delete')")
    public CommonResult<Boolean> deleteSecureInvokeRecord(@RequestParam("id") Long id) {
        secureInvokeRecordService.deleteSecureInvokeRecord(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得本地消息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('wms:secure-invoke-record:query')")
    public CommonResult<SecureInvokeRecordRespVO> getSecureInvokeRecord(@RequestParam("id") Long id) {
        SecureInvokeRecordDO secureInvokeRecord = secureInvokeRecordService.getSecureInvokeRecord(id);
        return success(BeanUtils.toBean(secureInvokeRecord, SecureInvokeRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得本地消息分页")
    @PreAuthorize("@ss.hasPermission('wms:secure-invoke-record:query')")
    public CommonResult<PageResult<SecureInvokeRecordRespVO>> getSecureInvokeRecordPage(@Valid SecureInvokeRecordPageReqVO pageReqVO) {
        PageResult<SecureInvokeRecordDO> pageResult = secureInvokeRecordService.getSecureInvokeRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SecureInvokeRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出本地消息 Excel")
    @PreAuthorize("@ss.hasPermission('wms:secure-invoke-record:export')")
    @OperateLog(type = EXPORT)
    public void exportSecureInvokeRecordExcel(@Valid SecureInvokeRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SecureInvokeRecordDO> list = secureInvokeRecordService.getSecureInvokeRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "本地消息.xls", "数据", SecureInvokeRecordRespVO.class,
                        BeanUtils.toBean(list, SecureInvokeRecordRespVO.class));
    }

}