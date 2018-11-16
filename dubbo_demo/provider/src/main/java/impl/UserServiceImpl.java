package impl;

import com.alibaba.dubbo.config.annotation.Service;
import xmm.bean.User;
import xmm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Override
    public User getUserById(int id) {
        
        User u=new User();
        u.setUserName("张三");
        u.setId(1);
        u.setGender(0);
        return u;
    }
}
