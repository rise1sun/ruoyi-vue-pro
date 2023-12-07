package cn.iocoder.yudao.module.wms.service.tray;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.wms.controller.admin.tray.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.tray.TrayDO;
import cn.iocoder.yudao.module.wms.dal.mysql.tray.TrayMapper;
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
 * {@link TrayServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(TrayServiceImpl.class)
public class TrayServiceImplTest extends BaseDbUnitTest {

    @Resource
    private TrayServiceImpl trayService;

    @Resource
    private TrayMapper trayMapper;

    @Test
    public void testCreateTray_success() {
        // 准备参数
        TraySaveReqVO createReqVO = randomPojo(TraySaveReqVO.class).setId(null);

        // 调用
        Long trayId = trayService.createTray(createReqVO);
        // 断言
        assertNotNull(trayId);
        // 校验记录的属性是否正确
        TrayDO tray = trayMapper.selectById(trayId);
        assertPojoEquals(createReqVO, tray, "id");
    }

    @Test
    public void testUpdateTray_success() {
        // mock 数据
        TrayDO dbTray = randomPojo(TrayDO.class);
        trayMapper.insert(dbTray);// @Sql: 先插入出一条存在的数据
        // 准备参数
        TraySaveReqVO updateReqVO = randomPojo(TraySaveReqVO.class, o -> {
            o.setId(dbTray.getId()); // 设置更新的 ID
        });

        // 调用
        trayService.updateTray(updateReqVO);
        // 校验是否更新正确
        TrayDO tray = trayMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, tray);
    }

    @Test
    public void testUpdateTray_notExists() {
        // 准备参数
        TraySaveReqVO updateReqVO = randomPojo(TraySaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> trayService.updateTray(updateReqVO), TRAY_NOT_EXISTS);
    }

    @Test
    public void testDeleteTray_success() {
        // mock 数据
        TrayDO dbTray = randomPojo(TrayDO.class);
        trayMapper.insert(dbTray);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbTray.getId();

        // 调用
        trayService.deleteTray(id);
       // 校验数据不存在了
       assertNull(trayMapper.selectById(id));
    }

    @Test
    public void testDeleteTray_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> trayService.deleteTray(id), TRAY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetTrayPage() {
       // mock 数据
       TrayDO dbTray = randomPojo(TrayDO.class, o -> { // 等会查询到
           o.setTrayNo(null);
           o.setType(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       trayMapper.insert(dbTray);
       // 测试 trayNo 不匹配
       trayMapper.insert(cloneIgnoreId(dbTray, o -> o.setTrayNo(null)));
       // 测试 type 不匹配
       trayMapper.insert(cloneIgnoreId(dbTray, o -> o.setType(null)));
       // 测试 status 不匹配
       trayMapper.insert(cloneIgnoreId(dbTray, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       trayMapper.insert(cloneIgnoreId(dbTray, o -> o.setCreateTime(null)));
       // 准备参数
       TrayPageReqVO reqVO = new TrayPageReqVO();
       reqVO.setTrayNo(null);
       reqVO.setType(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<TrayDO> pageResult = trayService.getTrayPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbTray, pageResult.getList().get(0));
    }

}