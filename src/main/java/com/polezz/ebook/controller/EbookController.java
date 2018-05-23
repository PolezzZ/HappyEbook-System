/**
 * File Name:EbookController.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月3日 下午10:30:51
 */
package com.polezz.ebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.polezz.ebook.model.User;
import com.polezz.ebook.model.es.EsEbook;
import com.polezz.ebook.service.EsEbookService;
import com.polezz.ebook.vo.TagVO;

/**
 * 电子书控制器
 * 
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月3日 下午10:30:51
 */
@Controller
@RequestMapping("/ebooks")
public class EbookController {

    @Autowired
    private EsEbookService esEbookService;
    @GetMapping
    public String listEsEbooks(
            @RequestParam(value = "order", required = false, defaultValue = "new") String order,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "async", required = false) boolean async,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            Model model) {
        if (keyword != null) {
            keyword.replaceAll(" ", "");
        }
        Page<EsEbook> page = null;
        List<EsEbook> list = null;
        boolean isEmpty = true; // 系统初始化时，没有博客数据
        try {
            if (order.equals("hot")) { // 最热查询
                Sort sort = new Sort(Direction.DESC, "readSize", "commentSize",
                        "likeSize", "createTime");
                Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
                page = esEbookService.listHotestEsEbooks(keyword, pageable);
            } else if (order.equals("new")) { // 最新查询
                Sort sort = new Sort(Direction.DESC, "createTime");
                Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
                page = esEbookService.listNewestEsEbooks(keyword, pageable);
            }

            isEmpty = false;
        } catch (Exception e) {
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            page = esEbookService.listEsEbooks(pageable);
        }

        list = page.getContent(); // 当前所在页面数据列表

        model.addAttribute("order", order);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("ebookList", list);

        // 首次访问页面才加载
        if (!async && !isEmpty) {
            List<EsEbook> newest = esEbookService.listTop5NewestEsEbooks();
            model.addAttribute("newest", newest);
            List<EsEbook> hotest = esEbookService.listTop5HotestEsEbooks();
            model.addAttribute("hotest", hotest);
            List<TagVO> tags = esEbookService.listTop30Tags();
            model.addAttribute("tags", tags);
            List<User> users = esEbookService.listTop12Users();
            model.addAttribute("users", users);
        }

        return (async == true ? "/index :: #mainContainerRepleace" : "/index");
    }

    @GetMapping("/newest")
    public String listNewestEsEbooks(Model model) {
        List<EsEbook> newest = esEbookService.listTop5NewestEsEbooks();
        model.addAttribute("newest", newest);
        return "newest";
    }

    @GetMapping("/hotest")
    public String listHotestEsEbooks(Model model) {
        List<EsEbook> hotest = esEbookService.listTop5HotestEsEbooks();
        model.addAttribute("hotest", hotest);
        return "hotest";
    }
}
