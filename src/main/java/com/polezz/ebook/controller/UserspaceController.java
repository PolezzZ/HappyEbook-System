/**
 * File Name:UserspaceController.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月3日 下午10:31:15
 */
package com.polezz.ebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.jdbc.StringUtils;

/**
 * 用户主页控制器
 * 
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月3日 下午10:31:15
 */
@Controller
@RequestMapping("/u")
public class UserspaceController {
    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username) {
        System.out.println("username=" + username);
        return "userspace/u";
    }
    @GetMapping("/{username}/ebooks")
    public String listEbooksByOrder(@PathVariable("username") String username,
            @RequestParam(value = "order", required = false, defaultValue = "new") String order,
            @RequestParam(value = "category", required = false) Long category,
            @RequestParam(value = "keyword", required = false) String keyword) {
        System.out.println("username=" + username);
        System.out.println("order=" + order + ",category=" + category
                + ",keyword=" + keyword);
        if (category != null) {
            System.out.println("category=" + category);
            System.out.println("selflink:" + "redirect:/u/" + username
                    + "/ebooks?category=" + category);
            return "userspace/u";
        } else if (StringUtils.isNullOrEmpty(keyword)) {
            System.out.println("keyword=" + keyword);
            System.out.println("selflink:" + "redirect:/u/" + username
                    + "/ebooks?keyword=" + keyword);
            return "userspace/u";
        }
        return "userspace/u";
    }
    @GetMapping("/{username}/ebooks/{id}")
    public String listEbooksById(@PathVariable("id") Long id) {
        System.out.println("id=" + id);
        return "userspace/u";
    }
    @GetMapping("/{username}/ebooks/edit")
    public String editEbook() {
        return "userspace/editEbook";
    }
}