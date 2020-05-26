package com.lihu.homework.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Li
 **/
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue//构建规则
    private Long id;
    private String username;  //用户姓名
    private String role;  //用户身份
    private String password;  //用户密码
    private Date birthday;  //用户生日--
    private String sex;  //用户性别
    private String phone;  //用户手机号码
    private String userclass;  //用户班级
    private String photo;//头像地址--
    private Boolean status;//用户状态

    @OneToMany(mappedBy = "parentuser")
    private List<User> replayusers;
    @ManyToOne
    private User parentuser;

    @ManyToMany(mappedBy = "users")
    private List<PublishHomework> publishHomeworks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Answer> answerList;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "userStatus")
    private List<HomeworkStatus> homeworkStatusList=new ArrayList<>() ;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserclass() {
        return userclass;
    }

    public void setUserclass(String userclass) {
        this.userclass = userclass;
    }

    public List<PublishHomework> getPublishHomeworks() {
        return publishHomeworks;
    }

    public void setPublishHomeworks(List<PublishHomework> publishHomeworks) {
        this.publishHomeworks = publishHomeworks;
    }

    public List<User> getReplayusers() {
        return replayusers;
    }

    public void setReplayusers(List<User> replayusers) {
        this.replayusers = replayusers;
    }

    public User getParentuser() {
        return parentuser;
    }

    public void setParentuser(User parentuser) {
        this.parentuser = parentuser;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<HomeworkStatus> getHomeworkStatusList() {
        return homeworkStatusList;
    }

    public void setHomeworkStatusList(List<HomeworkStatus> homeworkStatusList) {
        this.homeworkStatusList = homeworkStatusList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", userclass='" + userclass + '\'' +
                ", photo='" + photo + '\'' +
                ", status=" + status +
                '}';
    }
}
