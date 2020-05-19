package com.lihu.homework.webcontroller;


import com.lihu.homework.po.*;
import com.lihu.homework.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @Autowired
    private UserService userService;

    //教师首页
    @GetMapping("/teacherIndex")
    public String teacherIndex(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user.getUsername());
        return "/teacher/teacherindex";
    }

    //作业展示首页
    @GetMapping("/teacherHomework")
    public String teacherHomework(@PageableDefault(size = 5, sort = {"updatetime"}, direction = Sort.Direction.DESC)
                                          Pageable pageable, Model model, HttpSession httpSession,
                                  PublishHomework publishHomework) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user.getUsername());
        publishHomework.setUsername(user.getUsername());
        model.addAttribute("page", publicHomeworkService.listPublic(pageable, publishHomework, null));
        return "/teacher/homework";
    }

    //查询作业
    @PostMapping("/teacherHomework/search")
    public String teacherHomeworkSearch(@PageableDefault(size = 5, sort = {"updatetime"}, direction = Sort.Direction.DESC)
                                                Pageable pageable, Model model, HttpSession httpSession,
                                        PublishHomework publishHomework,
                                        @RequestParam("is") String is) {
        User user = (User) httpSession.getAttribute("user");
        publishHomework.setUsername(user.getUsername());
//        Page<PublishHomework> publishHomeworkPage = publicHomeworkService.listPublic(pageable, publishHomework, is);
        model.addAttribute("page", publicHomeworkService.listPublic(pageable, publishHomework, is));
      /*  for (PublishHomework homework : publishHomeworkPage) {
            if ("1".equals(is)&&publishHomework.getNote().equals()){
                if(homework.getIspublish()){
                    model.addAttribute("mm","查询成功");
                }else{
                    model.addAttribute("mm","您查询的不存在");
                }
            }else  if ("2".equals(is)){
                if(homework.getIspublish()){
                    model.addAttribute("mm","您查询的不存在");
                }else{
                    model.addAttribute("mm","查询成功");
                }
            }else{
                model.addAttribute("mm","您查询的不存在");
            }
        }*/
        model.addAttribute("mm", "操作成功");
        return "/teacher/homework::publishList";
    }

    //删除某个发布或者未发布的作业
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        PublishHomework one = publicHomeworkService.findOne(id);
        one.setUsername(one.getUsername() + "--delete");
        one.setNote(null);
        one.setIspublish(false);
        publicHomeworkService.deleteOne(one);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/login/teacher/teacherHomework/";
    }

    //新增作业界面
    @GetMapping("/addHomework")
    public String addHomework(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user.getUsername());
        model.addAttribute("knowledges", knowledgeService.listKnowledge());
        return "/teacher/homeworkinput";
    }

    //作业发布界面
    @GetMapping("/homeworkPublic")
    public String homeworkPublic(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("publishHomeworks", publicHomeworkService.findNoPublish(user.getUsername()));
        model.addAttribute("user", user.getUsername());
        String[] userclass = user.getUserclass().split(",");
        for (String s : userclass) {
            System.out.println(s);
        }
        model.addAttribute("userclass", userclass);
        return "/teacher/homeworkpublic";
    }

    //发布按钮
    @GetMapping("publish/{note}")
    public String publish(@PathVariable String note, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("Note", note);
        return "redirect:/login/teacher/homeworkPublic";
    }

    //批改首页
    @GetMapping("/gaizuoyeindex")
    public String gaiZuoYe(@PageableDefault(size = 5, sort = {"updatetime"}, direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user.getUsername());
        model.addAttribute("page", publicHomeworkService.listPublicGai(pageable, user.getUsername()));
        return "/teacher/gaizuoyeindex";
    }

    //查询某个人的试卷
    @GetMapping("/pigai/{id}")
    public String pigai(@PathVariable Long id, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user.getUsername());
        model.addAttribute("users", publicHomeworkService.findPiGai(id));
        model.addAttribute("publishid", id);
        model.addAttribute("scoreList", answerService.getScore(id, publicHomeworkService.findPiGai(id)));
        return "/teacher/gaizuoye";
    }

    //查询被批改的试卷，
    @PostMapping("/pigaiid")
    public String homeworkList(Model model, @RequestParam("id") String userid, @RequestParam("publishid") String publishid) {
        PublishHomework publishHomework = new PublishHomework();
        publishHomework.setId(Long.valueOf(publishid));
        //查询试卷
        model.addAttribute("testPagers", homeworkService.findPublish(publishHomework));
        for (Homework publish : homeworkService.findPublish(publishHomework)) {
            if(publish.getQuestiontype().equals("Radio")){
                model.addAttribute("hasRadio",true);
            }
            if(publish.getQuestiontype().equals("TK")){
                model.addAttribute("hasTK",true);
            }
            if(publish.getQuestiontype().equals("JD")){
                model.addAttribute("hasJD",true);
            }
        }
        User user = new User();
        user.setId(Long.valueOf(userid));
        model.addAttribute("answers", answerService.findAnswer(user, publishHomework));
        model.addAttribute("Comment", answerService.getHomeworkStatus(user, publishHomework));
        model.addAttribute("userid",user.getId());
        return "/teacher/gaizuoye :: homeworkList";
    }

    //作业批改的提交
    @PostMapping("/pigaipost")
    public String piGaiPost(@RequestParam(value = "score", required = false) String score,
                            @RequestParam(value = "answerid", required = false) String answerid,
                            @RequestParam("comment") String comment,
                            @RequestParam("publishId") String publishId,
                            @RequestParam("userid") String userid,
                            RedirectAttributes redirectAttributes,
                            HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (score != null) {
            String[] sc = score.split(",");
            String[] an = answerid.split(",");
            Long id = null;
            Answer answer = null;
            for (int i = 0; i < sc.length; i++) {
                answer = answerService.setScore(Integer.parseInt(sc[i]), Long.valueOf(an[i]));
                id = answer.getPublishHomework().getId();
            }
            answerService.setComment(comment, answer.getUser(), answer.getPublishHomework());
            redirectAttributes.addFlashAttribute("message", "操作成功:" + answer.getUser().getUsername() + "评分完成");
            String stu = "redirect:/login/teacher/pigai/" + id;
            return stu;
        } else {
            PublishHomework publishHomework = new PublishHomework();
            publishHomework.setId(Long.valueOf(publishId));
            User user1=new User();
            user1.setId(Long.valueOf(userid));
            HomeworkStatus homeworkStatus = answerService.setComment(comment, user1, publishHomework);
            Long id = homeworkStatus.getPublishHomework().getId();
            String stu = "redirect:/login/teacher/pigai/" + id;
            return stu;
        }
    }

    //班级学生展示
    @GetMapping("/banji")
    public String BanJi(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user.getUsername());
        String[] teacherClass = user.getUserclass().split(",");
        List<User> users = new ArrayList<>();
        for (String banji : teacherClass) {
            List<User> userList = userService.classUser(banji);
            if (!userList.isEmpty()) {
                for (User user1 : userList) {
                    if (!"null".equals(user1.getRole()) && user1.getRole().equals("student")) {
                        users.add(user1);
                    }
                }
            }
        }
        model.addAttribute("userList", users);
        return "/teacher/banji";
    }

    //通过学生审核进入班级
    @GetMapping("shenhe/{id}")
    public String tongGuo(@PathVariable Long id) {
        User user = userService.findUser(id);
        user.setStatus(true);
        userService.add(user);
        return "redirect:/login/teacher/banji";
    }

    //把学生T出班级
    @GetMapping("T/{id}")
    public String T(@PathVariable Long id) {
        User user = userService.findUser(id);
        user.setUserclass(null);
        user.setStatus(false);
        userService.add(user);
        return "redirect:/login/teacher/banji";
    }
}
