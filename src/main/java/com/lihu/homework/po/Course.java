package com.lihu.homework.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Li
 **/
@Entity
@Table(name="tb_course")
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String coursename;  //课程名称

    @OneToMany(mappedBy = "course")
    private List<PublishHomework> publishHomeworks=new ArrayList<>();
    @OneToMany(mappedBy = "course")
    private List<Knowledge> knowledges=new ArrayList<>();

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public List<PublishHomework> getPublishHomeworks() {
        return publishHomeworks;
    }

    public void setPublishHomeworks(List<PublishHomework> publishHomeworks) {
        this.publishHomeworks = publishHomeworks;
    }

    public List<Knowledge> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(List<Knowledge> knowledges) {
        this.knowledges = knowledges;
    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + id +
                ", course_name='" + coursename + '\'' +
                '}';
    }


}
