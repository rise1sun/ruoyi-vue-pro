package cn.iocoder.yudao.module.wms.service.tray;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.wms.controller.admin.tray.vo.*;
import cn.iocoder.yudao.module.wms.dal.dataobject.tray.TrayDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.wms.dal.mysql.tray.TrayMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.wms.enums.ErrorCodeConstants.*;

/**
 * 托盘 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class TrayServiceImpl implements TrayService {

    @Resource
    private TrayMapper trayMapper;

    @Override
    public Long createTray(TraySaveReqVO createReqVO) {
        // 插入
        TrayDO tray = BeanUtils.toBean(createReqVO, TrayDO.class);
        trayMapper.insert(tray);
        // 返回
        return tray.getId();
    }

    @Override
    public void updateTray(TraySaveReqVO updateReqVO) {
        // 校验存在
        validateTrayExists(updateReqVO.getId());
        // 更新
        TrayDO updateObj = BeanUtils.toBean(updateReqVO, TrayDO.class);
        trayMapper.updateById(updateObj);
    }

    @Override
    public void deleteTray(Long id) {
        // 校验存在
        validateTrayExists(id);
        // 删除
        trayMapper.deleteById(id);
    }

    private void validateTrayExists(Long id) {
        if (trayMapper.selectById(id) == null) {
            throw exception(TRAY_NOT_EXISTS);
        }
    }

    @Override
    public TrayDO getTray(Long id) {
        return trayMapper.selectById(id);
    }

    @Override
    public PageResult<TrayDO> getTrayPage(TrayPageReqVO pageReqVO) {
        return trayMapper.selectPage(pageReqVO);
    }

    @Override
    public TrayDO selectByTrayNo(String trayNo) {
        return trayMapper.selectOne(trayNo);
    }

    @Override
    public void updateTrayStatusByTrayNo(String tray, Integer status) {
        trayMapper.updateTrayStatusByTrayNo(tray,status);
    }

}