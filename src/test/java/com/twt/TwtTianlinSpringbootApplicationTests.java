package com.twt;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.twt.entity.User;
import com.twt.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TwtTianlinSpringbootApplicationTests {
    @Resource
    UserService userService;

    @Test
    void contextLoads() {}

    // 测试结果 找不到记录会返回null
    @Test
    void testGetOneFunction(){
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, "123@qq.com"));
        System.out.println(user);
    }

}
