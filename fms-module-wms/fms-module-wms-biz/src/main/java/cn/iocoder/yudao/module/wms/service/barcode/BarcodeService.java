package cn.iocoder.yudao.module.wms.service.barcode;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.wms.controller.admin.barcode.vo.*;
import cn.iocoder.yudao.module.wms.controller.app.vo.BarcodeVO;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcode.BarcodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 条码 Service 接口
 *
 * @author 芋道源码
 */
public interface BarcodeService {

    /**
     * 创建条码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBarcode(@Valid BarcodeSaveReqVO createReqVO);

    /**
     * 更新条码
     *
     * @param updateReqVO 更新信息
     */
    void updateBarcode(@Valid BarcodeSaveReqVO updateReqVO);

    /**
     * 删除条码
     *
     * @param id 编号
     */
    void deleteBarcode(Long id);

    /**
     * 获得条码
     *
     * @param id 编号
     * @return 条码
     */
    BarcodeDO getBarcode(Long id);

    /**
     * 获得条码分页
     *
     * @param pageReqVO 分页查询
     * @return 条码分页
     */
    PageResult<BarcodeDO> getBarcodePage(BarcodePageReqVO pageReqVO);

    /**
     * 删除条码
     *
     * @param barcode
     */
    Integer deleteBarcodeByBarcode(String barcode);

    /**
     * 批量更新条码
     * @param barcodes
     */
    void batchUpdateBarcodes(List<String> barcodes,Map<String,Object> params);
}