package cn.iocoder.yudao.module.wms.controller.app.vo.groupTray;

import cn.iocoder.yudao.module.wms.controller.app.vo.BarcodeVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author jiangfeng
 * @date 2023/12/18
 */
@Schema(description = "管理后台 - 组盘 VO")
@Data
public class GroupTrayReqVO {

        @NotBlank
        @Schema(description = "托盘号")
        private String tray;

        @NotBlank
        @Schema(description = "当前库位")
        private String storage;

        @NotBlank
        @Schema(description = "目标库位")
        private String targetStorage;

        @NotNull
        @Schema(description = "条码列表")
        private List<BarcodeVO> barcodes;

}
