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
import org.springframework.web.bind.annotation.*;
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
                model.addAttribute("user", user.getUsername());
                return "/teacher/teacherindex";
            } else if ("student".equals(role)) {
                if (user.getStatus()) {
                    return "redirect:/login/student/";
                } else {
                    return "redirect:/login/studentClass";
                }
            } else if ("admin".equals(role)) {
                return "redirect:/login/admin";
            } else {
                model.addAttribute("message","账号有问题，请联系管理员");
                return "/";
            }
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/";
        }
    }

    //管理页面
    @GetMapping("/login/admin")
    public String admin(Model model, @PageableDefault(size = 5) Pageable pageable,HttpSession httpSession) {
        model.addAttribute("UserPage", userService.findAllUser(pageable));
        User user=(User)httpSession.getAttribute("user");
        model.addAttribute("user" ,user.getUsername());
        return "/teacher/admin";
    }

    //初始化密码
    @GetMapping("/login/admin/password/{id}")
    public String password(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        User user = userService.findUser(id);
        user.setPassword(MD5Utils.code("111111"));
        userService.add(user);
        redirectAttributes.addFlashAttribute("message", ""+user.getUsername()+"：密码重置成功");
        return "redirect:/login/admin";
    }

    @GetMapping("/login/admin/delete/{id}")
    public String delete(RedirectAttributes redirectAttributes, @PathVariable Long id) {
        User user = userService.findUser(id);
        redirectAttributes.addFlashAttribute("message", ""+user.getUsername()+"：删除成功");
        userService.deleteUser(user);
        return "redirect:/login/admin";
    }

    //未审核的账号访问的页面
    @GetMapping("/login/studentClass")
    public String studentClass(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", userService.findUser(user.getId()));
        return "/studentclass";
    }

    //重新申请进入班级
    @PostMapping("/login/ReStudentClass")
    public String ReStudentClass(@RequestParam("userclass") String userclass, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        user.setUserclass(userclass);
        userService.add(user);
        return "redirect:/login/studentClass";
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
    public String zhuCePost(Model model, User user) {
        user.setRole("student");
        user.setStatus(false);//用户未通过申请
        user.setPassword(MD5Utils.code(user.getPassword()));
        userService.add(user);
        model.addAttribute("message", "注册成功,等待老师允许进入班级");
        return "/login";
    }


}
