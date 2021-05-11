package com.gpfei.recruit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gpfei.recruit.entity.Companyinfo;
import com.gpfei.recruit.entity.Userinfo;
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
@RequestMapping("/recruit/userinfo")
public class UserinfoController {

    @Autowired
    UserinfoService userinfoService;

    @PostMapping("setuserinfo")
    public Msg setuserinfo(@RequestBody Userinfo userinfo){

        boolean row = userinfoService.save(userinfo);
        if (row == true){
            System.out.println(">>>>>>"+userinfo);
            return Msg.success().add("data","ok");
        }else {
            System.out.println("<<<<<<<<<<"+userinfo);
            return Msg.fail().add("data","error");
        }
    }

    @PostMapping("updateuserinfo")
    public Msg updateUserinfo(@RequestBody Userinfo userinfo){
        LambdaUpdateWrapper<Userinfo> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(Userinfo::getUsername,userinfo.getUsername())
                .set(Userinfo::getPhone,userinfo.getPhone())
                .set(Userinfo::getHead,userinfo.getHead())
                .set(Userinfo::getSex, userinfo.getSex())
                .set(Userinfo::getQq,userinfo.getQq())
                .set(Userinfo::getName, userinfo.getName())
                .set(Userinfo::getBirthday,userinfo.getBirthday())
                .set(Userinfo::getEmail, userinfo.getEmail())
                .set(Userinfo::getProfile, userinfo.getProfile())
                .set(Userinfo::getExperience, userinfo.getExperience())
                .set(Userinfo::getNick, userinfo.getNick())
                .set(Userinfo::getMotto, userinfo.getMotto())
                .set(Userinfo::getFile, userinfo.getFile());
        boolean row = userinfoService.update(null,lambdaUpdateWrapper);
        if (row == true){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    //获取信息
    @GetMapping("getUserInfo")
    public Msg getCompanyInfoByUserName(String username){
        LambdaQueryWrapper<Userinfo> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Userinfo::getUsername,username);
        List<Userinfo> userinfos = userinfoService.list(lambdaQueryWrapper);
        if (userinfos.size() != 0){
            System.out.println(Msg.success().add("data",userinfos.toArray()));
            return Msg.success().add("data",userinfos.toArray());
        }else {
            System.out.println("error");
            return Msg.fail().add("data","error");
        }
    }

}

