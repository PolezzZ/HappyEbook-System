/**
 * File Name:UserspaceController.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月3日 下午10:31:15
 */
package com.polezz.ebook.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.polezz.ebook.model.Ebook;
import com.polezz.ebook.model.User;
import com.polezz.ebook.model.Vote;
import com.polezz.ebook.service.EbookService;
import com.polezz.ebook.service.UserService;
import com.polezz.ebook.util.ConstraintViolationExceptionHandler;
import com.polezz.ebook.vo.Response;

/**
 * 用户主页控制器
 * 
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月3日 下午10:31:15
 */
@Controller
@RequestMapping("/u")
public class UserspaceController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private EbookService ebookService;

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username,
            Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return "redirect:/u/" + username + "/ebooks";
    }

    @GetMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView profile(@PathVariable("username") String username,
            Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/profile", "userModel", model);
    }

    /**
     * 保存个人设置
     * 
     * @param user
     * @param result
     * @param redirect
     * @return
     */
    @PostMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String saveProfile(@PathVariable("username") String username,
            User user) {
        User originalUser = userService.getUserById(user.getId());
        originalUser.setEmail(user.getEmail());
        originalUser.setName(user.getName());

        // 判断密码是否做了变更
        String rawPassword = originalUser.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(user.getPassword());
        boolean isMatch = encoder.matches(rawPassword, encodePasswd);
        if (!isMatch) {
            originalUser.setEncodePassword(user.getPassword());
        }

        userService.saveUser(originalUser);
        return "redirect:/u/" + username + "/profile";
    }

    /**
     * 获取编辑头像的界面
     * 
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView avatar(@PathVariable("username") String username,
            Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/avatar", "userModel", model);
    }

    /**
     * 保存编辑头像的界面
     * 
     * @param username
     * @param model
     * @return
     */
    @PostMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> saveAvatar(
            @PathVariable("username") String username, User user) {
        String avatarUrl = user.getAvatar();

        User originalUser = userService.getUserById(user.getId());
        originalUser.setAvatar(avatarUrl);
        userService.saveUser(originalUser);

        return ResponseEntity.ok().body(new Response(true, "处理成功", avatarUrl));
    }

    @SuppressWarnings("deprecation")
    @GetMapping("/{username}/ebooks")
    public String listEbooksByOrder(@PathVariable("username") String username,
            @RequestParam(value = "order", required = false, defaultValue = "new") String order,
            @RequestParam(value = "category", required = false) Long category,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "async", required = false) boolean async,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);

        if (category != null) {
            System.out.println("category=" + category);
            System.out.println("selflink:" + "redirect:/u/" + username
                    + "/ebooks?category=" + category);
            return "/u";
        }

        Page<Ebook> page = null;
        if (order.equals("hot")) {
            Sort sort = new Sort(Direction.DESC, "comments", "likes",
                    "reading");
            Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
            page = ebookService.listEbooksByTitleLikeAndSort(user, keyword,
                    pageable);
        } else if (order.equals("new")) {
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            page = ebookService.listEbooksByTitleLike(user, keyword, pageable);
        }

        List<Ebook> list = page.getContent(); // 当前所在页面数据列表
        model.addAttribute("order", order);
        model.addAttribute("page", page);
        model.addAttribute("ebookList", list);
        return (async == true
                ? "/userspace/u :: #mainContainerRepleace"
                : "/userspace/u");
    }

    /**
     * 获取电子书展示界面
     * 
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{username}/ebooks/{id}")
    public String getEbooksById(@PathVariable("username") String username,
            @PathVariable("id") Long id, Model model) {
        User principal = null;
        Ebook ebook = ebookService.getEbookById(id);

        // 每次读取，简单的可以认为阅读量增加1次
        ebookService.readingIncrease(id);

        boolean isEbookOwner = false;

        // 判断操作用户是否是博客的所有者
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication()
                        .isAuthenticated()
                && !SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal().toString().equals("anonymousUser")) {
            principal = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            if (principal != null && username.equals(principal.getUsername())) {
                isEbookOwner = true;
            }
        }

        // 判断操作用户的点赞情况
        List<Vote> votes = ebook.getVotes();
        Vote currentVote = null; // 当前用户的点赞情况

        if (principal != null) {
            for (Vote vote : votes) {
                if (vote.getUser().getUsername()
                        .equals(principal.getUsername())) {
                    currentVote = vote;
                    break;
                }
            }
        }
        model.addAttribute("isEbookOwner", isEbookOwner);
        model.addAttribute("ebookModel", ebookService.getEbookById(id));
        model.addAttribute("currentVote", currentVote);
        return "/userspace/blog";
    }

    /**
     * 删除电子书
     * 
     * @param id
     * @param model
     * @return
     */
    @DeleteMapping("/{username}/ebooks/{id}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> deleteEbook(
            @PathVariable("username") String username,
            @PathVariable("id") Long id) {

        try {
            ebookService.removeEbook(id);
        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(new Response(false, e.getMessage()));
        }

        String redirectUrl = "/u/" + username + "/ebooks";
        return ResponseEntity.ok()
                .body(new Response(true, "处理成功", redirectUrl));
    }

    /**
     * 获取新增电子书的界面
     * 
     * @param model
     * @return
     */
    @GetMapping("/{username}/ebooks/edit")
    public ModelAndView createEbook(Model model) {
        model.addAttribute("ebook", new Ebook(null, null, null));
        return new ModelAndView("/userspace/blogedit", "ebookModel", model);
    }

    /**
     * 获取编辑电子书的界面
     * 
     * @param model
     * @return
     */
    @GetMapping("/{username}/ebooks/edit/{id}")
    public ModelAndView editEbook(@PathVariable("username") String username,
            @PathVariable("id") Long id, Model model) {
        model.addAttribute("ebook", ebookService.getEbookById(id));
        return new ModelAndView("/userspace/blogedit", "ebookModel", model);
    }

    /**
     * 保存电子书
     * 
     * @param username
     * @param blog
     * @return
     */
    @PostMapping("/{username}/ebooks/edit")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> saveEbook(
            @PathVariable("username") String username,
            @RequestBody Ebook ebook) {
        System.out.println(ebook);
        User user = (User) userDetailsService.loadUserByUsername(username);
        ebook.setUser(user);
        try {
            ebookService.saveEbook(ebook);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false,
                    ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(new Response(false, e.getMessage()));
        }

        String redirectUrl = "/u/" + username + "/ebooks/" + ebook.getId();
        return ResponseEntity.ok()
                .body(new Response(true, "处理成功", redirectUrl));
    }
}