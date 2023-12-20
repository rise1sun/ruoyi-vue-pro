package cn.iocoder.yudao.module.wms.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author jiangfeng
 * @date 2023/12/19
 */
@Schema(description = "管理后台 - 创建条码数据 VO")
@Data
public class CreateBarcodeReqVO {

    @NotBlank(message = "条码不能为空")
    private String barcode;

    @NotBlank(message = "通道号不能为空")
    private Integer channel;
}
