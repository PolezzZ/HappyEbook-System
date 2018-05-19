/**
 * File Name:CommentSericeimpl.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月16日 下午2:23:45
 */
package com.polezz.ebook.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polezz.ebook.mapper.CommentMapper;
import com.polezz.ebook.model.Comment;
import com.polezz.ebook.service.CommentService;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月16日 下午2:23:45
 */
@Service
public class CommentServiceimpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public void removeComment(Long id) {
        commentMapper.delete(id);
    }
    @Override
    public Comment getCommentById(Long id) {
        return commentMapper.getOne(id);
    }
}
