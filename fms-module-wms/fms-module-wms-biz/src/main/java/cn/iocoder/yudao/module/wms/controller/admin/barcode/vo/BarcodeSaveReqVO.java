package cn.iocoder.yudao.module.wms.controller.admin.barcode.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 条码新增/修改 Request VO")
@Data
public class BarcodeSaveReqVO {

    @Schema(description = "条码id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24861")
    private Long id;

    @Schema(description = "条码号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "条码号不能为空")
    private String barcode;

    @Schema(description = "数据来源")
    private String dataSource;

    @Schema(description = "规格型号")
    private String spec;

    @Schema(description = "类型", example = "1")
    private String type;

    @Schema(description = "库区")
    private String area;

    @Schema(description = "托盘号")
    private String tray;

    @Schema(description = "条码状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "条码状态不能为空")
    private Integer barcodeStatus;

    @Schema(description = "工艺流程id", example = "13927")
    private Integer formulaId;

    @Schema(description = "工艺流程名称", example = "张三")
    private String formulaName;

    @Schema(description = "工艺子节点编号", example = "7798")
    private Integer formulaItemId;

    @Schema(description = "工艺节点名称", example = "王五")
    private String formulaItemName;

    @Schema(description = "ng点位")
    private String ngSite;

    @Schema(description = "复测记录标记")
    private Integer retestMarkers;

    @Schema(description = "通道号")
    private Integer channelIndex;

    @Schema(description = "批次id", example = "950")
    private Integer batchId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "库位")
    private String storage;

}