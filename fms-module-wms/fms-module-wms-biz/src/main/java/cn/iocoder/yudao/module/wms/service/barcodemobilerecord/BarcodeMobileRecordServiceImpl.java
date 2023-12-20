package cn.iocoder.yudao.module.wms.service.barcodemobilerecord;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcodemobilerecord.BarcodeMobileRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.wms.dal.mysql.barcodemobilerecord.BarcodeMobileRecordMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.*;

/**
 * 条码移动记录 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class BarcodeMobileRecordServiceImpl implements BarcodeMobileRecordService {

    @Resource
    private BarcodeMobileRecordMapper barcodeMobileRecordMapper;

    @Override
    public Long createBarcodeMobileRecord(BarcodeMobileRecordSaveReqVO createReqVO) {
        // 插入
        BarcodeMobileRecordDO barcodeMobileRecord = BeanUtils.toBean(createReqVO, BarcodeMobileRecordDO.class);
        barcodeMobileRecordMapper.insert(barcodeMobileRecord);
        // 返回
        return barcodeMobileRecord.getId();
    }

    @Override
    public void updateBarcodeMobileRecord(BarcodeMobileRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateBarcodeMobileRecordExists(updateReqVO.getId());
        // 更新
        BarcodeMobileRecordDO updateObj = BeanUtils.toBean(updateReqVO, BarcodeMobileRecordDO.class);
        barcodeMobileRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteBarcodeMobileRecord(Long id) {
        // 校验存在
        validateBarcodeMobileRecordExists(id);
        // 删除
        barcodeMobileRecordMapper.deleteById(id);
    }

    private void validateBarcodeMobileRecordExists(Long id) {
        if (barcodeMobileRecordMapper.selectById(id) == null) {
            throw exception(BARCODE_MOBILE_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public BarcodeMobileRecordDO getBarcodeMobileRecord(Long id) {
        return barcodeMobileRecordMapper.selectById(id);
    }

    @Override
    public PageResult<BarcodeMobileRecordDO> getBarcodeMobileRecordPage(BarcodeMobileRecordPageReqVO pageReqVO) {
        return barcodeMobileRecordMapper.selectPage(pageReqVO);
    }
}