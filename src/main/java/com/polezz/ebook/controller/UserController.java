/**
 * File Name:UserController.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月3日 下午10:31:35
 */
package com.polezz.ebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.polezz.ebook.model.User;
import com.polezz.ebook.service.UserService;

/**
 * 用户控制器
 * 
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月3日 下午10:31:35
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据id查询用户
     * 
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("{userId}")
    public ModelAndView view(@PathVariable("userId") String userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用戶");
        return new ModelAndView("users/view", "userModel", model);
    }

    /**
     * 获取创建表单页面
     * 
     * @param model
     * @return
     */
    @GetMapping("/form")
    public ModelAndView creatForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用戶");
        return new ModelAndView("users/form", "userModel", model);
    }

    @PostMapping("/saveOrUpdateUser")
    public ModelAndView saveOrUpdateUser(User user, Model model) {
        user = userService.saveOrUpdateUser(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/deleteUser/{userId}")
    public ModelAndView deleteUser(@PathVariable("userId") String userId,
            Model model) {
        userService.deleteUser(userId);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/updateUser/{userId}")
    public ModelAndView updateUser(@PathVariable("userId") String userId,
            Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("title", "修改用戶");
        return new ModelAndView("users/form", "userModel", model);
    }
}