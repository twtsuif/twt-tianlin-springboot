package com.twt.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twt.dto.ConfirmParam;
import com.twt.entity.Confirm;
import com.twt.service.ConfirmService;
import com.twt.shiro.AccountProfile;
import com.twt.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 史熹东
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/api/confirm")
public class ConfirmController {

    @Autowired
    ConfirmService confirmService;

    @PostMapping
    @RequiresRoles("user")
    public Result commitConfirm(@RequestBody ConfirmParam confirmParam){

        // 生成一个confirm对象 并将属性拷贝
        Confirm confirm = new Confirm();
        BeanUtil.copyProperties(confirmParam,confirm);

        if (confirmParam.getIsJoin()==1){
            String temp = StrUtil.strip(Arrays.toString(confirmParam.getBedNeed()),"[");
            String replace = temp.replace(",", "-");
            confirm.setBedNeed(StrUtil.strip(replace,"]"));
            confirm.setUpdatedAt(LocalDateTime.now());
            confirm.setYear(LocalDateTime.now().getYear());
        }

        AccountProfile profile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        confirmParam.setUid(profile.getId());

        // 从数据库里查询这个uid
        Confirm oldConfirm = confirmService.getOne(new LambdaQueryWrapper<Confirm>().eq(Confirm::getUid, confirmParam.getUid()));
        if (oldConfirm!=null){
            confirmService.update(confirm,new LambdaQueryWrapper<Confirm>().eq(Confirm::getUid,confirm.getUid()));
        }else{
            confirm.setCreatedAt(LocalDateTime.now());
            confirmService.save(confirm);
        }

        return Result.success(null);
    }

}

