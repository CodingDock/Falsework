package org.xmm.falsework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmm.falsework.util.Config;
import org.xmm.falsework.util.test;
import org.xmm.falsework.xmm.bean.User;
import org.xmm.falsework.xmm.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    static Logger logger= LoggerFactory.getLogger(UserController.class);


    @Autowired
    private Config config;
    
    @Autowired
    private test tst;
    
    @Autowired
    UserService userService;

    @RequestMapping("hello")
    public User hello(){
        System.out.println("------------controller");
        logger.error("controller ..........");
        
        User u=new User();
        u.setId(11);
        u.setGender(0);
        u.setUserName("zhangsan");
        System.out.println("当前环境："+ config.getRole()+"----"+config.getXxx());
        System.out.println("当前环境："+ tst.getUrl());
        
        u=userService.getUserById(5);
        logger.error("controller end .........."+u);

        return u;
    }
    
}
