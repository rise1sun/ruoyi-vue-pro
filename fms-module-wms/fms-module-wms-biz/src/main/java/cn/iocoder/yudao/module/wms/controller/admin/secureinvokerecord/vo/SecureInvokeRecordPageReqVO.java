package cn.iocoder.yudao.module.wms.controller.admin.secureinvokerecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 本地消息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SecureInvokeRecordPageReqVO extends PageParam {

    @Schema(description = "快照参数")
    private String secureInvokeJson;

    @Schema(description = "下次重试时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] nextRetryTime;

    @Schema(description = "已重试次数")
    private Integer retryTimes;

    @Schema(description = "最大重试次数")
    private Integer maxRetryTimes;

    @Schema(description = "失败信息", example = "不喜欢")
    private String failReason;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "状态", example = "1")
    private Integer status;

}