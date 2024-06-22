package com.kclgroup.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kclgroup.backend.pojo.entity.User;
import com.kclgroup.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
 
    @Autowired
    UserService userService;
 
    @GetMapping("/test")
    public User test(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username","admin");
        User user = userService.getOne(userQueryWrapper);
        return user;
    }
 
 
}