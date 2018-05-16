/**
 * File Name:CommentMapper.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月16日 下午2:25:16
 */
package com.polezz.ebook.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polezz.ebook.model.Comment;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月16日 下午2:25:16
 */
public interface CommentMapper extends JpaRepository<Comment, Long> {

}
