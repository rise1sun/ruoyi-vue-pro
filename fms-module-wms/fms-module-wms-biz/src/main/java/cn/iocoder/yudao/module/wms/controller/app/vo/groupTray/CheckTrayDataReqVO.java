package cn.iocoder.yudao.module.wms.controller.app.vo.groupTray;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiangfeng
 * @date 2023/12/19
 */
@Schema(description = "管理后台 - 校验托盘数据 VO")
@Data
public class CheckTrayDataReqVO {

    @NotBlank(message = "托盘号不能为空")
    private String TrayNo;
}
