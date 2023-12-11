package cn.iocoder.yudao.module.wms.dal.dataobject.region;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 库位管理 DO
 *
 * @author 超级管理员
 */
@TableName("wms_region")
@KeySequence("wms_region_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionDO extends BaseDO {

    /**
     * 区域id
     */
    @TableId
    private Long id;
    /**
     * 区域名称
     */
    private String name;
    /**
     * 区域前缀
     */
    private String prefix;

}