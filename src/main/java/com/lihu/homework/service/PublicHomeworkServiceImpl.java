package com.lihu.homework.service;

import com.lihu.homework.dao.HomeworkRepository;
import com.lihu.homework.dao.PublicHomeworkRepository;
import com.lihu.homework.po.Homework;
import com.lihu.homework.po.PublishHomework;
import com.lihu.homework.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Li
 **/
@Service
public class PublicHomeworkServiceImpl implements PublicHomeworkService {
    @Autowired
    private PublicHomeworkRepository publicHomeworkRepository;

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Transactional
    @Override
    public Page<PublishHomework> listPublic(Pageable pageable) {
        return publicHomeworkRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Page<PublishHomework> showListPublic(Pageable pageable,Long userId) {
        return publicHomeworkRepository.findAll(new Specification<PublishHomework>() {
            @Override
            public Predicate toPredicate(Root<PublishHomework> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates=new ArrayList<>();
                Join join=root.join("users");
                return cb.equal(join.get("id"),userId);
            }
        },pageable);
    }

    @Transactional
    @Override
    public PublishHomework saveNotPublish(PublishHomework publishHomework) {
        publishHomework.setUpdatetime(new Date());
        PublishHomework p1 = publicHomeworkRepository.findByNote(publishHomework.getNote());
        if (p1!= null) {
            publishHomework.setId(p1.getId());
        }
        return publicHomeworkRepository.save(publishHomework);
    }

    @Override
    public List<Homework> listHomework(String note) {
        return homeworkRepository.findByNote(note);
    }

    @Override
    public List<PublishHomework> findAll() {
        return publicHomeworkRepository.findAll();
    }

    @Override
    public PublishHomework savePublish(PublishHomework publishHomework) {
        publishHomework.setId(publicHomeworkRepository.findByNote(publishHomework.getNote()).getId());
        publishHomework.setIspublish(true);
        publishHomework.setUpdatetime(new Date());
        return publicHomeworkRepository.save(publishHomework);
    }
}
