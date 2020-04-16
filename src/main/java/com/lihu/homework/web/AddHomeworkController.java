package com.lihu.homework.web;

import com.lihu.homework.po.Homework;
import com.lihu.homework.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Li
 **/
@Controller
@RequestMapping("/")
public class AddHomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    @ResponseBody
    @PostMapping("/addRadio")
    public void addRadio(Homework homework) {
        Homework homework1=homeworkService.addHomework(homework);
    }

}
