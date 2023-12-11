package cn.iocoder.yudao.module.wms.dal.dataobject.region;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 库位 DO
 *
 * @author 超级管理员
 */
@TableName("wms_region_storage")
@KeySequence("wms_region_storage_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionStorageDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 库位编号
     */
    private String code;
    /**
     * 库位名称
     */
    private String name;
    /**
     * 层
     */
    private Integer line;
    /**
     * 列
     */
    private Integer row;
    /**
     * 面
     */
    private String face;
    /**
     * 备注
     */
    private String remark;
    /**
     * 区域ID
     */
    private Long regionId;
    /**
     * 状态
     *
     * 枚举 {@link TODO wms_storage_status 对应的类}
     */
    private Integer status;
    /**
     * 消防通道
     *
     * 枚举 {@link TODO wms_fire_channel 对应的类}
     */
    private Integer fireChannel;
    /**
     * 特殊库位
     *
     * 枚举 {@link TODO wms_special_storage 对应的类}
     */
    private Integer specialStorage;
    /**
     * plc地址
     */
    private String storageAddress;
    /**
     * 站点顺序
     */
    private Integer doorOrder;

}