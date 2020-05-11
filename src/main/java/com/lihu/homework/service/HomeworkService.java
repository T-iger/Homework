package com.lihu.homework.service;

import com.lihu.homework.po.Homework;
import com.lihu.homework.po.PublishHomework;

import java.util.HashSet;
import java.util.List;


/**
 * @author Li
 **/
public interface HomeworkService {
    Homework addHomework(Homework homework);
    HashSet findAll();
    List<Homework> findPublish(PublishHomework publishHomework);


    /*Page<Homework> listHomework(Pageable pageable);*/
}
