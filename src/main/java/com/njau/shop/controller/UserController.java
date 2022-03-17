package com.njau.shop.controller;

import com.njau.shop.dao.UserDao;
import com.njau.shop.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserDao userDao = new UserDao();

    /**
     * 列表
     * @return
     */
    @RequestMapping("list")
    public String list(UserInfo userInfo, Model model){
        //把查询的结果(list)存入model对象
        model.addAttribute("list",userDao.selAll(userInfo));
        return "userAdmin";
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(){
        return "userAdd";
    }

    @RequestMapping("add")
    public String add(UserInfo userInfo){
        //把新增的数据存入数据库
        userDao.add(userInfo);
        //重定向到/user/list方法
        return "redirect:/user/list";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("del")
    public String del(Integer id){
        //删除
        userDao.del(id);
        //重定向到/user/list方法
        return "redirect:/user/list";
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("toUpdate")
    public String toUpdate(Integer id,Model model){
        //把加载到的记录存入model
        model.addAttribute("userInfo",userDao.getById(id));
        return "userUpdate";
    }

    /**
     * 修改
     * @param userInfo
     * @return
     */
    @RequestMapping("update")
    public String update(UserInfo userInfo){
        userDao.update(userInfo);
        //重定向到/user/list方法
        return "redirect:/user/list";
    }
}
