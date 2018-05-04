/**
 * File Name:AdminController.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月3日 下午10:31:26
 */
package com.polezz.ebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台管理控制器
 * 
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月3日 下午10:31:26
 */
@Controller
@RequestMapping("/admins")
public class AdminController {
    @GetMapping
    public ModelAndView listUsers(Model model) {
        return new ModelAndView("admins/index", "menuList", model);
    }
}
