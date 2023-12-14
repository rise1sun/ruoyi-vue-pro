package cn.iocoder.yudao.module.wms.service.adapter;

import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionStorageDO;

/**
 * @author jiangfeng
 * @date 2023/12/14
 */
public class RegionStorageAdapter {

    public static RegionStorageDO buildRegionStorageDO(String storage, Integer status) {
        return new RegionStorageDO().setCode(storage).setStatus(status);
    }
}
