package com.lihu.homework.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/indsex") //请求
    public String index() {
/*//        int i = 1 / 0;
        String blog = null;
        if (blog == null) {
            throw new NotFoundException();
        }*/
        System.out.println("------------------index-----------");
        return "index";
    }

    @GetMapping("/student")
    public String student(){
        return "index";
    }
}
