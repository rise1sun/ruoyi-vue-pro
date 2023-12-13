package cn.iocoder.yudao.module.wms.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.wms.common.annotation.RedissonLock;
import cn.iocoder.yudao.module.wms.controller.admin.barcode.vo.BarcodeSaveReqVO;
import cn.iocoder.yudao.module.wms.controller.app.vo.EmptyTrayWarehousingReqVO;
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
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

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
}
