package org.xmm.falsework.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import xmm.bean.User;
import xmm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Override
    public User getUserById(int id) {
        
        User u=new User();
        u.setUserName("李四");
        u.setId(111);
        u.setGender(1);
        return u;
    }
}
