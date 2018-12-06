package org.xmm.falsework.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xmm.bean.User;
import xmm.service.UserService;

@org.springframework.stereotype.Service
@Service
public class UserServiceImpl implements UserService {
    static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User getUserById(int id) {
        logger.error("coming....");
        User u=new User();
        u.setUserName("李四");
        u.setId(111);
        u.setGender(1);

        logger.error("ops over ...."+u);

        return u;
    }
}
