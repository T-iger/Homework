package com.lihu.homework.service;

import com.lihu.homework.dao.PublicHomeworkRepository;
import com.lihu.homework.po.PublishHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Li
 **/
@Service
public class PublicHomeworkServiceImpl implements PublicHomeworkService {
    @Autowired
    private PublicHomeworkRepository publicHomeworkRepository;


    @Transactional
    @Override
    public Page<PublishHomework> listPublic(Pageable pageable) {
        return publicHomeworkRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public PublishHomework saveNotPublish(PublishHomework publishHomework) {
        publishHomework.setUpdatetime(new Date());
        return publicHomeworkRepository.save(publishHomework);
    }
}
