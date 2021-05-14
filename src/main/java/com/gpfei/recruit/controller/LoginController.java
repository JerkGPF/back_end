package com.gpfei.recruit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gpfei.recruit.entity.Login;
import com.gpfei.recruit.entity.Userinfo;
import com.gpfei.recruit.service.LoginService;
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
@RequestMapping("/recruit/user")
public class LoginController {

    @Autowired
    LoginService loginService;
    //注册
    @PostMapping("registuser")
    public Msg registuser(@RequestBody Login login) {
        LambdaQueryWrapper<Login> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Login::getUsername,login.getUsername());
        List<Login> logins = loginService.list(queryWrapper);
        if (logins.size() != 0){
            return Msg.fail().add("data","用户已存在");
        }else {
            boolean row = loginService.save(login);
            return Msg.success().add("data",row == true ? "ok" : "error");
        }
    }
    //登录
    @PostMapping("login")
    public Msg login(@RequestBody Login login){
        System.out.println("username:" + login.getUsername() + "," + "password" + login.getPassword());
        QueryWrapper<Login> lambdaQueryWrapper = new QueryWrapper<>();
        lambdaQueryWrapper.eq("username",login.getUsername()).and(msg->msg.eq("password",login.getPassword()));
        List<Login> logins = loginService.list(lambdaQueryWrapper);
        if (logins.size() != 0){
            System.out.println(Msg.success().add("data",logins.toArray()));
            return Msg.success().add("data",logins.toArray());
        }else {
            System.out.println("error");
            return Msg.fail().add("data","error");
        }
    }
    //修改密码
    @PostMapping("modifyPass")
    public Msg modify(@RequestBody Login login){
        LambdaUpdateWrapper<Login> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(Login::getUsername,login.getUsername())
                .set(Login::getPassword,login.getPassword());
        boolean row = loginService.update(null,lambdaUpdateWrapper);
        if (row == true){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @GetMapping("getIsHr")
    public Msg getIsHr(String username){
        LambdaQueryWrapper<Login> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Login::getUsername,username);
        List<Login> list = loginService.list(lambdaQueryWrapper);
        return Msg.success().add("data",list.toArray());
    }
}

