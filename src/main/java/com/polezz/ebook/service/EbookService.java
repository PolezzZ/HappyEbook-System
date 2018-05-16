/**
 * File Name:EbookService.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月9日 下午1:32:24
 */
package com.polezz.ebook.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.polezz.ebook.model.Ebook;
import com.polezz.ebook.model.User;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月9日 下午1:32:24
 */
public interface EbookService {
    /**
     * 保存Ebook
     * @param Ebook
     * @return
     */
    Ebook saveEbook(Ebook ebook);
    
    /**
     * 删除Ebook
     * @param id
     * @return
     */
    void removeEbook(Long id);
    
    /**
     * 更新Ebook
     * @param Ebook
     * @return
     */
    Ebook updateEbook(Ebook ebook);
    
    /**
     * 根据id获取Ebook
     * @param id
     * @return
     */
    Ebook getEbookById(Long id);
    
    /**
     * 根据用户名进行分页模糊查询（最新）
     * @param user
     * @return
     */
    Page<Ebook> listEbooksByTitleLike(User user, String title, Pageable pageable);
 
    /**
     * 根据用户名进行分页模糊查询（最热）
     * @param user
     * @return
     */
    Page<Ebook> listEbooksByTitleLikeAndSort(User user, String title, Pageable pageable);
    
    /**
     * 阅读量递增
     * @param id
     */
    void readingIncrease(Long id);
    
    /**
     * 发表评论
     * @param blogId
     * @param commentContent
     * @return
     */
    Ebook createComment(Long blogId, String commentContent);
    
    /**
     * 删除评论
     * @param blogId
     * @param commentId
     * @return
     */
    void removeComment(Long blogId, Long commentId);
}
