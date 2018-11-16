package controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import xmm.bean.User;
import xmm.service.UserService;

@Component
public class UserXXX {
    
    
    @Reference
    private UserService userService;

    public User getUser(){

        return userService.getUserById(1);
    }
//    
    
    
}
