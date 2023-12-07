package cn.iocoder.yudao.module.wms.controller.admin.secureinvokerecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 本地消息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SecureInvokeRecordRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18250")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "快照参数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("快照参数")
    private String secureInvokeJson;

    @Schema(description = "下次重试时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("下次重试时间")
    private LocalDateTime nextRetryTime;

    @Schema(description = "已重试次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("已重试次数")
    private Integer retryTimes;

    @Schema(description = "最大重试次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("最大重试次数")
    private Integer maxRetryTimes;

    @Schema(description = "失败信息", example = "不喜欢")
    @ExcelProperty("失败信息")
    private String failReason;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("wms_invoke_recode_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

}