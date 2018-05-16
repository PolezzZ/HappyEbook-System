/**
 * File Name:AdminController.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月3日 下午10:31:26
 */
package com.polezz.ebook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.polezz.ebook.vo.Menu;

/**
 * 后台管理控制器
 * 
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月3日 下午10:31:26
 */
@Controller
@RequestMapping("/admins")
public class AdminController {

    /**
     * 获取后台管理主页面
     * 
     * @return
     */
    @GetMapping
    public ModelAndView listUsers(Model model) {
        System.out.println("@RequestMapping(\"/admins\")");
        List<Menu> list = new ArrayList<>();
        list.add(new Menu("用户管理", "/users"));
        list.add(new Menu("角色管理", "/roles"));
        list.add(new Menu("博客管理", "/ebooks"));
        list.add(new Menu("评论管理", "/commits"));
        model.addAttribute("list", list);
        return new ModelAndView("/admins/index", "model", model);
    }
}