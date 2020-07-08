package com.rong.controller;

import com.rong.pojo.User;
import com.rong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/goLogin")
    public String goLogin(User user,String remember, HttpSession session){

        Integer userId = userService.login(user);

        if(userId != null){
            session.setAttribute("userMsg",userService.getUser(userId));
            session.setMaxInactiveInterval(120*60);
            session.setAttribute("loginError",null);
            session.setAttribute("remember",remember);
            return "redirect:home";

        }else{
            session.setAttribute("loginError","账号或密码错误");
            return "redirect:login";
        }
    }

    @GetMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(String email){
        boolean flag = userService.checkEmail(email);
        String result = null;
        if(flag){
            result = "该账号已存在!";
        }
        return result;
    }


    @PostMapping("/goRegister")
    @ResponseBody
    public String goRegister(String userName,String userEmail,String userPassword){
        User user = new User();
        user.setUserEmail(userEmail);
        user.setUserPassword(userPassword);
        user.setUserName(userName);

        return "" + userService.register(user);
    }
    @GetMapping("/logout")
    @ResponseBody
    public void logout(HttpSession session){
        session.invalidate();
    }

    @PostMapping("/goUpdateUser")
    @ResponseBody
    public boolean goUpdateUser(int userId,String userName,String userPassword,HttpSession session){
        User user = new User();
        user.setUserId(userId);
        user.setUserPassword(userPassword);
        user.setUserName(userName);

        boolean flag = userService.updateUser(user);
        if(flag){
            session.setAttribute("userMsg",userService.getUser(user.getUserId()));
            return true;
        }else{
            return false;
        }
    }


}
