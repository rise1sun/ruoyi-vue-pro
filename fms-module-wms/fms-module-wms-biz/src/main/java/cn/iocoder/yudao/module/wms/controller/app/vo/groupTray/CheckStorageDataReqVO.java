package cn.iocoder.yudao.module.wms.controller.app.vo.groupTray;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiangfeng
 * @date 2023/12/18
 */
@Data
@Schema(description = "管理后台 - 校验库位数据 VO")
public class CheckStorageDataReqVO {
    @NotBlank
    @Schema(description = "当前库位")
    private String storage;
}
