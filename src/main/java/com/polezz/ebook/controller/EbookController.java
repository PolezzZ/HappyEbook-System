/**
 * File Name:EbookController.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月3日 下午10:30:51
 */
package com.polezz.ebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 电子书控制器
 * 
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月3日 下午10:30:51
 */
@Controller
@RequestMapping("/ebooks")
public class EbookController {
    @GetMapping
    public String listEbooks(
            @RequestParam(value = "order", required = false, defaultValue = "new") String order,
            @RequestParam(value = "keyword", required = false) String keyword) {
        System.out.println("order=" + order + ",keyword=" + keyword);
        return "redirect:/index?order=" + order + "&keyword=" + keyword;
    }

}
