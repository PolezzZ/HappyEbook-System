/**
 * File Name:VoteMapper.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月17日 上午11:39:20
 */
package com.polezz.ebook.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polezz.ebook.model.Vote;


/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月17日 上午11:39:20
 */
public interface VoteMapper extends JpaRepository<Vote, Long> {

}
