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

    Page<PublishHomework> listPublic(Pageable pageable,PublishHomework publishHomework,String isPublish);

    Page<PublishHomework> listPublicGai(Pageable pageable,String username);

    Page<PublishHomework> showListPublic(Pageable pageable,Long userId);

    Page<PublishHomework> showFinishListPublic(Pageable pageable,Long userId);

    Page<PublishHomework> showUndoListPublic(Pageable pageable,Long userId);

    PublishHomework saveNotPublish(PublishHomework publishHomework);

    List<Homework> listHomework(String note);

    List<PublishHomework> findNoPublish(String userName);

    PublishHomework savePublish(PublishHomework publishHomework);

    List<User> findPiGai(Long id);

    PublishHomework findOne(Long id);

    PublishHomework deleteOne(PublishHomework publishHomework);

    PublishHomework findOne(String note);

    Page<PublishHomework> undoListPublic(Pageable pageable,User user,String course);

    Page<PublishHomework> FinishListPublic(Pageable pageable,User user,String course);
}
