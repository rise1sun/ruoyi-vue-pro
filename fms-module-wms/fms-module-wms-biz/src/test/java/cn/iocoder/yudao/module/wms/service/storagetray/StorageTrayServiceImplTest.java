package cn.iocoder.yudao.module.wms.service.storagetray;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.wms.controller.admin.storagetray.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.storagetray.StorageTrayDO;
import cn.iocoder.yudao.module.wms.dal.mysql.storagetray.StorageTrayMapper;
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
 * {@link StorageTrayServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(StorageTrayServiceImpl.class)
public class StorageTrayServiceImplTest extends BaseDbUnitTest {

    @Resource
    private StorageTrayServiceImpl storageTrayService;

    @Resource
    private StorageTrayMapper storageTrayMapper;

    @Test
    public void testCreateStorageTray_success() {
        // 准备参数
        StorageTraySaveReqVO createReqVO = randomPojo(StorageTraySaveReqVO.class).setId(null);

        // 调用
        Long storageTrayId = storageTrayService.createStorageTray(createReqVO);
        // 断言
        assertNotNull(storageTrayId);
        // 校验记录的属性是否正确
        StorageTrayDO storageTray = storageTrayMapper.selectById(storageTrayId);
        assertPojoEquals(createReqVO, storageTray, "id");
    }

    @Test
    public void testUpdateStorageTray_success() {
        // mock 数据
        StorageTrayDO dbStorageTray = randomPojo(StorageTrayDO.class);
        storageTrayMapper.insert(dbStorageTray);// @Sql: 先插入出一条存在的数据
        // 准备参数
        StorageTraySaveReqVO updateReqVO = randomPojo(StorageTraySaveReqVO.class, o -> {
            o.setId(dbStorageTray.getId()); // 设置更新的 ID
        });

        // 调用
        storageTrayService.updateStorageTray(updateReqVO);
        // 校验是否更新正确
        StorageTrayDO storageTray = storageTrayMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, storageTray);
    }

    @Test
    public void testUpdateStorageTray_notExists() {
        // 准备参数
        StorageTraySaveReqVO updateReqVO = randomPojo(StorageTraySaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> storageTrayService.updateStorageTray(updateReqVO), STORAGE_TRAY_NOT_EXISTS);
    }

    @Test
    public void testDeleteStorageTray_success() {
        // mock 数据
        StorageTrayDO dbStorageTray = randomPojo(StorageTrayDO.class);
        storageTrayMapper.insert(dbStorageTray);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbStorageTray.getId();

        // 调用
        storageTrayService.deleteStorageTray(id);
       // 校验数据不存在了
       assertNull(storageTrayMapper.selectById(id));
    }

    @Test
    public void testDeleteStorageTray_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> storageTrayService.deleteStorageTray(id), STORAGE_TRAY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetStorageTrayPage() {
       // mock 数据
       StorageTrayDO dbStorageTray = randomPojo(StorageTrayDO.class, o -> { // 等会查询到
           o.setStorageId(null);
           o.setTrayId(null);
           o.setCreateTime(null);
       });
       storageTrayMapper.insert(dbStorageTray);
       // 测试 storageId 不匹配
       storageTrayMapper.insert(cloneIgnoreId(dbStorageTray, o -> o.setStorageId(null)));
       // 测试 trayId 不匹配
       storageTrayMapper.insert(cloneIgnoreId(dbStorageTray, o -> o.setTrayId(null)));
       // 测试 createTime 不匹配
       storageTrayMapper.insert(cloneIgnoreId(dbStorageTray, o -> o.setCreateTime(null)));
       // 准备参数
       StorageTrayPageReqVO reqVO = new StorageTrayPageReqVO();
       reqVO.setStorageId(null);
       reqVO.setTrayId(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<StorageTrayDO> pageResult = storageTrayService.getStorageTrayPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbStorageTray, pageResult.getList().get(0));
    }

}