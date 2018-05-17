/**
 * File Name:VoteService.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月17日 上午11:40:59
 */
package com.polezz.ebook.service;

import com.polezz.ebook.model.Vote;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月17日 上午11:40:59
 */
public interface VoteService {
    /**
     * 根据id获取 Vote
     * 
     * @param id
     * @return
     */
    Vote getVoteById(Long id);
    /**
     * 删除Vote
     * 
     * @param id
     * @return
     */
    void removeVote(Long id);
}
