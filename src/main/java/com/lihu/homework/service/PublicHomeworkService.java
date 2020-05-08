package com.lihu.homework.service;

import com.lihu.homework.po.Homework;
import com.lihu.homework.po.PublishHomework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Li
 **/
public interface PublicHomeworkService {

    Page<PublishHomework> listPublic(Pageable pageable);

    Page<PublishHomework> showListPublic(Pageable pageable,Long userId);

    PublishHomework saveNotPublish(PublishHomework publishHomework);

    List<Homework> listHomework(String note);
    List<PublishHomework> findAll();
    PublishHomework savePublish(PublishHomework publishHomework);
}
