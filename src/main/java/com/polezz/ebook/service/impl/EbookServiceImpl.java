/**
 * File Name:EbookServiceImpl.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月9日 下午1:34:07
 */
package com.polezz.ebook.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.polezz.ebook.mapper.EbookMapper;
import com.polezz.ebook.model.Catalog;
import com.polezz.ebook.model.Comment;
import com.polezz.ebook.model.Ebook;
import com.polezz.ebook.model.User;
import com.polezz.ebook.model.Vote;
import com.polezz.ebook.service.EbookService;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月9日 下午1:34:07
 */
@Service
public class EbookServiceImpl implements EbookService {

    @Autowired
    private EbookMapper ebookMapper;

    @Transactional
    @Override
    public Ebook saveEbook(Ebook ebook) {
        return ebookMapper.save(ebook);
    }

    @Transactional
    @Override
    public void removeEbook(Long id) {
        ebookMapper.deleteById(id);
    }

    @Transactional
    @Override
    public Ebook updateEbook(Ebook ebook) {
        return ebookMapper.save(ebook);
    }

    @Override
    public Ebook getEbookById(Long id) {
        return ebookMapper.getOne(id);
    }

    @Override
    public Page<Ebook> listEbooksByTitleVote(User user, String title,
            Pageable pageable) {
        Page<Ebook> ebooks = null;
        if (title == null) {
            ebooks = ebookMapper.findByUserOrderByCreateTimeDesc(user,
                    pageable);
        } else {
            title = "%" + title + "%";
            String tags = title;
            ebooks = ebookMapper
                    .findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(
                            title, user, tags, user, pageable);
        }
        return ebooks;
    }

    @Override
    public Page<Ebook> listEbooksByTitleVoteAndSort(User user, String title,
            Pageable pageable) {
        Page<Ebook> ebooks = null;
        if (title == null) {
            ebooks = ebookMapper.findByUser(user, pageable);
        } else {
            title = "%" + title + "%";
            String tags = title;
            ebooks = ebookMapper.findByTitleLikeAndUserOrTagsLikeAndUser(
                    title, user, tags, user, pageable);
        }
        return ebooks;
    }

    @Override
    public Page<Ebook> listEbooksByCatalog(Catalog catalog, Pageable pageable) {
        Page<Ebook> ebooks = ebookMapper.findByCatalog(catalog, pageable);
        return ebooks;
    }

    @Override
    public void readingIncrease(Long id) {
        Ebook ebook = ebookMapper.getOne(id);
        ebook.setReadSize(ebook.getReadSize() + 1);
        ebookMapper.save(ebook);
    }

    @Override
    public Ebook createComment(Long ebookId, String commentContent) {
        Ebook originalEbook = ebookMapper.getOne(ebookId);
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Comment comment = new Comment(user, commentContent);
        originalEbook.addComment(comment);
        return ebookMapper.save(originalEbook);
    }

    @Override
    public void removeComment(Long ebookId, Long commentId) {
        Ebook originalEbook = ebookMapper.getOne(ebookId);
        originalEbook.removeComment(commentId);
        ebookMapper.save(originalEbook);
    }

    @Override
    public Ebook createVote(Long ebookId) {
        Ebook originalEbook = ebookMapper.getOne(ebookId);
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Vote vote = new Vote(user);
        boolean isExist = originalEbook.addVote(vote);
        if (isExist) {
            throw new IllegalArgumentException("该用户已经点过赞了");
        }
        return ebookMapper.save(originalEbook);
    }

    @Override
    public void removeVote(Long ebookId, Long voteId) {
        Ebook originalEbook = ebookMapper.getOne(ebookId);
        originalEbook.removeVote(voteId);
        ebookMapper.save(originalEbook);
    }
}