package cn.iocoder.yudao.module.wms.service.adapter;

import cn.iocoder.yudao.module.wms.controller.app.vo.groupTray.CheckTrayDataRespVO;

/**
 * @author jiangfeng
 * @date 2023/12/19
 */
public class CheckTrayDataAdapter {

    public static CheckTrayDataRespVO buildCheckTrayDataRespVO(String trayNo) {
        return new CheckTrayDataRespVO()
                .setCheckTray(Boolean.TRUE)
                .setCheckTrayResult(trayNo+",托盘有效");
    }
}
