package cn.iocoder.yudao.module.wms.controller.app.vo.groupTray;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiangfeng
 * @date 2023/12/18
 */
@Data
@Schema(description = "管理后台 - 组盘数据 VO")
public class CheckGroupTrayDataReqVO {
        @NotBlank
        @Schema(description = "托盘号")
        private String trayNo;

        @NotBlank
        @Schema(description = "当前库位")
        private String storage;

        @NotBlank
        @Schema(description = "目标库位")
        private String targetStorage;
}
