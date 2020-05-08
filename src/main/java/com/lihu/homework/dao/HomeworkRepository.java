package com.lihu.homework.dao;

import com.lihu.homework.po.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Li
 **/
public interface HomeworkRepository extends JpaRepository<Homework,Long> {

    List<Homework> findByNote(String note);
}
