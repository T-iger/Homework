package com.lihu.homework.dao;

import com.lihu.homework.po.Answer;
import com.lihu.homework.po.PublishHomework;
import com.lihu.homework.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Li
 **/
public interface AnswerRepository extends JpaRepository<Answer,Long> {

    List<Answer> findByUserAndPublishHomework(User user, PublishHomework publishHomework);
}
