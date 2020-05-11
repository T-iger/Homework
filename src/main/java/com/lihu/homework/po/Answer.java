package com.lihu.homework.po;

import javax.persistence.*;

/**
 * @author Li
 **/
@Entity //具备和数据对应的功能
@Table(name = "tb_answer")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    private String studentanswer; //学生回答主观题的答案
    private String studenttk; //学生回答主观题的答案
    private String studentradio; //学生回答的单选题答案
    private int score;//分值

    @OneToOne
    private Homework homework;
    @ManyToOne
    private User user;
    @ManyToOne
    private PublishHomework publishHomework;

    public Answer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentanswer() {
        return studentanswer;
    }

    public void setStudentanswer(String studentanswer) {
        this.studentanswer = studentanswer;
    }

    public String getStudenttk() {
        return studenttk;
    }

    public void setStudenttk(String studenttk) {
        this.studenttk = studenttk;
    }

    public String getStudentradio() {
        return studentradio;
    }

    public void setStudentradio(String studentradio) {
        this.studentradio = studentradio;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PublishHomework getPublishHomework() {
        return publishHomework;
    }

    public void setPublishHomework(PublishHomework publishHomework) {
        this.publishHomework = publishHomework;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", studentanswer='" + studentanswer + '\'' +
                ", studenttk='" + studenttk + '\'' +
                ", studentradio='" + studentradio + '\'' +
                ", score=" + score +
                '}';
    }
}
