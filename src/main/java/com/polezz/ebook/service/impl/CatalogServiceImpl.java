/**
 * File Name:CatalogServiceImpl.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月17日 下午10:58:32
 */
package com.polezz.ebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polezz.ebook.mapper.CatalogMapper;
import com.polezz.ebook.model.Catalog;
import com.polezz.ebook.model.User;
import com.polezz.ebook.service.CatalogService;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月17日 下午10:58:32
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogMapper catalogMapper;

    @Override
    public Catalog saveCatalog(Catalog catalog) {
        // 判断重复
        List<Catalog> list = catalogMapper.findByUserAndName(catalog.getUser(),
                catalog.getName());
        if (list != null && list.size() > 0) {
            throw new IllegalArgumentException("该分类已经存在了");
        }
        return catalogMapper.save(catalog);
    }

    @Override
    public void removeCatalog(Long id) {
        catalogMapper.deleteById(id);
    }

    @Override
    public Catalog getCatalogById(Long id) {
        return catalogMapper.getOne(id);
    }

    @Override
    public List<Catalog> listCatalogs(User user) {
        return catalogMapper.findByUser(user);
    }

}
