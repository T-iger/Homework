package com.lihu.homework.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Li
 **/
@Entity
@Table(name = "tb_status")
public class HomeworkStatus {

    @Id
    @GeneratedValue
    private Long id;

    private Boolean status;
    @OneToOne
    private PublishHomework publishHomework;
    @OneToOne
    private User userStatus;

    public HomeworkStatus() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public PublishHomework getPublishHomework() {
        return publishHomework;
    }

    public void setPublishHomework(PublishHomework publishHomework) {
        this.publishHomework = publishHomework;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(User user_status) {
        this.userStatus = user_status;
    }

    @Override
    public String toString() {
        return "HomeworkStatus{" +
                "status=" + status +
                '}';
    }
}
