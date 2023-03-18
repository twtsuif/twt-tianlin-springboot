package com.twt.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twt.dto.RegisterParam;
import com.twt.entity.User;
import com.twt.mapper.UserMapper;
import com.twt.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User register(RegisterParam registerParam) {
        User user = new User();
        user.setName(registerParam.getName());
        user.setEmail(registerParam.getEmail());
        user.setPassword(SecureUtil.md5(registerParam.getPassword()));
        user.setCreatedAt(new Date());
        userMapper.insert(user);
        // 注册完之后是user角色
        userMapper.setUserRole(user.getId());
        return user;
    }

    @Override
    public String getUserRole(Integer id) {
        return userMapper.getUserRole(id);
    }
}
