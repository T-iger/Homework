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
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatetime;  //一套作业更新时间
    private Date starttime;  //发布时间
    private Date endtime;  //截止时间
    private String note;  //记录名称
    private boolean ispublish;  //是否发布

    @ManyToOne
    private Course course;
    @ManyToMany
    private List<User> users=new ArrayList<>();
    @OneToMany(mappedBy = "publishHomework")
    private List<Homework> homeworks =new ArrayList<>();

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

    public boolean isIspublish() {
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

    @Override
    public String toString() {
        return "PublishHomework{" +
                "id=" + id +
                ", public_name='" + publicname + '\'' +
                ", update_time=" + updatetime +
                ", start_time=" + starttime +
                ", end_time=" + endtime +
                ", note='" + note + '\'' +
                ", is_publish=" + ispublish +
                '}';
    }
}
