package com.twt.controller;

import cn.hutool.core.util.RandomUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @PostMapping("/photo")
    private String uploadPhoto(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        // 创建目录
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy");
        String datePath = dataFormat.format(new Date());
        File targetFile = new File("photo/"+datePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }

        // 存储路径示例：   /photo/2022/uuid-filename.file-suffix
        String filePath = "photo/"+datePath+"/"+ RandomUtil.randomString(4)+"-"+multipartFile.getOriginalFilename();

        // 开始构造流并且创建文件
        BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(filePath)));
        outputStream.write(multipartFile.getBytes());
        outputStream.flush();
        outputStream.close();
        return filePath;

    }

    @PostMapping("file")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception{
        // 创建目录
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy");
        String datePath = dataFormat.format(new Date());
        File targetFile = new File("file/"+datePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }

        // 存储路径     /file/2022/uuid-filename.file-suffix
        String filePath = "file/"+datePath+"/"+RandomUtil.randomString(4)+"-"+multipartFile.getOriginalFilename();

        // 开始构造流并且创建文件
        BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(filePath)));
        outputStream.write(multipartFile.getBytes());
        outputStream.flush();
        outputStream.close();
        return filePath;
    }

    @PostMapping("notice")
    public String uploadNoticeAttachment(@RequestParam("file") MultipartFile multipartFile) throws Exception{
        // 创建目录
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy");
        String datePath = dataFormat.format(new Date());
        File targetFile = new File("notice/"+datePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }

        // 存储路径     /notice/2022/uuid-filename.file-suffix
        String filePath = "notice/"+datePath+"/"+multipartFile.getOriginalFilename();

        // 开始构造流并且创建文件
        BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(filePath)));
        outputStream.write(multipartFile.getBytes());
        outputStream.flush();
        outputStream.close();
        return filePath;
    }
}
