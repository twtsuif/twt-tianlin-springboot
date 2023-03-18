package com.twt.mapper;

import com.twt.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User> {
    String getUserRole(Integer id);

    void setUserRole(Integer id);
}
