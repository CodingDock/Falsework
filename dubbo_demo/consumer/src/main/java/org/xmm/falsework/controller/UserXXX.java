package org.xmm.falsework.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import org.xmm.falsework.xmm.bean.User;
import org.xmm.falsework.xmm.service.UserService;

@Component
public class UserXXX {
    
    
    @Reference
    private UserService userService;

    public User getUser(){

        return userService.getUserById(1);
    }
//    
    
    
}
