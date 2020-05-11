package com.lihu.homework.service;

import com.lihu.homework.dao.UserRepository;
import com.lihu.homework.po.User;
import com.lihu.homework.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Li
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public List<User> classUser(String banji) {
        List<User> userList = userRepository.findByUserclass(banji);
        return userList;
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }
}
