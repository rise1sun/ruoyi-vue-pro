package cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 条码移动记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BarcodeMobileRecordRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27596")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "托盘号")
    @ExcelProperty("托盘号")
    private String trayNo;

    @Schema(description = "组托编号")
    @ExcelProperty("组托编号")
    private String suNo;

    @Schema(description = "条码号")
    @ExcelProperty("条码号")
    private String barcode;

    @Schema(description = "数量")
    @ExcelProperty("数量")
    private Integer number;

    @Schema(description = "规格/型号")
    @ExcelProperty("规格/型号")
    private String spec;

    @Schema(description = "批次")
    @ExcelProperty("批次")
    private String batch;

    @Schema(description = "库区")
    @ExcelProperty("库区")
    private String region;

    @Schema(description = "储位")
    @ExcelProperty("储位")
    private String storage;

    @Schema(description = "条码状态", example = "2")
    @ExcelProperty(value = "条码状态", converter = DictConvert.class)
    @DictFormat("wms_barcode_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String barcodeStatus;

    @Schema(description = "移动类型", example = "2")
    @ExcelProperty(value = "移动类型", converter = DictConvert.class)
    @DictFormat("wms_move_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String moveType;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "静置时间")
    @ExcelProperty("静置时间")
    private String restTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}