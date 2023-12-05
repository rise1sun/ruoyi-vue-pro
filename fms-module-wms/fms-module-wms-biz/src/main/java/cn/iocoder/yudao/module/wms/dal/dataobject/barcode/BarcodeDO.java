package cn.iocoder.yudao.module.wms.dal.dataobject.barcode;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 条码 DO
 *
 * @author 芋道源码
 */
@TableName("wms_barcode")
@KeySequence("wms_barcode_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarcodeDO extends BaseDO {

    /**
     * 条码id
     */
    @TableId
    private Long id;
    /**
     * 条码号
     */
    private String barcode;
    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 规格型号
     */
    private String spec;
    /**
     * 类型
     */
    private String type;
    /**
     * 库区
     */
    private String area;
    /**
     * 托盘号
     */
    private String tray;
    /**
     * 条码状态（1 在库 2 离库 3途中）
     */
    private Integer barcodeStatus;
    /**
     * 工艺流程id
     */
    private Integer formulaId;
    /**
     * 工艺流程名称
     */
    private String formulaName;
    /**
     * 工艺子节点编号
     */
    private Integer formulaItemId;
    /**
     * 工艺节点名称
     */
    private String formulaItemName;
    /**
     * ng点位
     */
    private String ngSite;
    /**
     * 复测记录标记
     */
    private Integer retestMarkers;
    /**
     * 通道号
     */
    private Integer channelIndex;
    /**
     * 批次id
     */
    private Integer batchId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 库位
     */
    private String storage;

}