package org.xmm.falsework.service;

import org.xmm.falsework.bean.User;

import java.util.List;

public interface UserService {
    
    User getUserById(int id);

    List getAll();
    
}
