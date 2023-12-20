package cn.iocoder.yudao.module.wms.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiangfeng
 * @date 2023/12/15
 */
@Schema(description = "管理后台 - 手动下料 VO")
@Data
public class ManualBlankingReqVO {

    @Schema(description = "托盘号")
    @NotBlank(message = "托盘号不能为空")
    private String trayNo;

    @Schema(description = "库位号")
    @NotBlank(message = "库位号不能为空")
    private String storage;
}
