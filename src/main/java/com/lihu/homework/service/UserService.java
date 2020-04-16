package com.lihu.homework.service;

import com.lihu.homework.po.User;

/**
 * @author Li
 **/
public interface UserService {
    public abstract User checkUser(String username,String password);
}
