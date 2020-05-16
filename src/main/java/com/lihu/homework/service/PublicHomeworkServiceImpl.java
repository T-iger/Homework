package com.lihu.homework.service;

import com.lihu.homework.dao.HomeworkRepository;
import com.lihu.homework.dao.HomeworkStatusRepository;
import com.lihu.homework.dao.PublicHomeworkRepository;
import com.lihu.homework.po.Homework;
import com.lihu.homework.po.HomeworkStatus;
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
import java.util.Optional;

/**
 * @author Li
 **/
@Service
public class PublicHomeworkServiceImpl implements PublicHomeworkService {
    @Autowired
    private PublicHomeworkRepository publicHomeworkRepository;
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private HomeworkStatusRepository homeworkStatusRepository;


    @Override//根据id查询发布的作业
    public Optional<PublishHomework> getPublishHomework(Long id) {
        return publicHomeworkRepository.findById(id);
    }

    @Transactional
    @Override//查询作业列表
    public Page<PublishHomework> listPublic(Pageable pageable,PublishHomework publishHomework,String isPublish) {
        return publicHomeworkRepository.findAll(new Specification<PublishHomework>() {
            @Override
            public Predicate toPredicate(Root<PublishHomework> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates=new ArrayList<>();
                if (!"".equals(publishHomework.getNote())&&publishHomework.getNote()!=null){
                    predicates.add(cb.like(root.<String>get("note"), publishHomework.getNote()));
                }
                if("".equals(isPublish)&&isPublish!=null){
                    if ("1".equals(isPublish)){
                        predicates.add(cb.equal(root.<Boolean>get("ispublish"), true));
                    }
                    if ("2".equals(isPublish)){
                        predicates.add(cb.equal(root.<Boolean>get("ispublish"), false));
                    }
                }
                predicates.add(cb.equal(root.<String>get("username"), publishHomework.getUsername()));
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }


    @Override
    public Page<PublishHomework> listPublicGai(Pageable pageable,String username) {
        return publicHomeworkRepository.findAll(new Specification<PublishHomework>() {
            @Override
            public Predicate toPredicate(Root<PublishHomework> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates=new ArrayList<>();
                predicates.add(cb.equal(root.<Boolean>get("ispublish"), true));
                predicates.add(cb.equal(root.<String>get("username"), username));
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Transactional
    @Override
    public Page<PublishHomework> showListPublic(Pageable pageable,Long userId) {
        return publicHomeworkRepository.findAll(new Specification<PublishHomework>() {
            @Override
            public Predicate toPredicate(Root<PublishHomework> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates=new ArrayList<>();
                Join join=root.join("users");
                return cb.equal(join.get("id"), userId);
            }
        },pageable);
    }

    @Override
    public List<User> findPiGai(Long id) {
        return publicHomeworkRepository.findById(id).get().getUsers();
    }

    @Transactional
    @Override//保存未发布的作业
    public PublishHomework saveNotPublish(PublishHomework publishHomework) {
        publishHomework.setUpdatetime(new Date());
        PublishHomework p1 = publicHomeworkRepository.findByNote(publishHomework.getNote());
        if (p1!= null) {
            publishHomework.setId(p1.getId());
        }

        return publicHomeworkRepository.save(publishHomework);
    }

    @Override//根据名称查询作业
    public List<Homework> listHomework(String note) {
        return homeworkRepository.findByNote(note);
    }

    @Override
    public List<PublishHomework> findNoPublish(String userName) {
        return publicHomeworkRepository.findByUsernameAndIspublish(userName,false);
    }

    @Override
    public PublishHomework savePublish(PublishHomework publishHomework) {
//        publishHomework.setId(publicHomeworkRepository.findByNote(publishHomework.getNote()).getId());
        publishHomework.setIspublish(true);
        publishHomework.setUpdatetime(new Date());
        for (User user : publishHomework.getUsers()) {
            HomeworkStatus homeworkStatus=new HomeworkStatus();
            homeworkStatus.setPublishHomework(publishHomework);
            homeworkStatus.setStatus(false);
            homeworkStatus.setUserStatus(user);
            homeworkStatusRepository.save(homeworkStatus);
        }
        List<Homework> byNote = homeworkRepository.findByNote(publishHomework.getNote());
        for (Homework homework : byNote) {
            homework.setPublishHomework(publishHomework);
            homeworkRepository.save(homework);
        }

        return publicHomeworkRepository.save(publishHomework);
    }

    @Override
    public PublishHomework findOne(Long id) {
        return  publicHomeworkRepository.findById(id).get();
    }

    @Override
    public PublishHomework deleteOne(PublishHomework publishHomework) {
        publishHomework.setUsers(null);
        return publicHomeworkRepository.save(publishHomework);
    }

    @Override
    public PublishHomework findOne(String note) {
        return publicHomeworkRepository.findByNote(note);
    }
}
