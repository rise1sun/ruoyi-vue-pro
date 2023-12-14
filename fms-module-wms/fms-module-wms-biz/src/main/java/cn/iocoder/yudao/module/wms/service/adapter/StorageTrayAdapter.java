package cn.iocoder.yudao.module.wms.service.adapter;

import cn.iocoder.yudao.module.wms.controller.admin.storagetray.vo.StorageTraySaveReqVO;

/**
 * @author jiangfeng
 * @date 2023/12/14
 */
public class StorageTrayAdapter {
    public static StorageTraySaveReqVO buildStorageTrayDO(Long storageId, Long trayId) {
        return new StorageTraySaveReqVO()
                .setStorageId(storageId)
                .setTrayId(trayId);
    }
}
