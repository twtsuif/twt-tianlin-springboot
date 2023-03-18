package com.twt.service.impl;

import com.twt.entity.Status;
import com.twt.mapper.StatusMapper;
import com.twt.service.StatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
@Service
public class StatusServiceImpl extends ServiceImpl<StatusMapper, Status> implements StatusService {

    @Resource
    StatusMapper statusMapper;

    @Override
    public void updateApplyStatus(int status) {
        statusMapper.updateApplyStatus(status);
    }

    @Override
    public void updateConfirmStatus(int status) {
        statusMapper.updateConfirmStatus(status);
    }
}
