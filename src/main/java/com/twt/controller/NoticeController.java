package com.twt.controller;


import cn.hutool.core.bean.BeanUtil;
import com.twt.dto.NoticeParam;
import com.twt.entity.Notice;
import com.twt.service.NoticeService;
import com.twt.utils.Result;
import com.twt.vo.HomeNoticeVO;
import com.twt.vo.NoticeVO;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 史熹东
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    @GetMapping
    public Result getHomeNotices(){
        List<HomeNoticeVO> list = noticeService.getHomeNotices();
        return Result.success(list);
    }

    @PostMapping
    @RequiresRoles("admin")
    public Result publishNotice(@RequestBody NoticeParam noticeParam){
        Notice notice = new Notice();
        BeanUtil.copyProperties(noticeParam,notice);
        notice.setCreatedAt(LocalDateTime.now());
        notice.setUpdatedAt(LocalDateTime.now());
        notice.setIsDeleted(0);
        noticeService.save(notice);
        return Result.success(null);
    }

    @GetMapping("/all")
    public Result getNoticesAll(){
        List<Notice> list = noticeService.list();
        return Result.success(copyList(list));
    }

    @GetMapping("/detail/{id}")
    public Result getNoticesAll(@PathVariable("id") Integer id){
        Notice notice = noticeService.getById(id);
        NoticeVO noticeVO = new NoticeVO();
        BeanUtil.copyProperties(notice,noticeVO);
        return Result.success(noticeVO);
    }

    @DeleteMapping("{id}")
    @RequiresRoles("admin")
    public Result deleteNotice(@PathVariable("id") Integer id){
        noticeService.removeById(id);
        return Result.success(null);
    }

    private List<NoticeVO> copyList(List<Notice> notices) {
        List<NoticeVO> noticeList = new ArrayList<>();
        for (Notice notice : notices) {
            NoticeVO noticeVO = new NoticeVO();
            BeanUtil.copyProperties(notice,noticeVO);
            noticeList.add(noticeVO);
        }
        return noticeList;
    }
}

