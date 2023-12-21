package cn.iocoder.yudao.module.wms.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiangfeng
 * @date 2023/12/20
 */
@Data
@Schema(description = "管理后台 - 删除条码数据 VO")
public class DeleteBarcodeReqVO {

    @Schema(description = "条码号")
    @NotBlank(message = "条码不能为空")
    private String barcode;
}
