package com.lihu.homework.webcontroller;

import com.lihu.homework.po.Homework;
import com.lihu.homework.po.PublishHomework;
import com.lihu.homework.po.User;
import com.lihu.homework.service.PublicHomeworkService;
import com.lihu.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Li
 **/
@Controller
@RequestMapping("/login/teacher")
public class HomeworkPublishController {

    @Autowired
    private PublicHomeworkService publicHomeworkService;
    @Autowired
    private UserService userService;

    @PostMapping("/publishSearch")
    public String homeworkList(Model model, @RequestParam("note") String note) {
        model.addAttribute("testPagers", publicHomeworkService.listHomework(note));
        List<Homework> homework = publicHomeworkService.listHomework(note);
/*
        for (Homework homework1 : homework) {
            System.out.println(homework1.getQuestiontype());
        }*/
        return "/teacher/homeworkpublic :: homeworkList";
    }

    @PostMapping("/publish")
    public String homeworkPublish(RedirectAttributes attributes,
                                  @RequestParam("banji") String banji,
                                  @RequestParam("starttime") String starttime,
                                  @RequestParam("endtime") String endtime,
                                  @RequestParam("note") String note) {
        SimpleDateFormat start=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        PublishHomework publishHomework = new PublishHomework();
        List<User> userList = userService.classUser(banji);
        String[] split = note.split(",");
        try {
            Date date1=start.parse(starttime);
            Date date2=start.parse(endtime);
            publishHomework.setStarttime(date1);
            publishHomework.setEndtime(date2);
            publishHomework.setNote(split[0]);
            publishHomework.setId(Long.valueOf(split[1]));
            publishHomework.setUsers(userList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PublishHomework publishHomework1 = publicHomeworkService.savePublish(publishHomework);
        if (publishHomework1==null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/login/teacher/teacherHomework";
    }

}
