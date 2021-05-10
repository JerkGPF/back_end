package com.gpfei.recruit.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public Msg login(String username,String password){
        QueryWrapper<Login> lambdaQueryWrapper = new QueryWrapper<>();
        lambdaQueryWrapper.eq("username",username).and(login->login.eq("password",password));
        List<Login> logins = loginService.list(lambdaQueryWrapper);
        if (logins.size() != 0){
            return Msg.success().add("data",logins.toArray());
        }else {
            return Msg.fail().add("data","error");
        }
    }
}

