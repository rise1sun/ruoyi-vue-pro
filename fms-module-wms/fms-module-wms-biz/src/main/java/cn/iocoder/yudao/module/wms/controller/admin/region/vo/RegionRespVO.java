package cn.iocoder.yudao.module.wms.controller.admin.region.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 库位管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RegionRespVO {

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "区域id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24856")
    @ExcelProperty("区域id")
    private Long id;

    @Schema(description = "区域名称", example = "芋艿")
    @ExcelProperty("区域名称")
    private String name;

    @Schema(description = "区域前缀")
    @ExcelProperty("区域前缀")
    private String prefix;

}