package org.xmm.falsework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmm.falsework.util.Config;
import org.xmm.falsework.util.test;
import xmm.bean.User;

@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private Config config;
    
    @Autowired
    private test tst;

    @RequestMapping("hello")
    public User hello(){
        
        User u=new User();
        u.setId(11);
        u.setGender(0);
        u.setUserName("zhangsan");
        System.out.println("当前环境："+ config.getRole()+"----"+config.getXxx());
        System.out.println("当前环境："+ tst.getUrl());
        return u;
    }
    
}
