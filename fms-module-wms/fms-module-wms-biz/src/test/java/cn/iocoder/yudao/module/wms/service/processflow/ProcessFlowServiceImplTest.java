package cn.iocoder.yudao.module.wms.service.processflow;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.wms.controller.admin.processflow.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.processflow.ProcessFlowDO;
import cn.iocoder.yudao.module.wms.dal.mysql.processflow.ProcessFlowMapper;
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
 * {@link ProcessFlowServiceImpl} 的单元测试类
 *
 * @author 超级管理员
 */
@Import(ProcessFlowServiceImpl.class)
public class ProcessFlowServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ProcessFlowServiceImpl processFlowService;

    @Resource
    private ProcessFlowMapper processFlowMapper;

    @Test
    public void testCreateProcessFlow_success() {
        // 准备参数
        ProcessFlowSaveReqVO createReqVO = randomPojo(ProcessFlowSaveReqVO.class).setId(null);

        // 调用
        Long processFlowId = processFlowService.createProcessFlow(createReqVO);
        // 断言
        assertNotNull(processFlowId);
        // 校验记录的属性是否正确
        ProcessFlowDO processFlow = processFlowMapper.selectById(processFlowId);
        assertPojoEquals(createReqVO, processFlow, "id");
    }

    @Test
    public void testUpdateProcessFlow_success() {
        // mock 数据
        ProcessFlowDO dbProcessFlow = randomPojo(ProcessFlowDO.class);
        processFlowMapper.insert(dbProcessFlow);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ProcessFlowSaveReqVO updateReqVO = randomPojo(ProcessFlowSaveReqVO.class, o -> {
            o.setId(dbProcessFlow.getId()); // 设置更新的 ID
        });

        // 调用
        processFlowService.updateProcessFlow(updateReqVO);
        // 校验是否更新正确
        ProcessFlowDO processFlow = processFlowMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, processFlow);
    }

    @Test
    public void testUpdateProcessFlow_notExists() {
        // 准备参数
        ProcessFlowSaveReqVO updateReqVO = randomPojo(ProcessFlowSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> processFlowService.updateProcessFlow(updateReqVO), PROCESS_FLOW_NOT_EXISTS);
    }

    @Test
    public void testDeleteProcessFlow_success() {
        // mock 数据
        ProcessFlowDO dbProcessFlow = randomPojo(ProcessFlowDO.class);
        processFlowMapper.insert(dbProcessFlow);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbProcessFlow.getId();

        // 调用
        processFlowService.deleteProcessFlow(id);
       // 校验数据不存在了
       assertNull(processFlowMapper.selectById(id));
    }

    @Test
    public void testDeleteProcessFlow_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> processFlowService.deleteProcessFlow(id), PROCESS_FLOW_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetProcessFlowPage() {
       // mock 数据
       ProcessFlowDO dbProcessFlow = randomPojo(ProcessFlowDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setStatus(null);
           o.setType(null);
           o.setDescription(null);
           o.setCreateTime(null);
       });
       processFlowMapper.insert(dbProcessFlow);
       // 测试 name 不匹配
       processFlowMapper.insert(cloneIgnoreId(dbProcessFlow, o -> o.setName(null)));
       // 测试 status 不匹配
       processFlowMapper.insert(cloneIgnoreId(dbProcessFlow, o -> o.setStatus(null)));
       // 测试 type 不匹配
       processFlowMapper.insert(cloneIgnoreId(dbProcessFlow, o -> o.setType(null)));
       // 测试 description 不匹配
       processFlowMapper.insert(cloneIgnoreId(dbProcessFlow, o -> o.setDescription(null)));
       // 测试 createTime 不匹配
       processFlowMapper.insert(cloneIgnoreId(dbProcessFlow, o -> o.setCreateTime(null)));
       // 准备参数
       ProcessFlowPageReqVO reqVO = new ProcessFlowPageReqVO();
       reqVO.setName(null);
       reqVO.setStatus(null);
       reqVO.setType(null);
       reqVO.setDescription(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<ProcessFlowDO> pageResult = processFlowService.getProcessFlowPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbProcessFlow, pageResult.getList().get(0));
    }

}