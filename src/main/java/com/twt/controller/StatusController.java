package com.twt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twt.entity.Status;
import com.twt.service.StatusService;
import com.twt.utils.Result;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "系统状态接口")
@RestController
@RequestMapping("/api/status")

public class StatusController {

    @Resource
    StatusService statusService;

    @GetMapping
    public Result getStatus(){
        Status status = statusService.getOne(new LambdaQueryWrapper<>());
        return Result.success(status);
    }

    @PutMapping("openApplySystem")
    @RequiresRoles("admin")
    public Result openApplySystem(){
        statusService.updateApplyStatus(1);
        return Result.success(null);
    }

    @PutMapping("closeApplySystem")
    @RequiresRoles("admin")
    public Result closeApplySystem(){
        statusService.updateApplyStatus(0);
        return Result.success(null);
    }

    @PutMapping("openConfirmSystem")
    @RequiresRoles("admin")
    public Result openConfirmSystem(){
        statusService.updateConfirmStatus(1);
        return Result.success(null);
    }

    @PutMapping("closeConfirmSystem")
    @RequiresRoles("admin")
    public Result closeConfirmSystem(){
        statusService.updateConfirmStatus(0);
        return Result.success(null);
    }
}

