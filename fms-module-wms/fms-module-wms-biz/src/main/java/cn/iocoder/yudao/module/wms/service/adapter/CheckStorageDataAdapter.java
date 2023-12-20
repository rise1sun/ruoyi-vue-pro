package cn.iocoder.yudao.module.wms.service.adapter;

import cn.iocoder.yudao.module.wms.controller.app.vo.groupTray.CheckStorageDataRespVO;

/**
 * @author jiangfeng
 * @date 2023/12/18
 */
public class CheckStorageDataAdapter {
    public static CheckStorageDataRespVO buildCheckStorageDataRepVO(String storage) {
        return new CheckStorageDataRespVO()
                .setCheckStorage(Boolean.TRUE)
                .setCheckStorageResult(storage+",库位有效");
    }
}
