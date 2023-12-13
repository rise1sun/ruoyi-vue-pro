package cn.iocoder.yudao.module.wms.service.region;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.wms.controller.admin.region.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionDO;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionStorageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 库位管理 Service 接口
 *
 * @author 超级管理员
 */
public interface RegionService {

    /**
     * 创建库位管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRegion(@Valid RegionSaveReqVO createReqVO);

    /**
     * 更新库位管理
     *
     * @param updateReqVO 更新信息
     */
    void updateRegion(@Valid RegionSaveReqVO updateReqVO);

    /**
     * 删除库位管理
     *
     * @param id 编号
     */
    void deleteRegion(Long id);

    /**
     * 获得库位管理
     *
     * @param id 编号
     * @return 库位管理
     */
    RegionDO getRegion(Long id);

    /**
     * 获得库位管理分页
     *
     * @param pageReqVO 分页查询
     * @return 库位管理分页
     */
    PageResult<RegionDO> getRegionPage(RegionPageReqVO pageReqVO);

    // ==================== 子表（库位） ====================

    /**
     * 获得库位分页
     *
     * @param pageReqVO 分页查询
     * @param regionId 区域ID
     * @return 库位分页
     */
    PageResult<RegionStorageDO> getRegionStoragePage(PageParam pageReqVO, Long regionId);

    /**
     * 创建库位
     *
     * @param regionStorage 创建信息
     * @return 编号
     */
    Long createRegionStorage(@Valid RegionStorageDO regionStorage);

    /**
     * 更新库位
     *
     * @param regionStorage 更新信息
     */
    void updateRegionStorage(@Valid RegionStorageDO regionStorage);

    /**
     * 删除库位
     *
     * @param id 编号
     */
    void deleteRegionStorage(Long id);

	/**
	 * 获得库位
	 *
	 * @param id 编号
     * @return 库位
	 */
    RegionStorageDO getRegionStorage(Long id);

    /**
     * 根据库位编号查询库位
     *
     * @param storage 库位编号
     * @return 库位
     */
    RegionStorageDO slectByStorage(String storage);

    /**
     * 更新库位
     *
     * @param regionStorageDO
     */
    void updateRegionStorageStatusByCode(RegionStorageDO regionStorageDO);
}