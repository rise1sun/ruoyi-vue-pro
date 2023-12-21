package cn.iocoder.yudao.module.wms.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author jiangfeng
 * @date 2023/12/18
 */
@Data
public class BarcodeVO {
    @NotBlank
    @Schema(description = "条码号")
    private String batteriesBarcode;

    @NotNull
    @Schema(description = "通道号")
    private String channelNo;
}
