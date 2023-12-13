package cn.iocoder.yudao.module.wms.service.barcodemobilerecord;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.wms.controller.admin.barcodemobilerecord.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.barcodemobilerecord.BarcodeMobileRecordDO;
import cn.iocoder.yudao.module.wms.dal.mysql.barcodemobilerecord.BarcodeMobileRecordMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link BarcodeMobileRecordServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(BarcodeMobileRecordServiceImpl.class)
public class BarcodeMobileRecordServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BarcodeMobileRecordServiceImpl barcodeMobileRecordService;

    @Resource
    private BarcodeMobileRecordMapper barcodeMobileRecordMapper;

    @Test
    public void testCreateBarcodeMobileRecord_success() {
        // 准备参数
        BarcodeMobileRecordSaveReqVO createReqVO = randomPojo(BarcodeMobileRecordSaveReqVO.class).setId(null);

        // 调用
        Long barcodeMobileRecordId = barcodeMobileRecordService.createBarcodeMobileRecord(createReqVO);
        // 断言
        assertNotNull(barcodeMobileRecordId);
        // 校验记录的属性是否正确
        BarcodeMobileRecordDO barcodeMobileRecord = barcodeMobileRecordMapper.selectById(barcodeMobileRecordId);
        assertPojoEquals(createReqVO, barcodeMobileRecord, "id");
    }

    @Test
    public void testUpdateBarcodeMobileRecord_success() {
        // mock 数据
        BarcodeMobileRecordDO dbBarcodeMobileRecord = randomPojo(BarcodeMobileRecordDO.class);
        barcodeMobileRecordMapper.insert(dbBarcodeMobileRecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BarcodeMobileRecordSaveReqVO updateReqVO = randomPojo(BarcodeMobileRecordSaveReqVO.class, o -> {
            o.setId(dbBarcodeMobileRecord.getId()); // 设置更新的 ID
        });

        // 调用
        barcodeMobileRecordService.updateBarcodeMobileRecord(updateReqVO);
        // 校验是否更新正确
        BarcodeMobileRecordDO barcodeMobileRecord = barcodeMobileRecordMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, barcodeMobileRecord);
    }

    @Test
    public void testUpdateBarcodeMobileRecord_notExists() {
        // 准备参数
        BarcodeMobileRecordSaveReqVO updateReqVO = randomPojo(BarcodeMobileRecordSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> barcodeMobileRecordService.updateBarcodeMobileRecord(updateReqVO), BARCODE_MOBILE_RECORD_NOT_EXISTS);
    }

    @Test
    public void testDeleteBarcodeMobileRecord_success() {
        // mock 数据
        BarcodeMobileRecordDO dbBarcodeMobileRecord = randomPojo(BarcodeMobileRecordDO.class);
        barcodeMobileRecordMapper.insert(dbBarcodeMobileRecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBarcodeMobileRecord.getId();

        // 调用
        barcodeMobileRecordService.deleteBarcodeMobileRecord(id);
       // 校验数据不存在了
       assertNull(barcodeMobileRecordMapper.selectById(id));
    }

    @Test
    public void testDeleteBarcodeMobileRecord_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> barcodeMobileRecordService.deleteBarcodeMobileRecord(id), BARCODE_MOBILE_RECORD_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBarcodeMobileRecordPage() {
       // mock 数据
       BarcodeMobileRecordDO dbBarcodeMobileRecord = randomPojo(BarcodeMobileRecordDO.class, o -> { // 等会查询到
           o.setTrayNo(null);
           o.setSuNo(null);
           o.setBarcode(null);
           o.setNumber(null);
           o.setSpec(null);
           o.setBatch(null);
           o.setRegion(null);
           o.setStorage(null);
           o.setBarcodeStatus(null);
           o.setMoveType(null);
           o.setRemark(null);
           o.setRestTime(null);
           o.setCreateTime(null);
       });
       barcodeMobileRecordMapper.insert(dbBarcodeMobileRecord);
       // 测试 trayNo 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setTrayNo(null)));
       // 测试 suNo 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setSuNo(null)));
       // 测试 barcode 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setBarcode(null)));
       // 测试 number 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setNumber(null)));
       // 测试 spec 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setSpec(null)));
       // 测试 batch 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setBatch(null)));
       // 测试 region 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setRegion(null)));
       // 测试 storage 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setStorage(null)));
       // 测试 barcodeStatus 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setBarcodeStatus(null)));
       // 测试 moveType 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setMoveType(null)));
       // 测试 remark 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setRemark(null)));
       // 测试 restTime 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setRestTime(null)));
       // 测试 createTime 不匹配
       barcodeMobileRecordMapper.insert(cloneIgnoreId(dbBarcodeMobileRecord, o -> o.setCreateTime(null)));
       // 准备参数
       BarcodeMobileRecordPageReqVO reqVO = new BarcodeMobileRecordPageReqVO();
       reqVO.setTrayNo(null);
       reqVO.setSuNo(null);
       reqVO.setBarcode(null);
       reqVO.setNumber(null);
       reqVO.setSpec(null);
       reqVO.setBatch(null);
       reqVO.setRegion(null);
       reqVO.setStorage(null);
       reqVO.setBarcodeStatus(null);
       reqVO.setMoveType(null);
       reqVO.setRemark(null);
       reqVO.setRestTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<BarcodeMobileRecordDO> pageResult = barcodeMobileRecordService.getBarcodeMobileRecordPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBarcodeMobileRecord, pageResult.getList().get(0));
    }

}