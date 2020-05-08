package com.lihu.homework.dao;

import com.lihu.homework.po.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 * @author Li
 **/
public interface UserRepository extends JpaRepository<User,Long> {


    User findByUsernameAndPassword(String username, String password);
    List<User> findByUserclass(String banji);

}
