package com.twt.handler;

import com.twt.utils.Result;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕获权限异常
    @ExceptionHandler(UnauthorizedException.class)
    public Result doWithoutPermission(Exception e) {
        e.printStackTrace();
        return Result.fail(403, "没有权限访问", null);
    }

    // Token过期
    @ExceptionHandler(ExpiredCredentialsException.class)
    public Result doExpiredCredentialException(Exception e) {
        e.printStackTrace();
        return Result.fail(405, e.getMessage(), null);
    }

    // 捕获其余异常
    @ExceptionHandler(Exception.class)
    public Result doException(Exception e) {
        e.printStackTrace();
        return Result.fail("出现异常");
    }
}
