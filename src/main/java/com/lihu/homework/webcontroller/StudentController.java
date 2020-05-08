package com.lihu.homework.webcontroller;

import com.lihu.homework.po.User;
import com.lihu.homework.service.PublicHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Li
 **/
@Controller
@RequestMapping("/login/student")
public class StudentController {
    @Autowired
    private PublicHomeworkService publicHomeworkService;

    @PostMapping("showPublish")
    public String showPublish(@PageableDefault(size = 5,sort = {"updatetime"},direction = Sort.Direction.DESC)
                                          Pageable pageable, Model model, HttpSession session){
        User user=(User)session.getAttribute("user");
        model.addAttribute("page",publicHomeworkService.showListPublic(pageable,user.getId()));
        return "/student/index :: publishList";
    }




}
