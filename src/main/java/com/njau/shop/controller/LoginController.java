package com.njau.shop.controller;

import com.njau.shop.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 登录模块控制器
 * 注解:
 * @Controller: 告诉SpringBoot这是一个控制器
 * @RequestMapping: 如何访问这个控制器,value就是访问的路径
 */
@Controller
@RequestMapping("/")
public class LoginController {

    private UserDao userDao = new UserDao();

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("")
    public String toLogin(){
        //跳转到login.html
        return "login";
    }

    /**
     * 判断登录是否成功
     * @return
     */
    @RequestMapping("login")
    public String login(String username, String password, Model model, HttpSession session){
        if(userDao.isExist(username,password)){ //登录成功
            session.setAttribute("username",username);
            return "admin_index";
        }else{ //登录失败
            model.addAttribute("flag","1");
            return "login";
        }

    }



}
