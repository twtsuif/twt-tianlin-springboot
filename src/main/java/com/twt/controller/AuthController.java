package com.twt.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.twt.dto.LoginParam;
import com.twt.dto.RegisterParam;
import com.twt.entity.User;
import com.twt.service.UserService;
import com.twt.utils.JwtUtils;
import com.twt.utils.Result;
import com.twt.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    @Resource
    UserService userService;

    @Resource
    JwtUtils jwtUtils;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginParam loginParam) {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, loginParam.getEmail()));
        if (user==null || !user.getPassword().equals(SecureUtil.md5(loginParam.getPassword()))){
            return Result.fail("用户不存在或密码不正确");
        }
        return Result.success(userToUserVO(user));
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterParam registerParam){
        // 查用邮箱是否被注册过
        User user = userService.getOne(new QueryWrapper<User>().eq("email", registerParam.getEmail()));
        if (user!=null){
            return Result.fail("邮箱已被注册 如有问题请联系管理员");
        }

        // 注册
        User registerAfterUser = userService.register(registerParam);
        return Result.success(userToUserVO(registerAfterUser));
    }

    private UserVO userToUserVO(User user){
        String jwt = jwtUtils.generateToken(user.getId());

        // 封装返回信息
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        userVO.setToken(jwt);
        userVO.setRole(user.getIsAdmin()==1?"admin":"user");

        return userVO;
    }
}

