package com.lihu.homework.service;

import com.lihu.homework.po.Answer;
import com.lihu.homework.po.HomeworkStatus;
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

    HomeworkStatus setComment(String comment,User user,PublishHomework publishHomework);

    HomeworkStatus getHomeworkStatus(User user,PublishHomework publishHomework);

    List<HomeworkStatus> findUndoHomework(User user);

    List<HomeworkStatus> findFinishHomework(User user);
}
