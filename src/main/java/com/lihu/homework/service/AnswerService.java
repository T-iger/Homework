package com.lihu.homework.service;

import com.lihu.homework.po.Answer;
import com.lihu.homework.po.PublishHomework;
import com.lihu.homework.po.User;

import java.util.List;

/**
 * @author Li
 **/
public interface AnswerService {
    List<Answer> save(List<Answer> answerList);
    List<Answer> findAnswer(User user, PublishHomework publishHomework);

    Answer setScore(int score, Long answerId);

    List<Answer> getScore(Long id, List<User> piGai);

}
