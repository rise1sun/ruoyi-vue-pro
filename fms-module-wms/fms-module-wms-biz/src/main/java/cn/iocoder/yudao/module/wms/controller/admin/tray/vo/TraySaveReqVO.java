package cn.iocoder.yudao.module.wms.controller.admin.tray.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 托盘新增/修改 Request VO")
@Data
public class TraySaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1451")
    private Long id;

    @Schema(description = "托盘编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "托盘编号不能为空")
    private String trayNo;

    @Schema(description = "托盘类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "托盘类型不能为空")
    private Integer type;

    @Schema(description = "托盘状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "托盘状态不能为空")
    private Integer status;

}