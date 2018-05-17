/**
 * File Name:CatalogMapper.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月17日 下午10:56:50
 */
package com.polezz.ebook.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polezz.ebook.model.Catalog;
import com.polezz.ebook.model.User;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月17日 下午10:56:50
 */
public interface CatalogMapper extends JpaRepository<Catalog, Long> {

    /**
     * 根据用户查询
     * 
     * @param user
     * @return
     */
    List<Catalog> findByUser(User user);

    /**
     * 根据用户查询
     * 
     * @param user
     * @param name
     * @return
     */
    List<Catalog> findByUserAndName(User user, String name);
}
