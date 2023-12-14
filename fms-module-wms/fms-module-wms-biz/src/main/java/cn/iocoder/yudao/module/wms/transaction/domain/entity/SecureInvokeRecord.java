package cn.iocoder.yudao.module.wms.transaction.domain.entity;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.module.wms.transaction.domain.dto.SecureInvokeDTO;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;

import java.util.Date;

@TableName("wms_secure_invoke_record")
@KeySequence("wms_secure_invoke_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecureInvokeRecord extends BaseDO {
    public final static byte STATUS_WAIT = 1;
    public final static byte STATUS_FAIL = 2;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 请求快照参数json
     */
    @TableField(value = "secure_invoke_json", typeHandler = JacksonTypeHandler.class)
    private SecureInvokeDTO secureInvokeDTO;
    /**
     * 状态 1待执行 2已失败
     */
    @TableField("status")
    @Builder.Default
    private byte status = SecureInvokeRecord.STATUS_WAIT;
    /**
     * 下一次重试的时间
     */
    @TableField("next_retry_time")
    @Builder.Default
    private Date nextRetryTime = new Date();
    /**
     * 已经重试的次数
     */
    @TableField("retry_times")
    @Builder.Default
    private Integer retryTimes = 0;
    @TableField("max_retry_times")
    private Integer maxRetryTimes;
    @TableField("fail_reason")
    private String failReason;
//    @TableField("create_time")
//    private Date createTime;
//    @TableField("update_time")
//    private Date updateTime;

}
