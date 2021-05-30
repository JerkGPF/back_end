package com.gpfei.recruit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gpfei.recruit.entity.Companyinfo;
import com.gpfei.recruit.entity.Jobinfo;
import com.gpfei.recruit.entity.Resume;
import com.gpfei.recruit.entity.Userinfo;
import com.gpfei.recruit.service.CompanyinfoService;
import com.gpfei.recruit.service.JobinfoService;
import com.gpfei.recruit.service.ResumeService;
import com.gpfei.recruit.service.UserinfoService;
import com.gpfei.recruit.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/recruit/resume")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    JobinfoService jobinfoService;

    @Autowired
    UserinfoService userinfoService;

    @Autowired
    CompanyinfoService companyinfoService;

    @PostMapping("insertResume")
    public Msg insertResume(@RequestBody Resume resume){
        boolean row = resumeService.save(resume);
        if (row){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    //通过用户id拿到工作信息
    @GetMapping("getbyusername")
    public Msg getbyusername(Integer userid){
        LambdaQueryWrapper<Resume> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Resume::getUserid,userid).and(msg->msg.eq(Resume::getDelivery,true));
        List<Resume> list = resumeService.list(lambdaQueryWrapper);
        List<Jobinfo> jobinfoList = new ArrayList<>();
        Jobinfo jobinfo;
        List<Companyinfo> companyinfoList = new ArrayList<>();
        Companyinfo companyinfo;
        if (list.size() != 0){
            for (int i = 0;i < list.size();i++) {
                LambdaQueryWrapper<Jobinfo> lambdaQueryWrapper1 = Wrappers.lambdaQuery();
                lambdaQueryWrapper1.eq(Jobinfo::getId, list.get(i).getJobid());
                jobinfo = jobinfoService.list(lambdaQueryWrapper1).get(0);
                System.out.println("tag" + i +jobinfoService.list(lambdaQueryWrapper1).get(0));
                jobinfoList.add(jobinfo);
            }
            for (int i = 0;i< list.size();i++){
                LambdaQueryWrapper<Companyinfo> lambdaQueryWrapper1 = Wrappers.lambdaQuery();
                lambdaQueryWrapper1.eq(Companyinfo::getId,list.get(i).getCompanyid());
                companyinfo = companyinfoService.list(lambdaQueryWrapper1).get(0);
                companyinfoList.add(companyinfo);

            }
            return Msg.success().add("resume",list.toArray()).add("jobinfo",jobinfoList).add("companyinfo",companyinfoList);
        }else {
            return Msg.fail();
        }
    }
    //通过用户id拿到工作信息
    @GetMapping("getbyusernameCollect")
    public Msg getbyusernameCollect(Integer userid){
        LambdaQueryWrapper<Resume> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Resume::getUserid,userid).and(msg->msg.eq(Resume::getCollect,true));
        List<Resume> list = resumeService.list(lambdaQueryWrapper);
        List<Jobinfo> jobinfoList = new ArrayList<>();
        Jobinfo jobinfo;
        List<Companyinfo> companyinfoList = new ArrayList<>();
        Companyinfo companyinfo;
        if (list.size() != 0){
            for (int i = 0;i < list.size();i++) {
                LambdaQueryWrapper<Jobinfo> lambdaQueryWrapper1 = Wrappers.lambdaQuery();
                lambdaQueryWrapper1.eq(Jobinfo::getId, list.get(i).getJobid());
                jobinfo = jobinfoService.list(lambdaQueryWrapper1).get(0);
                jobinfoList.add(jobinfo);
            }
            for (int i = 0;i< list.size();i++){
                LambdaQueryWrapper<Companyinfo> lambdaQueryWrapper1 = Wrappers.lambdaQuery();
                lambdaQueryWrapper1.eq(Companyinfo::getId,list.get(i).getCompanyid());
                companyinfo = companyinfoService.list(lambdaQueryWrapper1).get(0);
                companyinfoList.add(companyinfo);
            }
            return Msg.success().add("resume",list.toArray()).add("jobinfo",jobinfoList).add("companyinfo",companyinfoList);
        }else {
            return Msg.fail();
        }
    }

    //通过公司id拿到职位信息
    @GetMapping("getbycompany")
    public Msg getbycompany(Integer companyid){
        LambdaQueryWrapper<Resume> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Resume::getCompanyid,companyid).and(msg->msg.eq(Resume::getDelivery,true))
                .and(msg->msg.eq(Resume::getIsdelete,false));
        List<Resume> list = resumeService.list(lambdaQueryWrapper);
        List<Jobinfo> jobinfoList = new ArrayList<>();
        Jobinfo jobinfo;
        List<Userinfo> userinfoList = new ArrayList<>();
        Userinfo userinfo;
        if (list.size() != 0){
            for (int i = 0;i < list.size();i++) {
                LambdaQueryWrapper<Jobinfo> lambdaQueryWrapper1 = Wrappers.lambdaQuery();
                lambdaQueryWrapper1.eq(Jobinfo::getId, list.get(i).getJobid());
                jobinfo = jobinfoService.list(lambdaQueryWrapper1).get(0);
                jobinfoList.add(jobinfo);
            }
            for (int i = 0;i <list.size();i++){
                LambdaQueryWrapper<Userinfo> lambdaQueryWrapper1 = Wrappers.lambdaQuery();
                lambdaQueryWrapper1.eq(Userinfo::getId,list.get(i).getUserid());
                userinfo = userinfoService.list(lambdaQueryWrapper1).get(0);
                userinfoList.add(userinfo);
            }
            return Msg.success().add("resume",list.toArray()).add("jobinfo",jobinfoList).add("userinfo",userinfoList);
        }else {
            return Msg.fail();
        }
    }

    @PostMapping("updateResume")
    public Msg updateResume(Integer resId,boolean collect){
        LambdaUpdateWrapper<Resume> lambdaQueryWrapper = Wrappers.lambdaUpdate();
        lambdaQueryWrapper.eq(Resume::getId,resId)
                .set(Resume::getCollect,collect);
        boolean row = resumeService.update(lambdaQueryWrapper);
        if (row){
            System.out.println("成功");
            return Msg.success();
        }else {
            System.out.println("失败");
            return Msg.fail();
        }
    }



    @PostMapping("insert")
    public Msg insert(Integer userId,Integer jobId,boolean collect,boolean delievery) {
        LambdaQueryWrapper<Jobinfo> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Jobinfo::getId,jobId);
        List<Jobinfo> list = jobinfoService.list(lambdaQueryWrapper);
        List<Companyinfo> companyinfoList = new ArrayList<>();
        Companyinfo companyinfo;
        if (list.size() != 0) {//查出公司id
            for (int i = 0; i < list.size(); i++) {
                LambdaQueryWrapper<Companyinfo> lambdaQueryWrapper1 = Wrappers.lambdaQuery();
                lambdaQueryWrapper1.eq(Companyinfo::getUsername, list.get(i).getUsername());
                companyinfo = companyinfoService.list(lambdaQueryWrapper1).get(0);
                companyinfoList.add(companyinfo);
            }
        }
        Resume resume = new Resume();
        resume.setJobid(jobId);
        resume.setCompanyid(companyinfoList.get(0).getId());
        resume.setUserid(userId);
        resume.setCollect(collect);
        resume.setDelivery(delievery);
        resume.setIsdelete(false);
        boolean save = resumeService.save(resume);

        return save?Msg.success():Msg.fail();
    }
}

