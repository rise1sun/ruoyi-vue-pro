package cn.iocoder.yudao.module.wms.dal.dataobject.secureinvokerecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 本地消息 DO
 *
 * @author 超级管理员
 */
@TableName("wms_secure_invoke_record")
@KeySequence("wms_secure_invoke_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecureInvokeRecordDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 快照参数
     */
    private String secureInvokeJson;
    /**
     * 下次重试时间
     */
    private LocalDateTime nextRetryTime;
    /**
     * 已重试次数
     */
    private Integer retryTimes;
    /**
     * 最大重试次数
     */
    private Integer maxRetryTimes;
    /**
     * 失败信息
     */
    private String failReason;
    /**
     * 状态
     *
     * 枚举 {@link TODO wms_invoke_recode_status 对应的类}
     */
    private Integer status;

}