package com.lihu.homework.service;

import com.lihu.homework.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Li
 **/
public interface UserService {
    User checkUser(String username,String password);
    List<User> classUser(String banji);
    User add(User user);//增加用户
    User findUser(Long id);
    Page<User> findAllUser(Pageable pageable);
    Boolean deleteUser(User user);
}
