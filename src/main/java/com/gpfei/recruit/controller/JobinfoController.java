package com.gpfei.recruit.controller;


import com.gpfei.recruit.entity.Jobinfo;
import com.gpfei.recruit.service.JobinfoService;
import com.gpfei.recruit.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gpfei
 * @since 2021-05-10
 */
@RestController
@RequestMapping("/recruit/jobinfo")
public class JobinfoController {
    @Autowired
    JobinfoService jobinfoService;
    //1.获取所有的职位信息
    @GetMapping("findAll")
    public Msg findAllJob(){
        List<Jobinfo> list = jobinfoService.list(null);
        return Msg.success().add("items",list);
    }
}

