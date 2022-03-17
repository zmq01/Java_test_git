package com.njau.shop.controller;


import com.njau.shop.dao.ProviderDao;
import com.njau.shop.model.Provider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/provider")
public class ProviderController {
    private ProviderDao providerDao = new ProviderDao();

    /**
     * 列表
     * @return
     */
    @RequestMapping("list")
    public String list( Model model){
        //把查询的结果(list)存入model对象
        model.addAttribute("list",providerDao.selAll());
        return "providerAdmin";
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(){
        return "providerAdd";
    }
}
