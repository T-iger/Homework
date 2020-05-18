package com.lihu.homework.po;

import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Li
 **/
@Entity
@Table(name="tb_publish")
public class PublishHomework {
    @Id
    @GeneratedValue
    private Long id;

    private String publicname;  //发布名称
   /* @Temporal(TemporalType.TIMESTAMP)*/
    private Date updatetime;  //一套作业更新时间
  /*  @Temporal(TemporalType.TIMESTAMP)*/
    private Date starttime;  //发布时间
    /*@Temporal(TemporalType.TIMESTAMP)*/
    private Date endtime;  //截止时间
    private String note;  //记录名称
    private boolean ispublish;  //是否发布
    private String username;//发布者姓名

    @ManyToOne
    private Course course;
    @ManyToMany
    private List<User> users=new ArrayList<>();

    @OneToMany(mappedBy = "publishHomework")
    private List<Homework> homeworks =new ArrayList<>();

    @OneToOne(mappedBy = "publishHomework")
    private List<HomeworkStatus> statusList=new ArrayList<>();

    @OneToMany(mappedBy = "publishHomework")
    private List<Answer> answerList=new ArrayList<>();

    public PublishHomework() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicname() {
        return publicname;
    }

    public void setPublicname(String publicname) {
        this.publicname = publicname;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean getIspublish() {
        return ispublish;
    }

    public void setIspublish(boolean ispublish) {
        this.ispublish = ispublish;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public List<HomeworkStatus> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<HomeworkStatus> statusList) {
        this.statusList = statusList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "PublishHomework{" +
                "id=" + id +
                ", publicname='" + publicname + '\'' +
                ", updatetime=" + updatetime +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", note='" + note + '\'' +
                ", ispublish=" + ispublish +
                ", username='" + username + '\'' +
                '}';
    }
}
