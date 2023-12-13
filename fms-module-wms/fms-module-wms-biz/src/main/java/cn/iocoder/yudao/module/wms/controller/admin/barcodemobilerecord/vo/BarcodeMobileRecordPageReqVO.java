package cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 条码移动记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BarcodeMobileRecordPageReqVO extends PageParam {

    @Schema(description = "托盘号")
    private String trayNo;

    @Schema(description = "组托编号")
    private String suNo;

    @Schema(description = "条码号")
    private String barcode;

    @Schema(description = "数量")
    private Integer number;

    @Schema(description = "规格/型号")
    private String spec;

    @Schema(description = "批次")
    private String batch;

    @Schema(description = "库区")
    private String region;

    @Schema(description = "储位")
    private String storage;

    @Schema(description = "条码状态", example = "2")
    private String barcodeStatus;

    @Schema(description = "移动类型", example = "2")
    private String moveType;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "静置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] restTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}