package com.njau.shop.controller;

import com.njau.shop.dao.BillDao;
import com.njau.shop.dao.ProviderDao;
import com.njau.shop.model.Bill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bill")
public class BillController {

    private BillDao billDao = new BillDao();

    /**
     * 列表
     * @return
     */
    @RequestMapping("list")
    public String list(Bill bill,Model model){
        model.addAttribute("list",billDao.list());
        return "admin_bill_list";
    }

    /**
     * 跳转到新增页面
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(Model model){
        ProviderDao providerDao = new ProviderDao();
        //取所有供应商列表存入model对象
        model.addAttribute("list",providerDao.selAll());
        return "bill_add";
    }

    /**
     * 新增
     * @param bill
     * @return
     */
    @RequestMapping("add")
    public String add(Bill bill){
        billDao.add(bill);
        return "redirect:/bill/list";
    }
}
