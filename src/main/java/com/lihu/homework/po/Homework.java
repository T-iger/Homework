package com.lihu.homework.po;

import javax.persistence.*;

/**
 * @author Li
 **/
@Entity //具备和数据对应的功能
@Table(name = "tb_homework")
public class Homework {

    @Id
    @GeneratedValue
    private Long id;
    private String questiontype; //问题类型（单选、主观题）
    private String content;  //题目内容
    private String radioA;
    private String radioB;
    private String radioC;
    private String radioD;
    private String studentanswer; //学生回答主观题的答案
    private String studentradio; //学生回答的单选题答案
    private String radio; //单选正确答案
    private String tk; //填空正确答案
    private int initialscore; //题目初始分值
    private int score; //该题得分
    private String note; //一些介绍

    @ManyToOne
    private Knowledge knowledge;
    @ManyToOne
    private PublishHomework publishHomework;//一题发布一次

    public Homework() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStudentanswer() {
        return studentanswer;
    }

    public void setStudentanswer(String studentanswer) {
        this.studentanswer = studentanswer;
    }

    public String getStudentradio() {
        return studentradio;
    }

    public void setStudentradio(String studentradio) {
        this.studentradio = studentradio;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public int getInitialscore() {
        return initialscore;
    }

    public void setInitialscore(int initialscore) {
        this.initialscore = initialscore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    public PublishHomework getPublishHomework() {
        return publishHomework;
    }

    public void setPublishHomework(PublishHomework publishHomework) {
        this.publishHomework = publishHomework;
    }

    public String getRadioA() {
        return radioA;
    }

    public void setRadioA(String radioA) {
        this.radioA = radioA;
    }

    public String getRadioB() {
        return radioB;
    }

    public void setRadioB(String radioB) {
        this.radioB = radioB;
    }

    public String getRadioC() {
        return radioC;
    }

    public void setRadioC(String radioC) {
        this.radioC = radioC;
    }

    public String getRadioD() {
        return radioD;
    }

    public void setRadioD(String radioD) {
        this.radioD = radioD;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", questiontype='" + questiontype + '\'' +
                ", content='" + content + '\'' +
                ", radioA='" + radioA + '\'' +
                ", radioB='" + radioB + '\'' +
                ", radioC='" + radioC + '\'' +
                ", radioD='" + radioD + '\'' +
                ", studentanswer='" + studentanswer + '\'' +
                ", studentradio='" + studentradio + '\'' +
                ", radio='" + radio + '\'' +
                ", tk='" + tk + '\'' +
                ", initialscore=" + initialscore +
                ", score=" + score +
                ", note='" + note + '\'' +
                '}';
    }
}
