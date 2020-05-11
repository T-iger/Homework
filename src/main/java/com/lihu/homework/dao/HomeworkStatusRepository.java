package com.lihu.homework.dao;

import com.lihu.homework.po.Answer;
import com.lihu.homework.po.HomeworkStatus;
import com.lihu.homework.po.PublishHomework;
import com.lihu.homework.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Li
 **/
public interface HomeworkStatusRepository extends JpaRepository<HomeworkStatus,Long> {
    HomeworkStatus findByUserStatusAndPublishHomework(User user,PublishHomework publishHomework);
}
