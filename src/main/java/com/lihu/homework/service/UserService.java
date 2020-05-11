package com.lihu.homework.service;

import com.lihu.homework.po.User;

import java.util.List;

/**
 * @author Li
 **/
public interface UserService {
    public abstract User checkUser(String username,String password);
    List<User> classUser(String banji);
    User add(User user);//增加用户
}
