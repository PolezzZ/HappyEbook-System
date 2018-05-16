/**
 * File Name:CommentController.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月16日 下午2:33:27
 */
package com.polezz.ebook.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.polezz.ebook.model.Comment;
import com.polezz.ebook.model.Ebook;
import com.polezz.ebook.model.User;
import com.polezz.ebook.service.CommentService;
import com.polezz.ebook.service.EbookService;
import com.polezz.ebook.util.ConstraintViolationExceptionHandler;
import com.polezz.ebook.vo.Response;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月16日 下午2:33:27
 */
@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private EbookService ebookService;

    @Autowired
    private CommentService commentService;

    /**
     * 获取评论列表
     * 
     * @param ebookId
     * @param model
     * @return
     */
    @GetMapping
    public String listComments(
            @RequestParam(value = "ebookId", required = true) Long ebookId,
            Model model) {
        Ebook ebook = ebookService.getEbookById(ebookId);
        List<Comment> comments = ebook.getComments();

        // 判断操作用户是否是评论的所有者
        String commentOwner = "";
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication()
                        .isAuthenticated()
                && !SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal().toString().equals("anonymousUser")) {
            User principal = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            if (principal != null) {
                commentOwner = principal.getUsername();
            }
        }

        model.addAttribute("commentOwner", commentOwner);
        model.addAttribute("comments", comments);
        return "/userspace/blog :: #mainContainerRepleace";
    }
    /**
     * 发表评论
     * 
     * @param ebookId
     * @param commentContent
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')") // 指定角色权限才能操作方法
    public ResponseEntity<Response> createComment(Long ebookId,
            String commentContent) {

        try {
            ebookService.createComment(ebookId, commentContent);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false,
                    ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(new Response(false, e.getMessage()));
        }

        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }

    /**
     * 删除评论
     * 
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteBlog(@PathVariable("id") Long id,
            Long ebookId) {

        boolean isOwner = false;
        User user = commentService.getCommentById(id).getUser();

        // 判断操作用户是否是博客的所有者
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication()
                        .isAuthenticated()
                && !SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal().toString().equals("anonymousUser")) {
            User principal = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            if (principal != null
                    && user.getUsername().equals(principal.getUsername())) {
                isOwner = true;
            }
        }

        if (!isOwner) {
            return ResponseEntity.ok().body(new Response(false, "没有操作权限"));
        }

        try {
            ebookService.removeComment(ebookId, id);
            commentService.removeComment(id);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false,
                    ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(new Response(false, e.getMessage()));
        }

        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }
}
