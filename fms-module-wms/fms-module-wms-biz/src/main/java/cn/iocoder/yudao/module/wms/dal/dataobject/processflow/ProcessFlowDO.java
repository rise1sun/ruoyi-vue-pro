package cn.iocoder.yudao.module.wms.dal.dataobject.processflow;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工艺流程表 DO
 *
 * @author 超级管理员
 */
@TableName("wms_process_flow")
@KeySequence("wms_process_flow_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessFlowDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 工艺流程名称
     */
    private String name;
    /**
     * 状态
     *
     * 枚举 {@link TODO wms_process_flow_status 对应的类}
     */
    private Integer status;
    /**
     * 类型
     *
     * 枚举 {@link TODO wms_process_flow_type 对应的类}
     */
    private Integer type;
    /**
     * 描述
     */
    private String description;

}