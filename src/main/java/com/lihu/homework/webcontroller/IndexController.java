package com.lihu.homework.webcontroller;

import com.lihu.homework.po.User;
import com.lihu.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller

public class IndexController {
    @Autowired
    private UserService userService;

    @GetMapping("person") //请求
    public String person(HttpSession httpSession) {
        User user= (User) httpSession.getAttribute("user");
        userService.findUser(user);
        return "index";
    }
}
