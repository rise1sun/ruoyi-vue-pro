package cn.iocoder.yudao.module.wms.controller.admin.barcode.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 条码 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BarcodeRespVO {

    @Schema(description = "条码id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24861")
    @ExcelProperty("条码id")
    private Long id;

    @Schema(description = "条码号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("条码号")
    private String barcode;

    @Schema(description = "数据来源")
    @ExcelProperty("数据来源")
    private String dataSource;

    @Schema(description = "规格型号")
    @ExcelProperty("规格型号")
    private String spec;

    @Schema(description = "类型", example = "1")
    @ExcelProperty("类型")
    private String type;

    @Schema(description = "库区")
    @ExcelProperty("库区")
    private String area;

    @Schema(description = "托盘号")
    @ExcelProperty("托盘号")
    private String tray;

    @Schema(description = "条码状态（1 在库 2 离库 3途中）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("条码状态（1 在库 2 离库 3途中）")
    private Integer barcodeStatus;

    @Schema(description = "工艺流程id", example = "13927")
    @ExcelProperty("工艺流程id")
    private Integer formulaId;

    @Schema(description = "工艺流程名称", example = "张三")
    @ExcelProperty("工艺流程名称")
    private String formulaName;

    @Schema(description = "工艺子节点编号", example = "7798")
    @ExcelProperty("工艺子节点编号")
    private Integer formulaItemId;

    @Schema(description = "工艺节点名称", example = "王五")
    @ExcelProperty("工艺节点名称")
    private String formulaItemName;

    @Schema(description = "ng点位")
    @ExcelProperty("ng点位")
    private String ngSite;

    @Schema(description = "复测记录标记")
    @ExcelProperty("复测记录标记")
    private Integer retestMarkers;

    @Schema(description = "通道号")
    @ExcelProperty("通道号")
    private Integer channelIndex;

    @Schema(description = "批次id", example = "950")
    @ExcelProperty("批次id")
    private Integer batchId;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "库位")
    @ExcelProperty("库位")
    private String storage;

}