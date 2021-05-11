package com.gpfei.recruit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gpfei.recruit.entity.Companyinfo;
import com.gpfei.recruit.entity.Login;
import com.gpfei.recruit.entity.Userinfo;
import com.gpfei.recruit.service.CompanyinfoService;
import com.gpfei.recruit.service.UserinfoService;
import com.gpfei.recruit.utils.Msg;
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
@RequestMapping("/recruit/companyinfo")
public class CompanyinfoController {

    @Autowired
    CompanyinfoService companyinfoService;

    //插入信息
    @PostMapping("setcompanyinfo")
    public Msg setCompanyInfo(@RequestBody Companyinfo companyinfo){

        boolean row = companyinfoService.save(companyinfo);
        if (row == true){
            return Msg.success().add("data","ok");
        }else {
            return Msg.fail().add("data","error");
        }
    }
    //更新信息
    @PostMapping("updatcompanyinfo")
    public Msg updateCompanyInfo(@RequestBody Companyinfo companyinfo){
        LambdaUpdateWrapper<Companyinfo> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(Companyinfo::getUsername,companyinfo.getUsername())
                .set(Companyinfo::getPhone,companyinfo.getPhone())
                .set(Companyinfo::getBirthday, companyinfo.getBirthday())
                .set(Companyinfo::getName,companyinfo.getName())
                .set(Companyinfo::getFree, companyinfo.getFree())
                .set(Companyinfo::getEmail,companyinfo.getEmail())
                .set(Companyinfo::getProfile,companyinfo.getProfile());
        boolean row = companyinfoService.update(null,lambdaUpdateWrapper);
        if (row == true){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
    //获取信息
    @GetMapping("getCompanyInfo")
    public Msg getCompanyInfoByUserName(String username){
        LambdaQueryWrapper<Companyinfo> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Companyinfo::getUsername,username);
        List<Companyinfo> companyinfos = companyinfoService.list(lambdaQueryWrapper);
        if (companyinfos.size() != 0){
            System.out.println(Msg.success().add("data",companyinfos.toArray()));
            return Msg.success().add("data",companyinfos.toArray());
        }else {
            System.out.println("error");
            return Msg.fail().add("data","error");
        }
    }

}

