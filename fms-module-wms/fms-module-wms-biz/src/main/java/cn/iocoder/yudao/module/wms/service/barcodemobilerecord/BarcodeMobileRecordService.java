package cn.iocoder.yudao.module.wms.service.barcodemobilerecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo.BarcodeMobileRecordPageReqVO;
import cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo.BarcodeMobileRecordSaveReqVO;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcodemobilerecord.BarcodeMobileRecordDO;

import javax.validation.Valid;

/**
 * 条码移动记录 Service 接口
 *
 * @author 超级管理员
 */
public interface BarcodeMobileRecordService {

    /**
     * 创建条码移动记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBarcodeMobileRecord(@Valid BarcodeMobileRecordSaveReqVO createReqVO);

    /**
     * 更新条码移动记录
     *
     * @param updateReqVO 更新信息
     */
    void updateBarcodeMobileRecord(@Valid BarcodeMobileRecordSaveReqVO updateReqVO);

    /**
     * 删除条码移动记录
     *
     * @param id 编号
     */
    void deleteBarcodeMobileRecord(Long id);

    /**
     * 获得条码移动记录
     *
     * @param id 编号
     * @return 条码移动记录
     */
    BarcodeMobileRecordDO getBarcodeMobileRecord(Long id);

    /**
     * 获得条码移动记录分页
     *
     * @param pageReqVO 分页查询
     * @return 条码移动记录分页
     */
    PageResult<BarcodeMobileRecordDO> getBarcodeMobileRecordPage(BarcodeMobileRecordPageReqVO pageReqVO);
}
