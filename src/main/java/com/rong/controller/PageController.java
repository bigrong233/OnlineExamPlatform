package com.rong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/top")
    public String top(){
        return "top";
    }
    @GetMapping("/bottom")
    public String bottom(){
        return "bottom";
    }
    @GetMapping("/left")
    public String left(){
        return "left";
    }
    @GetMapping("/right")
    public String right(){
        return "right";
    }
    @GetMapping("/updateUserMsg")
    public String updateUserMsg(){
        return "content/updateUserMsg";
    }
    @GetMapping("/joinClass")
    public String joinClass(){
        return "content/joinClazz";
    }
    @GetMapping("/findQuestion")
    public String findQuestion(){
        return "content/findQuestion";
    }
    @GetMapping("/findExam")
    public String findExam(){
        return "content/findExam";
    }

}
