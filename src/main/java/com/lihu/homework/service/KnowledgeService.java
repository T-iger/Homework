package com.lihu.homework.service;

import com.lihu.homework.po.Knowledge;

import java.util.HashMap;
import java.util.List;

/**
 * @author Li
 **/
public interface KnowledgeService {
    List<Knowledge> listKnowledge();

    HashMap<String,String> TuiJan(String subject);//推荐不会的知识点
}
