/**
 * File Name:CatalogService.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月17日 下午10:58:21
 */
package com.polezz.ebook.service;

import java.util.List;

import com.polezz.ebook.model.Catalog;
import com.polezz.ebook.model.User;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月17日 下午10:58:21
 */
public interface CatalogService {
    /**
     * 保存Catalog
     * 
     * @param catalog
     * @return
     */
    Catalog saveCatalog(Catalog catalog);

    /**
     * 删除Catalog
     * 
     * @param id
     * @return
     */
    void removeCatalog(Long id);

    /**
     * 根据id获取Catalog
     * 
     * @param id
     * @return
     */
    Catalog getCatalogById(Long id);

    /**
     * 获取Catalog列表
     * 
     * @return
     */
    List<Catalog> listCatalogs(User user);
}
