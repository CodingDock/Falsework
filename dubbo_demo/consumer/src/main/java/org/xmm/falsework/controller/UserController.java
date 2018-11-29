package org.xmm.falsework.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmm.falsework.util.CustomizedPropertyConfigurer;
import org.xmm.falsework.util.test;
import xmm.bean.User;
import xmm.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

//    @Reference
//    private UserService userService;

    @Autowired
    private test tst;
    

//    @RequestMapping("getUser")
//    public User getUser(){
//
//        return userService.getUserById(1);
//    }

    @RequestMapping("hello")
    public User hello(){
        
        User u=new User();
        u.setId(11);
        u.setGender(0);
        u.setUserName("zhangsan");

        System.out.println("当前环境："+ tst.getUrl());

        System.out.println(CustomizedPropertyConfigurer.getContextProperty("role"));
        System.out.println(CustomizedPropertyConfigurer.getContextProperty("xxx"));

        System.out.println(CustomizedPropertyConfigurer.getContextProperty("jdbc.slave.url"));
        return u;
    }
    
}
