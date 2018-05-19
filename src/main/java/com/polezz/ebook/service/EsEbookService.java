/**
 * File Name:EsEbookService.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月19日 上午12:31:31
 */
package com.polezz.ebook.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.polezz.ebook.model.User;
import com.polezz.ebook.model.es.EsEbook;
import com.polezz.ebook.vo.TagVO;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月19日 上午12:31:31
 */
public interface EsEbookService {

    /**
     * 删除Ebook
     * 
     * @param id
     * @return
     */
    void removeEsEbook(String id);

    /**
     * 更新 EsEbook
     * 
     * @param EsEbook
     * @return
     */
    EsEbook updateEsEbook(EsEbook esEbook);

    /**
     * 根据id获取Ebook
     * 
     * @param id
     * @return
     */
    EsEbook getEsEbookByEbookId(Long ebookId);

    /**
     * 最新博客列表，分页
     * 
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsEbook> listNewestEsEbooks(String keyword, Pageable pageable);

    /**
     * 最热博客列表，分页
     * 
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsEbook> listHotestEsEbooks(String keyword, Pageable pageable);

    /**
     * 博客列表，分页
     * 
     * @param pageable
     * @return
     */
    Page<EsEbook> listEsEbooks(Pageable pageable);
    /**
     * 最新前5
     * 
     * @param keyword
     * @return
     */
    List<EsEbook> listTop5NewestEsEbooks();

    /**
     * 最热前5
     * 
     * @param keyword
     * @return
     */
    List<EsEbook> listTop5HotestEsEbooks();

    /**
     * 最热前 30 标签
     * 
     * @return
     */
    List<TagVO> listTop30Tags();

    /**
     * 最热前12用户
     * 
     * @return
     */
    List<User> listTop12Users();
}
