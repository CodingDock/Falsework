package controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmm.bean.User;
import xmm.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("getUser")
    public User getUser(){

        return userService.getUserById(1);
    }

    @RequestMapping("hello")
    public User hello(){
        
        User u=new User();
        u.setId(11);
        u.setGender(0);
        u.setUserName("zhangsan");
        return u;
    }
    
}
