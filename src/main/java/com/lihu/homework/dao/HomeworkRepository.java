package com.lihu.homework.dao;

import com.lihu.homework.po.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Li
 **/
public interface HomeworkRepository extends JpaRepository<Homework,Long> {
}
