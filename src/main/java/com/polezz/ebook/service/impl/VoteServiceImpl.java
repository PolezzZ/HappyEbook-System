/**
 * File Name:VoteServiceImpl.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月17日 上午11:41:27
 */
package com.polezz.ebook.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polezz.ebook.mapper.VoteMapper;
import com.polezz.ebook.model.Vote;
import com.polezz.ebook.service.VoteService;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月17日 上午11:41:27
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteMapper voteMapper;

    @Override
    @Transactional
    public void removeVote(Long id) {
        voteMapper.deleteById(id);
    }
    @Override
    public Vote getVoteById(Long id) {
        return voteMapper.getOne(id);
    }
}
