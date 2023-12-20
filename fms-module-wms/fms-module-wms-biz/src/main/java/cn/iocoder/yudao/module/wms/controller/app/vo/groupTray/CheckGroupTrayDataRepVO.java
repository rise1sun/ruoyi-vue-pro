package cn.iocoder.yudao.module.wms.controller.app.vo.groupTray;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author jiangfeng
 * @date 2023/12/18
 */
@Data
@Schema(description = "管理后台 - 校验结果数据 VO")
public class CheckGroupTrayDataRepVO {

    @Schema(description = "托盘")
    private Boolean checkTrayNo;

    @Schema(description = "托盘校验结果")
    private String checkTrayNoResult;

    @Schema(description = "库位")
    private Boolean checkStorage;

    @Schema(description = "库位校验结果")
    private String checkStorageResult;

    @Schema(description = "目标库位")
    private Boolean checkTargetStorage;

    @Schema(description = "目标库位校验结果")
    private String checkTargetStorageResult;


}
