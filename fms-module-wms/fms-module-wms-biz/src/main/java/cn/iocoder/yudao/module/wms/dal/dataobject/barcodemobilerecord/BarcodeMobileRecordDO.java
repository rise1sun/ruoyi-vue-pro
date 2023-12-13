package cn.iocoder.yudao.module.wms.dal.dataobject.barcodemobilerecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 条码移动记录 DO
 *
 * @author 超级管理员
 */
@TableName("wms_barcode_mobile_record")
@KeySequence("wms_barcode_mobile_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarcodeMobileRecordDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 托盘号
     */
    private String trayNo;
    /**
     * 组托编号
     */
    private String suNo;
    /**
     * 条码号
     */
    private String barcode;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 规格/型号
     */
    private String spec;
    /**
     * 批次
     */
    private String batch;
    /**
     * 库区
     */
    private String region;
    /**
     * 储位
     */
    private String storage;
    /**
     * 条码状态
     *
     * 枚举 {@link TODO wms_barcode_status 对应的类}
     */
    private String barcodeStatus;
    /**
     * 移动类型
     *
     * 枚举 {@link TODO wms_move_type 对应的类}
     */
    private String moveType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 静置时间
     */
    private String restTime;

}