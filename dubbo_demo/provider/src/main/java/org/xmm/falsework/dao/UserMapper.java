package org.xmm.falsework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xmm.falsework.entity.User;

public interface UserMapper extends BaseMapper<User> {
    
    User getUserById(int id);    
}
