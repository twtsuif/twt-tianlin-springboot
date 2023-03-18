package com.twt.controller;

import com.twt.service.DownloadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.net.URLEncoder;


@RequestMapping("/api/download")
//@RequiresRoles("admin")
@RestController
public class DownloadController {

    @Resource
    DownloadService downloadService;

    @ApiOperation("导出报名信息")
    @GetMapping("applyInfo")
    public String exportApplyInfo(HttpServletResponse response) {
        try {
            downloadService.exportApplyInfo(response);
            return "success";
        } catch (Exception e) {
            return "failure";
        }
    }

    @ApiOperation("导出确人信息")
    @GetMapping("confirmInfo")
    public String exportConfirmInfo(HttpServletResponse response) {
        try {
            downloadService.exportConfirmInfo(response);
            return "success";
        } catch (Exception e) {
            return "failure";
        }
    }

    @ApiOperation("导出学生作证材料")
    @GetMapping("studentProofMaterial")
    public String exportAttachment(HttpServletResponse response) {
        try {
            downloadService.exportAttachment(response);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @ApiOperation("下载申请人的资料")
    @GetMapping("/applier/file")
    public ResponseEntity<InputStreamResource> downloadApplierFile(@RequestParam String filePath) throws Exception {

        // 文件后缀
        String suffixName = filePath.substring(filePath.lastIndexOf("."));
        // 文件名
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));

        // 获取这个路径下的文件
        FileSystemResource fileSystemResource = new FileSystemResource(filePath);

        // 设置返回的头部
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + suffixName);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileSystemResource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(fileSystemResource.getInputStream()));
    }


    @ApiOperation("展示申请人的图片")
    @GetMapping("/applier/photo")
    public void showApplierPhoto(@RequestParam String filePath, HttpServletResponse response) throws Exception {
        // 文件后缀
        String suffixName = filePath.substring(filePath.lastIndexOf("."));
        // 服务器文件
        FileInputStream in = new FileInputStream(filePath);

        //设置文件ContentType类型
        response.setContentType("image/" + suffixName);
        ServletOutputStream out = response.getOutputStream();

        // 读取文件流
        int len = 0;
        byte[] buffer = new byte[1024 * 10];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();

    }

    @ApiOperation("获取公告下的附件")
    @GetMapping("/notice/attachment")
    public void getNoticeAttachment(@RequestParam String filePath, HttpServletResponse response) throws Exception {

//        文件后缀
        String suffixName = filePath.substring(filePath.lastIndexOf("."));
//        文件名
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));

//       文件
        FileInputStream in = new FileInputStream(filePath);


        //设置文件ContentType类型
        response.setContentType("notice/" + suffixName);

        response.setContentType("application/x-download");

        response.setCharacterEncoding("UTF-8");

        //设置文件头：最后一个参数是设置下载文件名
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8") + suffixName);
        ServletOutputStream out = response.getOutputStream();

        // 读取文件流
        int len = 0;
        byte[] buffer = new byte[1024 * 10];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();

    }


}
