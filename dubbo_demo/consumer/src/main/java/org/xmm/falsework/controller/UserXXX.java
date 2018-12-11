package org.xmm.falsework.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.xmm.falsework.bean.User;
import org.xmm.falsework.service.UserService;

@Component
public class UserXXX {
    
    
    @Reference
    private UserService userService;

    public User getUser(){

        return userService.getUserById(1);
    }
//    
    
    
}
