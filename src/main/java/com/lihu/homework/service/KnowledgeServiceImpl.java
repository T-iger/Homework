package com.lihu.homework.service;

import com.lihu.homework.dao.KnowledgeRepository;
import com.lihu.homework.po.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
        return knowledgeRepository.findByPropertyAndObject("type", "MathKChuzhong");
    }

    @Override
    public HashMap<String, String> TuiJan(String subject) {
        HashMap<String, String> hashMap = new HashMap<>();
        StringBuilder note = new StringBuilder();
        StringBuilder link = new StringBuilder();
        HashSet<String> hashSet = new HashSet<>();
        List<Knowledge> hasPostKOwlList = knowledgeRepository.findByPropertyAndSubject("hasPostK", subject);
        HashSet<String> hasPostSet = new HashSet<>();
        note.append("[{" + "name:'" + subject + "',des:'当前欠缺知识点:" +subject + "',symbolSize:50,category:0" + "}");
        link.append("[");
        if (!hasPostKOwlList.isEmpty()) {
            for (Knowledge knowledge : hasPostKOwlList) {
                //查询不能为空
                if (!knowledge.getObject().isEmpty() && hasPostSet.add(knowledge.getObject())) {
                    //判断subject和Object是否相同，和是否为最后一个
                    if (!knowledge.getSubject().equals(knowledge.getObject()) && hashSet.add(knowledge.getObject())) {
                        note.append(",{" + "name:'" + knowledge.getObject() + "',des:'" + knowledge.getObject() + "',symbolSize:50,category:2" + "}");
                        link.append(",{" + "source:'" + knowledge.getSubject() + "',target:'" + knowledge.getObject() + "',name:'后继知识点'" + ",lineStyle: {normal: { curveness: 0.1 }}}");
                    } else if (!knowledge.getSubject().equals(knowledge.getObject())) {
                        link.append(",{" + "source:'" + knowledge.getSubject() + "',target:'" + knowledge.getObject() + "',name:'后继知识点'" + ",lineStyle: {normal: { curveness: 0.1 }}}");
                    }
                }
            }
            LinkedHashSet<String> count = new LinkedHashSet<>();
            count.addAll(hashSet);
            while (!count.isEmpty()) {
                String next = count.iterator().next();
                count.remove(next);
                List<Knowledge> owls = knowledgeRepository.findByPropertyAndSubject("hasPostK", next);
                if (!owls.isEmpty()) {
                    for (Knowledge knowledge : owls) {
                        //查询不能为空
                        if (!knowledge.getObject().isEmpty() && hasPostSet.add(knowledge.getObject())) {
                            //判断subject和Object是否相同，和是否为最后一个
                            if (!knowledge.getSubject().equals(knowledge.getObject()) && hashSet.add(knowledge.getObject())) {
                                count.add(knowledge.getObject());
                                note.append(",{" + "name:'" + knowledge.getObject() + "',des:'" + knowledge.getObject() + "',symbolSize:50,category:2" + "}");
                                link.append(",{" + "source:'" + knowledge.getSubject() + "',target:'" + knowledge.getObject() + "',name:'后继知识点'" + ",lineStyle: {normal: { curveness: 0.1 }}}");
                            } else if (!knowledge.getSubject().equals(knowledge.getObject())) {
                                link.append(",{" + "source:'" + knowledge.getSubject() + "',target:'" + knowledge.getObject() + "',name:'后继知识点'" + ",lineStyle: {normal: { curveness: 0.1 }}}");
                            }
                        }
                    }
                }
            }
        }
        /*hasPreK查询*/
        List<Knowledge> hasPreKOwlList = knowledgeRepository.findByPropertyAndSubject("hasPreK", subject);
        HashSet<String> hasPreKSet = new HashSet<>();
        if (!hasPreKOwlList.isEmpty()) {
            for (Knowledge knowledge : hasPreKOwlList) {
                //查询不能为空
                if (!knowledge.getObject().isEmpty() && hasPreKSet.add(knowledge.getObject())) {
                    //判断subject和Object是否相同，和是否为最后一个
                    if (!knowledge.getSubject().equals(knowledge.getObject()) && hashSet.add(knowledge.getObject())) {
                        note.append(",{" + "name:'" + knowledge.getObject() + "',des:'" + knowledge.getObject() + "',symbolSize:50,category:2" + "}");
                        link.append(",{" + "source:'" + knowledge.getSubject() + "',target:'" + knowledge.getObject() + "',name:'前序知识点'" + ",lineStyle: {normal: { curveness: 0.1 }}}");
                    } else if (!knowledge.getSubject().equals(knowledge.getObject())) {
                        link.append(",{" + "source:'" + knowledge.getSubject() + "',target:'" + knowledge.getObject() + "',name:'前序知识点'" + ",lineStyle: {normal: { curveness: 0.1 }}}");
                    }
                }
            }
            LinkedHashSet<String> count = new LinkedHashSet<>();
            count.addAll(hashSet);
            while (!count.isEmpty()) {
                String next = count.iterator().next();
                count.remove(next);
                List<Knowledge> owls = knowledgeRepository.findByPropertyAndSubject("hasPreK", next);
                if (!owls.isEmpty()) {
                    for (Knowledge knowledge : owls) {
                        //查询不能为空
                        if (!knowledge.getObject().isEmpty() && hasPreKSet.add(knowledge.getObject())) {
                            //判断subject和Object是否相同，和是否为最后一个
                            if (!knowledge.getSubject().equals(knowledge.getObject()) && hashSet.add(knowledge.getObject())) {
                                count.add(knowledge.getObject());
                                note.append(",{" + "name:'" + knowledge.getObject() + "',des:'" + knowledge.getObject() + "',symbolSize:50,category:2" + "}");
                                link.append(",{" + "source:'" + knowledge.getSubject() + "',target:'" + knowledge.getObject() + "',name:'前序知识点'" + ",lineStyle: {normal: { curveness: 0.1 }}}");
                            } else if (!knowledge.getSubject().equals(knowledge.getObject())) {
                                link.append(",{" + "source:'" + knowledge.getSubject() + "',target:'" + knowledge.getObject() + "',name:'前序知识点'" + ",lineStyle: {normal: { curveness: 0.1 }}}");
                            }
                        }
                    }
                }
            }
        }
        if (note.toString().endsWith(",")){
            StringBuilder note2 = new StringBuilder();
            note2.append( note.toString().substring(0, note.length()-1)+"]");
            note=note2;
        }else{
            note.append("]");
        }
        if (link.toString().startsWith("[,")){
            StringBuilder link2 = new StringBuilder();
            link2.append("["+link.toString().substring(2,link.length())+"]");
            link=link2;
        }

        String a = note.substring(0, note.length());
        String b = link.substring(0, link.length());

        hashMap.put("NOTE", a);
        hashMap.put("LINK", b);

        return hashMap;
    }
}
