package com.lihu.homework.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Li
 **/
@Entity
@Table(name="tb_knowledge")
public class Knowledge {

    @Id
    @GeneratedValue
    private Long id;
    private String subject;
    private String property;
    private String object;

    @OneToMany(mappedBy = "knowledge")
    private List<Homework> homeworks =new ArrayList<>();
    @ManyToOne
    private Course course;

    public Knowledge() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", property='" + property + '\'' +
                ", object='" + object + '\'' +
                '}';
    }
}
