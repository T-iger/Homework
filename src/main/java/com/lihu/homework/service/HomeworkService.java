package com.lihu.homework.service;

import com.lihu.homework.po.Homework;

import java.util.HashSet;


/**
 * @author Li
 **/
public interface HomeworkService {
    Homework addHomework(Homework homework);
    HashSet findAll();


    /*Page<Homework> listHomework(Pageable pageable);*/
}
