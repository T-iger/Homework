package com.lihu.homework.web;


import com.lihu.homework.service.HomeworkService;
import com.lihu.homework.service.PublicHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Li
 **/
@Controller()
@RequestMapping("/login/teacher")
public class TeacherController {


    @Autowired
    private PublicHomeworkService publicHomeworkService;

    @GetMapping("/teacherIndex")
    public String teacherIndex(){

        return "/teacher/teacherindex";
    }

    @GetMapping("/teacherHomework")
    public String teacherHomework(@PageableDefault(size = 5,sort = {"updatetime"},direction = Sort.Direction.DESC)
                                              Pageable pageable, Model model){
        model.addAttribute("page",publicHomeworkService.listPublic(pageable));
        return "/teacher/homework";
    }
    @GetMapping("/addHomework")
    public String addHomework(){
        return "/teacher/homeworkinput";
    }

    @GetMapping("/homeworkPublic")
    public String homeworkPublic(){
        return "/teacher/homeworkpublic";
    }
}
