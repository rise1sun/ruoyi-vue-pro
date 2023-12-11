package cn.iocoder.yudao.module.wms.controller.admin.region.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionStorageDO;

@Schema(description = "管理后台 - 库位管理新增/修改 Request VO")
@Data
public class RegionSaveReqVO {

    @Schema(description = "区域id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24856")
    private Long id;

    @Schema(description = "区域名称", example = "芋艿")
    private String name;

    @Schema(description = "区域前缀")
    private String prefix;

}