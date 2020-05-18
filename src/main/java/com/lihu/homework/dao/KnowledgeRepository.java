package com.lihu.homework.dao;

import com.lihu.homework.po.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Li
 **/
public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {
    List<Knowledge> findByPropertyAndObject(String property,String object);

    List<Knowledge> findByPropertyAndSubject(String property,String subject);

}
