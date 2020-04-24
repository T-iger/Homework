package com.lihu.homework.service;

import com.lihu.homework.po.Homework;
import com.lihu.homework.po.PublishHomework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Li
 **/
public interface PublicHomeworkService {

    Page<PublishHomework> listPublic(Pageable pageable);

    PublishHomework saveNotPublish(PublishHomework publishHomework);
}
