package org.xmm.falsework.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xmm.falsework.xmm.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class UserServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    UserService userService;
    

    @Test
    public void getAll(){
        userService.getAll();
        
    }


}
