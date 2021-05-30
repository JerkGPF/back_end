package com.gpfei.recruit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gpfei.recruit.entity.Jobinfo;
import com.gpfei.recruit.entity.Resume;
import com.gpfei.recruit.entity.Userinfo;
import com.gpfei.recruit.service.JobinfoService;
import com.gpfei.recruit.service.ResumeService;
import com.gpfei.recruit.utils.Msg;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ResumeService resumeService;
    //1.根据类别获取所有的职位信息
    @GetMapping("findAll")
    public Msg findAllJob(String kind){
        LambdaQueryWrapper<Jobinfo> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Jobinfo::getKind,kind).and(msg->msg.eq(Jobinfo::getIsdeleted,false));
        List<Jobinfo> list = jobinfoService.list(lambdaQueryWrapper);
        return Msg.success().add("data",list);
    }
    //2.添加职位
    @PostMapping("addJob")
    public Msg addJob(@RequestBody Jobinfo jobinfo){
        boolean save = jobinfoService.save(jobinfo);
        return save?Msg.success():Msg.fail();
    }
    //3。修改职位信息
    @PostMapping("updateJob")
    public Msg updateJob(@RequestBody Jobinfo jobinfo){
        LambdaUpdateWrapper<Jobinfo> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(Jobinfo::getUsername,jobinfo.getUsername())
                .set(Jobinfo::getCompanyname,jobinfo.getCompanyname())
                .set(Jobinfo::getCount,jobinfo.getCount())
                .set(Jobinfo::getDetail,jobinfo.getDetail())
                .set(Jobinfo::getPlace,jobinfo.getPlace())
                .set(Jobinfo::getSalary,jobinfo.getSalary())
                .set(Jobinfo::getTitle,jobinfo.getTitle())
                .set(Jobinfo::getKind,jobinfo.getKind());
        boolean row = jobinfoService.update(null,lambdaUpdateWrapper);
        return row?Msg.success():Msg.fail();
    }
    //先查找到
    @GetMapping("getJobById")
    public Msg getJobById(Integer id){
        LambdaQueryWrapper<Jobinfo> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Jobinfo::getId,id).eq(Jobinfo::getIsdeleted,false);
        List<Jobinfo> list = jobinfoService.list(lambdaQueryWrapper);
        //Jobinfo jobinfo = jobinfoService.getById(id);
        return Msg.success().add("data",list);
    }
    //修改职位信息
    @PostMapping("updateJobById")
    public Msg updateJobById(@RequestBody Jobinfo jobinfo){
        boolean b = jobinfoService.updateById(jobinfo);
        return b?Msg.success():Msg.fail();
    }
    //4.根据类别获取职位信息
    @GetMapping("getJob")
    public Msg getJob(String username,String kind){
        LambdaQueryWrapper<Jobinfo> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Jobinfo::getUsername,username).and(msg->msg.eq(Jobinfo::getKind,kind))
                .and(msg->msg.eq(Jobinfo::getIsdeleted,false));
        List<Jobinfo> jobinfos = jobinfoService.list(lambdaQueryWrapper);
        if (jobinfos.size() != 0){
            System.out.println(Msg.success().add("data",jobinfos.toArray()));
            return Msg.success().add("data",jobinfos.toArray());
        }else {
            System.out.println("error");
            return Msg.fail().add("data","error");
        }
    }
    //5.删除职位信息
    //修改职位信息
    @PostMapping("deleteJobById")
    public Msg deleteJobById(@RequestBody Jobinfo jobinfo){
        boolean b = jobinfoService.updateById(jobinfo);
        //boolean b = jobinfoService.removeById(jobinfo);
        if (b){
            LambdaQueryWrapper<Resume> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(Resume::getJobid,jobinfo.getId());
            List<Resume> list = resumeService.list(lambdaQueryWrapper);
            if (list.size() != 0){
                LambdaUpdateWrapper<Resume> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
                lambdaUpdateWrapper.eq(Resume::getJobid,jobinfo.getId())
                        .set(Resume::getIsdelete,true);
                resumeService.update(lambdaUpdateWrapper);
            }
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}

