package cn.iocoder.yudao.module.wms.controller.admin.secureinvokerecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 本地消息新增/修改 Request VO")
@Data
public class SecureInvokeRecordSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18250")
    private Long id;

    @Schema(description = "快照参数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "快照参数不能为空")
    private String secureInvokeJson;

    @Schema(description = "下次重试时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "下次重试时间不能为空")
    private LocalDateTime nextRetryTime;

    @Schema(description = "已重试次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "已重试次数不能为空")
    private Integer retryTimes;

    @Schema(description = "最大重试次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "最大重试次数不能为空")
    private Integer maxRetryTimes;

    @Schema(description = "失败信息", example = "不喜欢")
    private String failReason;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}