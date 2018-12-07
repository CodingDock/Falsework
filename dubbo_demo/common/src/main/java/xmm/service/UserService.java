package xmm.service;

import xmm.bean.User;

import java.util.List;

public interface UserService {
    
    User getUserById(int id);

    List getAll();
    
}
