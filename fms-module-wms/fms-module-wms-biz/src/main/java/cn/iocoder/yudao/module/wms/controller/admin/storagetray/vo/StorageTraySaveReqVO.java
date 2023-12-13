package cn.iocoder.yudao.module.wms.controller.admin.storagetray.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 库位托盘新增/修改 Request VO")
@Data
public class StorageTraySaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2604")
    private Long id;

    @Schema(description = "库位id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31207")
    @NotNull(message = "库位id不能为空")
    private Long storageId;

    @Schema(description = "托盘id", requiredMode = Schema.RequiredMode.REQUIRED, example = "20894")
    @NotNull(message = "托盘id不能为空")
    private Long trayId;

}