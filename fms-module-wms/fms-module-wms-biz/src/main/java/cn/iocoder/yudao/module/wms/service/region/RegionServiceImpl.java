package cn.iocoder.yudao.module.wms.service.region;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.wms.controller.admin.region.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionDO;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionStorageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.wms.dal.mysql.region.RegionMapper;
import cn.iocoder.yudao.module.wms.dal.mysql.region.RegionStorageMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.*;

/**
 * 库位管理 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class RegionServiceImpl implements RegionService {

    @Resource
    private RegionMapper regionMapper;
    @Resource
    private RegionStorageMapper regionStorageMapper;

    @Override
    public Long createRegion(RegionSaveReqVO createReqVO) {
        // 插入
        RegionDO region = BeanUtils.toBean(createReqVO, RegionDO.class);
        regionMapper.insert(region);
        // 返回
        return region.getId();
    }

    @Override
    public void updateRegion(RegionSaveReqVO updateReqVO) {
        // 校验存在
        validateRegionExists(updateReqVO.getId());
        // 更新
        RegionDO updateObj = BeanUtils.toBean(updateReqVO, RegionDO.class);
        regionMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRegion(Long id) {
        // 校验存在
        validateRegionExists(id);
        // 删除
        regionMapper.deleteById(id);

        // 删除子表
        deleteRegionStorageByRegionId(id);
    }

    private void validateRegionExists(Long id) {
        if (regionMapper.selectById(id) == null) {
            throw exception(REGION_NOT_EXISTS);
        }
    }

    @Override
    public RegionDO getRegion(Long id) {
        return regionMapper.selectById(id);
    }

    @Override
    public PageResult<RegionDO> getRegionPage(RegionPageReqVO pageReqVO) {
        return regionMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（库位） ====================

    @Override
    public PageResult<RegionStorageDO> getRegionStoragePage(PageParam pageReqVO, Long regionId) {
        return regionStorageMapper.selectPage(pageReqVO, regionId);
    }

    @Override
    public Long createRegionStorage(RegionStorageDO regionStorage) {
        regionStorageMapper.insert(regionStorage);
        return regionStorage.getId();
    }

    @Override
    public void updateRegionStorage(RegionStorageDO regionStorage) {
        // 校验存在
        validateRegionStorageExists(regionStorage.getId());
        // 更新
        regionStorageMapper.updateById(regionStorage);
    }

    @Override
    public void deleteRegionStorage(Long id) {
        // 校验存在
        validateRegionStorageExists(id);
        // 删除
        regionStorageMapper.deleteById(id);
    }

    @Override
    public RegionStorageDO getRegionStorage(Long id) {
        return regionStorageMapper.selectById(id);
    }

    private void validateRegionStorageExists(Long id) {
        if (regionStorageMapper.selectById(id) == null) {
            throw exception(REGION_STORAGE_NOT_EXISTS);
        }
    }

    private void deleteRegionStorageByRegionId(Long regionId) {
        regionStorageMapper.deleteByRegionId(regionId);
    }

}