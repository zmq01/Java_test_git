package com.njau.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    /**
     * 跳转到菜单的头
     * @return
     */
    @RequestMapping("top")
    public String top(){
        return "admin_top";
    }

    /**
     * 跳转到的左边菜单项
     * @return
     */
    @RequestMapping("left")
    public String left(){
        return "admin_left";
    }

    /**
     * 跳转到默认的首面
     * @return
     */
    @RequestMapping("content")
    public String content(){
        return "info";
    }
}
