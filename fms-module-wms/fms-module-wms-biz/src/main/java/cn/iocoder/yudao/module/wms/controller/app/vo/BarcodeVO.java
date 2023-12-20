package cn.iocoder.yudao.module.wms.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * @author jiangfeng
 * @date 2023/12/18
 */
public class BarcodeVO {
    @NotNull
    @Schema(description = "条码号")
    private String barcode;

    @Schema(description = "通道号")
    private String channelNo;
}
