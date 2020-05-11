package com.lihu.homework.dao;


import com.lihu.homework.po.PublishHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.awt.print.Pageable;
import java.util.Optional;

/**
 * @author Li
 **/
public interface PublicHomeworkRepository extends JpaRepository<PublishHomework,Long>, JpaSpecificationExecutor<PublishHomework> {

    PublishHomework findByNote(String note);


}
