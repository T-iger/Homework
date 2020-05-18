package com.lihu.homework.service;

import com.lihu.homework.dao.AnswerRepository;
import com.lihu.homework.dao.HomeworkRepository;
import com.lihu.homework.dao.HomeworkStatusRepository;
import com.lihu.homework.dao.UserRepository;
import com.lihu.homework.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Li
 **/
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private HomeworkStatusRepository homeworkStatusRepository;
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Answer> save(List<Answer> answerList) {
        Optional<Homework> byId = homeworkRepository.findById(answerList.get(0).getHomework().getId());
        HomeworkStatus stu1 = homeworkStatusRepository.findByUserStatusAndPublishHomework(answerList.get(0).getUser(), byId.get().getPublishHomework());
        if (stu1 != null) {//查询该作业对应状态
            stu1.setStatus(true);
            homeworkStatusRepository.save(stu1);
        }
        return answerRepository.saveAll(answerList);
    }

    @Override
    public List<Answer> findAnswer(User user, PublishHomework publishHomework) {
        List<Answer> answerList = answerRepository.findByUserAndPublishHomework(user, publishHomework);
        for (int i = 0; i < answerList.size(); i++) {
            Answer answer = answerList.get(i);
            Optional<Homework> homework = homeworkRepository.findById(answer.getHomework().getId());
            if (answer.getStudentradio() != null && answer.getStudentradio().equals(homework.get().getRadio())) {
                answerList.get(i).setScore(homework.get().getScore());
            } else if (answer.getStudentradio() != null) {
                answerList.get(i).setScore(0);
            }
            if (answer.getStudenttk() != null && answer.getStudenttk().equals(homework.get().getTk())) {
                answerList.get(i).setScore(homework.get().getScore());
            } else if (answer.getStudenttk() != null) {
                answerList.get(i).setScore(0);
            }
        }
        return answerList;
    }

    @Override
    public Answer setScore(int score, Long answerId) {
        Optional<Answer> answer = answerRepository.findById(answerId);
        answer.get().setScore(score);
        return answerRepository.save(answer.get());
    }

    @Override
    public HomeworkStatus setComment(String comment, User user, PublishHomework publishHomework) {
        HomeworkStatus homeworkStatus = homeworkStatusRepository.findByUserStatusAndPublishHomework(user, publishHomework);
        homeworkStatus.setComment(comment);
        HashSet<String> set = new HashSet<>();
        List<Answer> answerList = answerRepository.findByUserAndPublishHomework(user, publishHomework);
        for (Answer answer : answerList) {
            Optional<Homework> homework = homeworkRepository.findById(answer.getHomework().getId());
            if (answer.getStudentradio() != null && answer.getStudentradio().equals(homework.get().getRadio())) {
                answer.setScore(homework.get().getScore());
                answerRepository.save(answer);
            } else if (answer.getStudentradio() != null) {
                answer.setScore(0);
                set.add(answer.getHomework().getKnowledge().getSubject());//存储错误的知识点
                answerRepository.save(answer);
            }
            if (answer.getStudenttk() != null && answer.getStudenttk().equals(homework.get().getTk())) {
                answer.setScore(homework.get().getScore());
                answerRepository.save(answer);
            } else if (answer.getStudenttk() != null) {
                answer.setScore(0);
                set.add(answer.getHomework().getKnowledge().getSubject());
                answerRepository.save(answer);
            }
        }
        System.out.println(set);

        return homeworkStatusRepository.save(homeworkStatus);
    }

    @Override
    public List<Answer> getScore(Long id, List<User> users) {
        List<Answer> scoreList = new ArrayList<>();
        for (User user : users) {
            PublishHomework publishHomework = new PublishHomework();
            publishHomework.setId(id);
            List<Answer> answerList = answerRepository.findByUserAndPublishHomework(user, publishHomework);
            Integer sum = 0;
            Answer an = new Answer();
            for (Answer answer : answerList) {
                sum += answer.getScore();
            }
            //推荐不会的知识点
            if (sum != 0) {
                for (Answer answer : answerList) {
                    if (answer.getScore() == 0) {//读取分值为0的题目
                        answer.getHomework().getKnowledge().getObject();
                    }
                }
            }

            an.setScore(sum);
            an.setUser(user);
            scoreList.add(an);
        }
        return scoreList;
    }

    //取作业状态（评语和系统评语）
    @Override
    public HomeworkStatus getHomeworkStatus(User user, PublishHomework publishHomework) {
        return homeworkStatusRepository.findByUserStatusAndPublishHomework(user, publishHomework);
    }

    //查询未完成的作业
    @Override
    public List<HomeworkStatus> findUndoHomework(User user) {
        return homeworkStatusRepository.findByUserStatusAndStatus(user, false);
    }
    //查询已完成作业
    @Override
    public List<HomeworkStatus> findFinishHomework(User user) {
        return homeworkStatusRepository.findByUserStatusAndStatus(user, true);
    }
}
