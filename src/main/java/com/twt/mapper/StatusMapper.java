package com.twt.mapper;

import com.twt.entity.Status;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


public interface StatusMapper extends BaseMapper<Status> {

    void updateApplyStatus(int status);

    void updateConfirmStatus(int status);
}
