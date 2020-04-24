package com.lihu.homework.service;

import com.lihu.homework.dao.KnowledgeRepository;
import com.lihu.homework.po.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * @author Li
 **/
@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Override
    public List<Knowledge> listKnowledge() {
        return knowledgeRepository.findAll();
    }
}
