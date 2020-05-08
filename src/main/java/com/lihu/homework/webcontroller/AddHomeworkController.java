package com.lihu.homework.webcontroller;

import com.lihu.homework.po.Homework;
import com.lihu.homework.po.PublishHomework;
import com.lihu.homework.service.HomeworkService;
import com.lihu.homework.service.PublicHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



/**
 * @author Li
 **/
@Controller
@RequestMapping("/login/teacher")
public class AddHomeworkController {

    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private PublicHomeworkService publicHomeworkService;

    @ResponseBody
    @PostMapping("/addRadio")
    public void addRadio(Homework homework) {
        Homework homework1 = homeworkService.addHomework(homework);
    }

    @GetMapping("/homework")
    public String home(){
        return "/teacher/homework";
    }


    @PostMapping("/saveNotPublish")
    public String saveHomework(PublishHomework publishHomework, RedirectAttributes attributes) {
        PublishHomework publishHomework1 = publicHomeworkService.saveNotPublish(publishHomework);
        if (publishHomework1 == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/login/teacher/teacherHomework";
    }

}
