/**
 * File Name:Ebookmapper.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月9日 下午1:30:59
 */
package com.polezz.ebook.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.polezz.ebook.model.Catalog;
import com.polezz.ebook.model.Ebook;
import com.polezz.ebook.model.User;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月9日 下午1:30:59
 */
public interface EbookMapper extends JpaRepository<Ebook, Long> {

    /**
     * 
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Ebook> findByUserAndTitleLike(User user, String title,
            Pageable pageable);

    /**
     * 
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Ebook> findByUserAndTitleLikeOrderByCreateTimeDesc(User user,
            String title, Pageable pageable);

    /**
     * 
     * @param user
     * @param pageable
     * @return
     */
    Page<Ebook> findByUserOrderByCreateTimeDesc(User user, Pageable pageable);

    /**
     * 
     * @param user
     * @param pageable
     * @return
     */
    Page<Ebook> findByUser(User user, Pageable pageable);
    /**
     * 根据用户名分页查询用户列表
     * 
     * @param user
     * @param title
     * @param sort
     * @param pageable
     * @return
     */
    Page<Ebook> findByCatalog(Catalog catalog, Pageable pageable);
}
