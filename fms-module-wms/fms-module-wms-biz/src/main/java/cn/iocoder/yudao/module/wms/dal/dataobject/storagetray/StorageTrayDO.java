package cn.iocoder.yudao.module.wms.dal.dataobject.storagetray;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 库位托盘 DO
 *
 * @author 超级管理员
 */
@TableName("wms_storage_tray")
@KeySequence("wms_storage_tray_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageTrayDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 库位id
     */
    private Long storageId;
    /**
     * 托盘id
     */
    private Long trayId;

}