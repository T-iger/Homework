package com.lihu.homework.service;

import com.lihu.homework.po.Homework;
import com.lihu.homework.po.PublishHomework;
import com.lihu.homework.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Li
 **/
public interface PublicHomeworkService {

    Optional<PublishHomework> getPublishHomework(Long id);

    Page<PublishHomework> listPublic(Pageable pageable);

    Page<PublishHomework> listPublicGai(Pageable pageable,String username);

    Page<PublishHomework> showListPublic(Pageable pageable,Long userId);

    PublishHomework saveNotPublish(PublishHomework publishHomework);

    List<Homework> listHomework(String note);

    List<PublishHomework> findAll();

    PublishHomework savePublish(PublishHomework publishHomework);

    List<User> findPiGai(Long id);
}
