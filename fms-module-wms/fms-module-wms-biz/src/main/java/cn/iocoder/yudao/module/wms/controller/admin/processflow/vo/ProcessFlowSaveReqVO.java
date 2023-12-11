package cn.iocoder.yudao.module.wms.controller.admin.processflow.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.processflow.ProcessFlowItemDO;

@Schema(description = "管理后台 - 工艺流程表新增/修改 Request VO")
@Data
public class ProcessFlowSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15046")
    private Long id;

    @Schema(description = "工艺流程名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "工艺流程名称不能为空")
    private String name;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "类型不能为空")
    private Integer type;

    @Schema(description = "描述", example = "你说的对")
    private String description;

}