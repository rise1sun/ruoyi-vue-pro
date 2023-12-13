package cn.iocoder.yudao.module.wms.service.storagetray;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.wms.controller.admin.storagetray.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.storagetray.StorageTrayDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.wms.dal.mysql.storagetray.StorageTrayMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.*;

/**
 * 库位托盘 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class StorageTrayServiceImpl implements StorageTrayService {

    @Resource
    private StorageTrayMapper storageTrayMapper;

    @Override
    public Long createStorageTray(StorageTraySaveReqVO createReqVO) {
        // 插入
        StorageTrayDO storageTray = BeanUtils.toBean(createReqVO, StorageTrayDO.class);
        storageTrayMapper.insert(storageTray);
        // 返回
        return storageTray.getId();
    }

    @Override
    public void updateStorageTray(StorageTraySaveReqVO updateReqVO) {
        // 校验存在
        validateStorageTrayExists(updateReqVO.getId());
        // 更新
        StorageTrayDO updateObj = BeanUtils.toBean(updateReqVO, StorageTrayDO.class);
        storageTrayMapper.updateById(updateObj);
    }

    @Override
    public void deleteStorageTray(Long id) {
        // 校验存在
        validateStorageTrayExists(id);
        // 删除
        storageTrayMapper.deleteById(id);
    }

    private void validateStorageTrayExists(Long id) {
        if (storageTrayMapper.selectById(id) == null) {
            throw exception(STORAGE_TRAY_NOT_EXISTS);
        }
    }

    @Override
    public StorageTrayDO getStorageTray(Long id) {
        return storageTrayMapper.selectById(id);
    }

    @Override
    public PageResult<StorageTrayDO> getStorageTrayPage(StorageTrayPageReqVO pageReqVO) {
        return storageTrayMapper.selectPage(pageReqVO);
    }

}