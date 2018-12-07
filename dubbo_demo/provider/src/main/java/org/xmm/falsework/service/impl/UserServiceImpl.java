package org.xmm.falsework.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xmm.falsework.dao.UserMapper;
import org.xmm.falsework.entity.User;
import xmm.service.UserService;
import xmm.util.CustomizedPropertyConfigurer;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
@Service
public class UserServiceImpl implements UserService {
    static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public xmm.bean.User getUserById(int id) {
        logger.error("coming....");
        xmm.bean.User u=new xmm.bean.User();
        u.setUserName("李四");
        u.setId(111);
        u.setGender(1);

        logger.error("ops over ...."+u);

        return u;
    }
    
    public List getAll(){
        List<org.xmm.falsework.entity.User> list = userMapper.selectList(null);
        System.out.println(list);
        System.out.println(CustomizedPropertyConfigurer.getContextProperty("xxx"));

        System.out.println("----- baseMapper 自带分页 ------");
        Page<User> page = new Page<>(1, 2);
        IPage<User> userIPage = userMapper.selectPage(page,null);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        System.out.println(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 ------");

        Page<Map> mpage = new Page<>(1, 2);
//        IPage<Map> mapIPage = userMapper.
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        System.out.println(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 ------");
        
        return null;
        
    }
    
    
    
}
