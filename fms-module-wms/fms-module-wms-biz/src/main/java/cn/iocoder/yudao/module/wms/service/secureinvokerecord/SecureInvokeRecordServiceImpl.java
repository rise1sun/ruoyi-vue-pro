package cn.iocoder.yudao.module.wms.service.secureinvokerecord;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.wms.controller.admin.secureinvokerecord.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.secureinvokerecord.SecureInvokeRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.wms.dal.mysql.secureinvokerecord.SecureInvokeRecordMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.*;

/**
 * 本地消息 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class SecureInvokeRecordServiceImpl implements SecureInvokeRecordService {

    @Resource
    private SecureInvokeRecordMapper secureInvokeRecordMapper;

    @Override
    public Long createSecureInvokeRecord(SecureInvokeRecordSaveReqVO createReqVO) {
        // 插入
        SecureInvokeRecordDO secureInvokeRecord = BeanUtils.toBean(createReqVO, SecureInvokeRecordDO.class);
        secureInvokeRecordMapper.insert(secureInvokeRecord);
        // 返回
        return secureInvokeRecord.getId();
    }

    @Override
    public void updateSecureInvokeRecord(SecureInvokeRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateSecureInvokeRecordExists(updateReqVO.getId());
        // 更新
        SecureInvokeRecordDO updateObj = BeanUtils.toBean(updateReqVO, SecureInvokeRecordDO.class);
        secureInvokeRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteSecureInvokeRecord(Long id) {
        // 校验存在
        validateSecureInvokeRecordExists(id);
        // 删除
        secureInvokeRecordMapper.deleteById(id);
    }

    private void validateSecureInvokeRecordExists(Long id) {
        if (secureInvokeRecordMapper.selectById(id) == null) {
            throw exception(SECURE_INVOKE_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public SecureInvokeRecordDO getSecureInvokeRecord(Long id) {
        return secureInvokeRecordMapper.selectById(id);
    }

    @Override
    public PageResult<SecureInvokeRecordDO> getSecureInvokeRecordPage(SecureInvokeRecordPageReqVO pageReqVO) {
        return secureInvokeRecordMapper.selectPage(pageReqVO);
    }

}