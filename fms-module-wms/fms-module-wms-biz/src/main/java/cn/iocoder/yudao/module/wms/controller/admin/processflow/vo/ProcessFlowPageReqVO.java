package cn.iocoder.yudao.module.wms.controller.admin.processflow.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工艺流程表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcessFlowPageReqVO extends PageParam {

    @Schema(description = "工艺流程名称", example = "赵六")
    private String name;

    @Schema(description = "状态", example = "2")
    private Integer status;

    @Schema(description = "类型", example = "2")
    private Integer type;

    @Schema(description = "描述", example = "你说的对")
    private String description;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}