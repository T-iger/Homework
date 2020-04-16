package com.lihu.homework.web.login;

import com.lihu.homework.po.User;
import com.lihu.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Li
 **/
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            String role = user.getRole();
            user.setPassword(null);
            session.setAttribute("user", user);
            if ("teacher".equals(role)) {
                return "teacher/homeworkinput";
            } else if ("student".equals(role)) {
                return "/index";
            } else {
                return "/";
            }
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

}
