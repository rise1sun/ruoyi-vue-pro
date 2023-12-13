package cn.iocoder.yudao.module.wms.controller.admin.storagetray.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 库位托盘 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StorageTrayRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2604")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "库位id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31207")
    @ExcelProperty("库位id")
    private Long storageId;

    @Schema(description = "托盘id", requiredMode = Schema.RequiredMode.REQUIRED, example = "20894")
    @ExcelProperty("托盘id")
    private Long trayId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}