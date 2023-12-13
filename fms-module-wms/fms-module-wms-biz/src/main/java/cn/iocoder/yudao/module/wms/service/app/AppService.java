package cn.iocoder.yudao.module.wms.service.app;

import cn.iocoder.yudao.module.wms.controller.app.vo.EmptyTrayWarehousingReqVO;

/**
 * @author jiangfeng
 * @date 2023/12/13
 */
public interface AppService {
    /**
     * 空托盘入库
     *
     * @param emptyTrayWarehousingReqVO
     */
    void emptyTrayWarehousing(EmptyTrayWarehousingReqVO emptyTrayWarehousingReqVO);

}
