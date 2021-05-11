package com.gpfei.recruit.controller;


import com.gpfei.recruit.service.OssService;
import com.gpfei.recruit.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    OssService ossService;
    //上传头像方法
    @PostMapping
    public Msg uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return Msg.success().add("url",url);
    }


}
