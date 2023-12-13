package cn.iocoder.yudao.module.wms.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiangfeng
 * @date 2023/12/13
 */
@Schema(description = "管理后台 - 条码 Response VO")
@Data
public class EmptyTrayWarehousingReqVO {

    @Schema(description = "托盘号")
    @NotBlank(message = "托盘号不能为空")
    private String trayNo;

    @Schema(description = "库位号")
    @NotBlank(message = "库位号不能为空")
    private String storage;
}
