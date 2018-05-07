/**
 * File Name:AuthorityServiceImpl.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月7日 上午10:52:02
 */
package com.polezz.ebook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polezz.ebook.mapper.AuthorityMapper;
import com.polezz.ebook.model.Authority;
import com.polezz.ebook.service.AuthorityService;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月7日 上午10:52:02
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityMapper.getOne(id);
    }

}
