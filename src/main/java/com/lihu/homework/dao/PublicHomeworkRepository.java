package com.lihu.homework.dao;


import com.lihu.homework.po.PublishHomework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Li
 **/
public interface PublicHomeworkRepository extends JpaRepository<PublishHomework, Long>, JpaSpecificationExecutor<PublishHomework> {

    PublishHomework findByNote(String note);

    List<PublishHomework> findByUsernameAndIspublish(String username, Boolean b);

    List<PublishHomework> findAllById(Iterable<Long> iterable);


}
