package cn.iocoder.yudao.module.wms.dal.dataobject.tray;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 托盘 DO
 *
 * @author 超级管理员
 */
@TableName("wms_tray")
@KeySequence("wms_tray_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrayDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 托盘编号
     */
    private String trayNo;
    /**
     * 托盘类型
     *
     * 枚举 {@link TODO wms_tray_type  对应的类}
     */
    private Integer type;
    /**
     * 托盘状态
     *
     * 枚举 {@link TODO wms_tray_status 对应的类}
     */
    private Integer status;

}