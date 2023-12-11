package cn.iocoder.yudao.module.wms.service.region;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.wms.controller.admin.region.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.region.RegionDO;
import cn.iocoder.yudao.module.wms.dal.mysql.region.RegionMapper;
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
 * {@link RegionServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(RegionServiceImpl.class)
public class RegionServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RegionServiceImpl regionService;

    @Resource
    private RegionMapper regionMapper;

    @Test
    public void testCreateRegion_success() {
        // 准备参数
        RegionSaveReqVO createReqVO = randomPojo(RegionSaveReqVO.class).setId(null);

        // 调用
        Long regionId = regionService.createRegion(createReqVO);
        // 断言
        assertNotNull(regionId);
        // 校验记录的属性是否正确
        RegionDO region = regionMapper.selectById(regionId);
        assertPojoEquals(createReqVO, region, "id");
    }

    @Test
    public void testUpdateRegion_success() {
        // mock 数据
        RegionDO dbRegion = randomPojo(RegionDO.class);
        regionMapper.insert(dbRegion);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RegionSaveReqVO updateReqVO = randomPojo(RegionSaveReqVO.class, o -> {
            o.setId(dbRegion.getId()); // 设置更新的 ID
        });

        // 调用
        regionService.updateRegion(updateReqVO);
        // 校验是否更新正确
        RegionDO region = regionMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, region);
    }

    @Test
    public void testUpdateRegion_notExists() {
        // 准备参数
        RegionSaveReqVO updateReqVO = randomPojo(RegionSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> regionService.updateRegion(updateReqVO), REGION_NOT_EXISTS);
    }

    @Test
    public void testDeleteRegion_success() {
        // mock 数据
        RegionDO dbRegion = randomPojo(RegionDO.class);
        regionMapper.insert(dbRegion);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRegion.getId();

        // 调用
        regionService.deleteRegion(id);
       // 校验数据不存在了
       assertNull(regionMapper.selectById(id));
    }

    @Test
    public void testDeleteRegion_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> regionService.deleteRegion(id), REGION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRegionPage() {
       // mock 数据
       RegionDO dbRegion = randomPojo(RegionDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setName(null);
           o.setPrefix(null);
       });
       regionMapper.insert(dbRegion);
       // 测试 createTime 不匹配
       regionMapper.insert(cloneIgnoreId(dbRegion, o -> o.setCreateTime(null)));
       // 测试 name 不匹配
       regionMapper.insert(cloneIgnoreId(dbRegion, o -> o.setName(null)));
       // 测试 prefix 不匹配
       regionMapper.insert(cloneIgnoreId(dbRegion, o -> o.setPrefix(null)));
       // 准备参数
       RegionPageReqVO reqVO = new RegionPageReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setName(null);
       reqVO.setPrefix(null);

       // 调用
       PageResult<RegionDO> pageResult = regionService.getRegionPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRegion, pageResult.getList().get(0));
    }

}