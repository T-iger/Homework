package com.lihu.homework.dao;

import com.lihu.homework.po.Homework;
import com.lihu.homework.po.PublishHomework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Li
 **/
public interface HomeworkRepository extends JpaRepository<Homework,Long> {

    List<Homework> findByNote(String note);
    List<Homework> findByPublishHomework(PublishHomework publishHomework);
}
