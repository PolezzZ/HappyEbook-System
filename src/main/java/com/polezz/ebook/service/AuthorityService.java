/**
 * File Name:AuthorityService.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月7日 上午10:46:36
 */
package com.polezz.ebook.service;

import com.polezz.ebook.model.Authority;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月7日 上午10:46:36
 */
public interface AuthorityService {

    /**
     * 根据id获取 Authority
     * 
     * @param Authority
     * @return
     */
    Authority getAuthorityById(Long id);
}
