package cn.iocoder.yudao.module.wms.service.secureinvokerecord;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.wms.controller.admin.secureinvokerecord.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.secureinvokerecord.SecureInvokeRecordDO;
import cn.iocoder.yudao.module.wms.dal.mysql.secureinvokerecord.SecureInvokeRecordMapper;
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
 * {@link SecureInvokeRecordServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(SecureInvokeRecordServiceImpl.class)
public class SecureInvokeRecordServiceImplTest extends BaseDbUnitTest {

    @Resource
    private SecureInvokeRecordServiceImpl secureInvokeRecordService;

    @Resource
    private SecureInvokeRecordMapper secureInvokeRecordMapper;

    @Test
    public void testCreateSecureInvokeRecord_success() {
        // 准备参数
        SecureInvokeRecordSaveReqVO createReqVO = randomPojo(SecureInvokeRecordSaveReqVO.class).setId(null);

        // 调用
        Long secureInvokeRecordId = secureInvokeRecordService.createSecureInvokeRecord(createReqVO);
        // 断言
        assertNotNull(secureInvokeRecordId);
        // 校验记录的属性是否正确
        SecureInvokeRecordDO secureInvokeRecord = secureInvokeRecordMapper.selectById(secureInvokeRecordId);
        assertPojoEquals(createReqVO, secureInvokeRecord, "id");
    }

    @Test
    public void testUpdateSecureInvokeRecord_success() {
        // mock 数据
        SecureInvokeRecordDO dbSecureInvokeRecord = randomPojo(SecureInvokeRecordDO.class);
        secureInvokeRecordMapper.insert(dbSecureInvokeRecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        SecureInvokeRecordSaveReqVO updateReqVO = randomPojo(SecureInvokeRecordSaveReqVO.class, o -> {
            o.setId(dbSecureInvokeRecord.getId()); // 设置更新的 ID
        });

        // 调用
        secureInvokeRecordService.updateSecureInvokeRecord(updateReqVO);
        // 校验是否更新正确
        SecureInvokeRecordDO secureInvokeRecord = secureInvokeRecordMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, secureInvokeRecord);
    }

    @Test
    public void testUpdateSecureInvokeRecord_notExists() {
        // 准备参数
        SecureInvokeRecordSaveReqVO updateReqVO = randomPojo(SecureInvokeRecordSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> secureInvokeRecordService.updateSecureInvokeRecord(updateReqVO), SECURE_INVOKE_RECORD_NOT_EXISTS);
    }

    @Test
    public void testDeleteSecureInvokeRecord_success() {
        // mock 数据
        SecureInvokeRecordDO dbSecureInvokeRecord = randomPojo(SecureInvokeRecordDO.class);
        secureInvokeRecordMapper.insert(dbSecureInvokeRecord);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbSecureInvokeRecord.getId();

        // 调用
        secureInvokeRecordService.deleteSecureInvokeRecord(id);
       // 校验数据不存在了
       assertNull(secureInvokeRecordMapper.selectById(id));
    }

    @Test
    public void testDeleteSecureInvokeRecord_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> secureInvokeRecordService.deleteSecureInvokeRecord(id), SECURE_INVOKE_RECORD_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetSecureInvokeRecordPage() {
       // mock 数据
       SecureInvokeRecordDO dbSecureInvokeRecord = randomPojo(SecureInvokeRecordDO.class, o -> { // 等会查询到
           o.setSecureInvokeJson(null);
           o.setNextRetryTime(null);
           o.setRetryTimes(null);
           o.setMaxRetryTimes(null);
           o.setFailReason(null);
           o.setCreateTime(null);
           o.setStatus(null);
       });
       secureInvokeRecordMapper.insert(dbSecureInvokeRecord);
       // 测试 secureInvokeJson 不匹配
       secureInvokeRecordMapper.insert(cloneIgnoreId(dbSecureInvokeRecord, o -> o.setSecureInvokeJson(null)));
       // 测试 nextRetryTime 不匹配
       secureInvokeRecordMapper.insert(cloneIgnoreId(dbSecureInvokeRecord, o -> o.setNextRetryTime(null)));
       // 测试 retryTimes 不匹配
       secureInvokeRecordMapper.insert(cloneIgnoreId(dbSecureInvokeRecord, o -> o.setRetryTimes(null)));
       // 测试 maxRetryTimes 不匹配
       secureInvokeRecordMapper.insert(cloneIgnoreId(dbSecureInvokeRecord, o -> o.setMaxRetryTimes(null)));
       // 测试 failReason 不匹配
       secureInvokeRecordMapper.insert(cloneIgnoreId(dbSecureInvokeRecord, o -> o.setFailReason(null)));
       // 测试 createTime 不匹配
       secureInvokeRecordMapper.insert(cloneIgnoreId(dbSecureInvokeRecord, o -> o.setCreateTime(null)));
       // 测试 status 不匹配
       secureInvokeRecordMapper.insert(cloneIgnoreId(dbSecureInvokeRecord, o -> o.setStatus(null)));
       // 准备参数
       SecureInvokeRecordPageReqVO reqVO = new SecureInvokeRecordPageReqVO();
       reqVO.setSecureInvokeJson(null);
       reqVO.setNextRetryTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setRetryTimes(null);
       reqVO.setMaxRetryTimes(null);
       reqVO.setFailReason(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setStatus(null);

       // 调用
       PageResult<SecureInvokeRecordDO> pageResult = secureInvokeRecordService.getSecureInvokeRecordPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbSecureInvokeRecord, pageResult.getList().get(0));
    }

}