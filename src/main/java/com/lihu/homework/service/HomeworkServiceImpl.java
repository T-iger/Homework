package com.lihu.homework.service;

import com.lihu.homework.dao.HomeworkRepository;
import com.lihu.homework.po.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Li
 **/
@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;

    @Transactional
    @Override
    public Homework addHomework(Homework homework) {
        return homeworkRepository.save(homework);
    }
}
