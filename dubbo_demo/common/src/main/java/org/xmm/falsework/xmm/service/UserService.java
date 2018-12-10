package org.xmm.falsework.xmm.service;

import org.xmm.falsework.xmm.bean.User;

import java.util.List;

public interface UserService {
    
    User getUserById(int id);

    List getAll();
    
}
