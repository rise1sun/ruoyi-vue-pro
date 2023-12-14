package cn.iocoder.yudao.module.wms.dal.mysql.secureinvokerecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.wms.controller.admin.secureinvokerecord.vo.SecureInvokeRecordPageReqVO;
import cn.iocoder.yudao.module.wms.dal.dataobject.secureinvokerecord.SecureInvokeRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 本地消息 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface SecureInvokeRecordMapper extends BaseMapperX<SecureInvokeRecordDO> {

    default PageResult<SecureInvokeRecordDO> selectPage(SecureInvokeRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SecureInvokeRecordDO>()
                .eqIfPresent(SecureInvokeRecordDO::getSecureInvokeJson, reqVO.getSecureInvokeJson())
                .betweenIfPresent(SecureInvokeRecordDO::getNextRetryTime, reqVO.getNextRetryTime())
                .eqIfPresent(SecureInvokeRecordDO::getRetryTimes, reqVO.getRetryTimes())
                .eqIfPresent(SecureInvokeRecordDO::getMaxRetryTimes, reqVO.getMaxRetryTimes())
                .eqIfPresent(SecureInvokeRecordDO::getFailReason, reqVO.getFailReason())
                .betweenIfPresent(SecureInvokeRecordDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SecureInvokeRecordDO::getStatus, reqVO.getStatus())
                .orderByDesc(SecureInvokeRecordDO::getId));
    }

}