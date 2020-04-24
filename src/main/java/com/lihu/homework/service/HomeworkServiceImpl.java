package com.lihu.homework.service;

import com.lihu.homework.dao.HomeworkRepository;
import com.lihu.homework.po.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

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

    @Override
    public HashSet findAll() {
        HashSet set = new HashSet();
        List<Homework> all = homeworkRepository.findAll();
        for (Homework homework : all) {
            set.add(homework.getNote());
        }
        return set;
    }

    /*    @Transactional
    @Override
    public Page<Homework> listHomework(Pageable pageable) {
        return homeworkRepository.findAll(pageable);
    }*/

}
