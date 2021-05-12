package com.gpfei.recruit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gpfei.recruit.entity.Sign;
import com.gpfei.recruit.entity.Userinfo;
import com.gpfei.recruit.service.SignService;
import com.gpfei.recruit.utils.Msg;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("/recruit/sign")
public class SignController {

    @Autowired
    private SignService signService;
    //获取签到信息
    @GetMapping("getSignInfo")
    public Msg getSignInfoByUserName(String username){
        LambdaQueryWrapper<Sign> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Sign::getUsername,username);
        List<Sign> signs = signService.list(lambdaQueryWrapper);
        if (signs.size() != 0){
            System.out.println(Msg.success().add("data",signs.toArray()));
            return Msg.success().add("data",signs.toArray());
        }else {
            System.out.println("error");
            return Msg.fail().add("data","error");
        }
    }

    //签到进行签到
    @PostMapping("addSign")
    public Msg addSign(@RequestBody Sign sign){
        boolean save = signService.save(sign);
        return save?Msg.success():Msg.fail();
    }
    @PostMapping("updateSign")
    public Msg sign(String username, Integer intergal){
        LambdaUpdateWrapper<Sign> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(Sign::getUsername,username)
                .set(Sign::getIntergal,intergal)
                .set(Sign::getUpdateTime,new Date());
        boolean row = signService.update(null,lambdaUpdateWrapper);
        if (row == true){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }



}

