package com.lihu.homework.dao;


import com.lihu.homework.po.PublishHomework;
import com.lihu.homework.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Li
 **/
public interface PublicHomeworkRepository extends JpaRepository<PublishHomework,Long>, JpaSpecificationExecutor<PublishHomework> {

    PublishHomework findByNote(String note);

}
