package com.lihu.homework.webcontroller.login;

import com.lihu.homework.po.User;
import com.lihu.homework.service.PublicHomeworkService;
import com.lihu.homework.service.UserService;
import com.lihu.homework.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private PublicHomeworkService publicHomeworkService;

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @PostMapping("/loginpost")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes,
                        Model model
                        ) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            String role = user.getRole();
            /*user.setPassword(null);*/
            session.setAttribute("user", user);
            if ("teacher".equals(role)) {
                model.addAttribute("user",user.getUsername());
                return "/teacher/teacherindex";
            } else if ("student".equals(role)) {
                return "redirect:/login/student/";
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
    @GetMapping("/login/zhuce")
    public String zhuCe() {
        return "/zhuce";
    }
    @PostMapping("/zhuce")
    public String zhuCePost(Model model,User user){
        user.setRole("student");
        user.setStatus(false);//用户未通过申请
        user.setPassword(MD5Utils.code(user.getPassword()));
        userService.add(user);
        model.addAttribute("message","注册成功,等待老师允许进入班级");
        return "/login";
    }


}
