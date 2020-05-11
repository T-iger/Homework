package com.lihu.homework.webcontroller;


import com.lihu.homework.po.Answer;
import com.lihu.homework.po.PublishHomework;
import com.lihu.homework.po.User;
import com.lihu.homework.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Li
 **/
@Controller()
@RequestMapping("/login/teacher")
public class TeacherController {


    @Autowired
    private PublicHomeworkService publicHomeworkService;
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/teacherIndex")
    public String teacherIndex() {

        return "/teacher/teacherindex";
    }

    @GetMapping("/teacherHomework")
    public String teacherHomework(@PageableDefault(size = 5, sort = {"updatetime"}, direction = Sort.Direction.DESC)
                                          Pageable pageable, Model model) {
        model.addAttribute("page", publicHomeworkService.listPublic(pageable));
        return "/teacher/homework";
    }

    @GetMapping("/addHomework")
    public String addHomework(Model model) {
        model.addAttribute("knowledges", knowledgeService.listKnowledge());
        return "/teacher/homeworkinput";
    }

    @GetMapping("/homeworkPublic")
    public String homeworkPublic(Model model, HttpSession session) {
        model.addAttribute("publishHomeworks", publicHomeworkService.findAll());
        User user = (User) session.getAttribute("user");
        String[] userclass = user.getUserclass().split(",");
        for (String s : userclass) {
            System.out.println(s);
        }
        model.addAttribute("userclass", userclass);
        return "/teacher/homeworkpublic";
    }

    @GetMapping("/gaizuoyeindex")
    public String gaiZuoYe(@PageableDefault(size = 5, sort = {"updatetime"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("page", publicHomeworkService.listPublicGai(pageable, user.getUsername()));
        return "/teacher/gaizuoyeindex";
    }

    @GetMapping("/pigai/{id}")
    public String pigai(@PathVariable Long id, Model model) {
        model.addAttribute("users", publicHomeworkService.findPiGai(id));
        model.addAttribute("publishid", id);
        model.addAttribute("scoreList", answerService.getScore(id,publicHomeworkService.findPiGai(id)));
        return "/teacher/gaizuoye";
    }

    @PostMapping("/pigaiid")
    public String homeworkList(Model model, @RequestParam("id") String userid, @RequestParam("publishid") String publishid) {
        PublishHomework publishHomework = new PublishHomework();
        publishHomework.setId(Long.valueOf(publishid));
        //查询试卷
        model.addAttribute("testPagers", homeworkService.findPublish(publishHomework));
        User user = new User();
        user.setId(Long.valueOf(userid));
        model.addAttribute("answers", answerService.findAnswer(user, publishHomework));
        return "/teacher/gaizuoye :: homeworkList";
    }

    @PostMapping("/pigaipost")
    public String piGaiPost(@RequestParam("score") String score,
                            @RequestParam("answerid") String answerid,
                            RedirectAttributes redirectAttributes) {
        Answer answer = answerService.setScore(Integer.parseInt(score), Long.valueOf(answerid));
        Long id = answer.getPublishHomework().getId();
        redirectAttributes.addFlashAttribute("message","操作成功:"+answer.getUser().getUsername()+"评分完成");
        String stu="redirect:/login/teacher/pigai/"+id;
        return stu;
    }
}
