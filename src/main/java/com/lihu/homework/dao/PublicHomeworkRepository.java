package com.lihu.homework.dao;


import com.lihu.homework.po.PublishHomework;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Li
 **/
public interface PublicHomeworkRepository extends JpaRepository<PublishHomework,Long> {
}
