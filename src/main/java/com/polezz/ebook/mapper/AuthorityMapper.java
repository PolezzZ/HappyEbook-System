/**
 * File Name:AuthorityMapper.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月7日 上午10:53:05
 */
package com.polezz.ebook.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polezz.ebook.model.Authority;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月7日 上午10:53:05
 */
public interface AuthorityMapper extends JpaRepository<Authority, Long> {

}
