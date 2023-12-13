package cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 条码移动记录新增/修改 Request VO")
@Data
public class BarcodeMobileRecordSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27596")
    private Long id;

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
    private String restTime;

}