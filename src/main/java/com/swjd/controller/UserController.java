package com.swjd.controller;

import com.swjd.bean.User;
import com.swjd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

  //去登录
    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "login";
    }

    //做登录
    @RequestMapping("/doLogin")
    public  String doLogin(User user, Model model, HttpSession session){
       User u= userService.login(user);
        if (u!=null){
            //账号密码没问题
            if (u.getFlag().equals("1")){
                //登录成功把用户名存到session
                session.setAttribute("activeName",u.getuName());
                return "redirect:/customerController/toMain";
            }else {
                //账号被禁用了
                model.addAttribute("user",user);
                model.addAttribute("errorMsg","该账号被禁用，请联系管理员");
                return "login";
            }
        }else {
            //账号密码错了
            User user1=new User();
            model.addAttribute("user",user1);
            model.addAttribute("errorMsg","账号或者密码错误");
            return "login";
        }
    }

    //去main.jsp
    @RequestMapping("/toMain")
    public  String toMain(){
        return "main";
    }

    //退出账号
    @RequestMapping("/logOut")
    public String logOut(HttpSession session,Model model){
        session.invalidate();//让session过期的方法
        User user=new User();
        model.addAttribute("user",user);

        return "login";
    }

}
