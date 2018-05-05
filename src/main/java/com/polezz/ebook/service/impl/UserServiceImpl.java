/**
 * File Name:UserServiceImpl.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年4月21日 下午2:36:30
 */
package com.polezz.ebook.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polezz.ebook.mapper.UserMapper;
import com.polezz.ebook.model.User;
import com.polezz.ebook.service.UserService;
import com.polezz.ebook.util.UUIDUtil;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年4月21日 下午2:36:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public User saveOrUpdateUser(User user) {
        String id = user.getId();
        if(id == null) {
            id = UUIDUtil.getUUID();
            user.setId(id);
        }
        userMapper.saveOrUpdateUser(user);
        return user;
    }

    @Transactional
    @Override
    public User registerUser(User user) {
        String id = user.getId();
        if(id == null) {
            id = UUIDUtil.getUUID();
            user.setId(id);
        }
        userMapper.saveOrUpdateUser(user);
        return user;
    }

    @Transactional
    @Override
    public void deleteUser(String id) {
        userMapper.deleteUser(id);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> listUsersByNameLike(String name) {
        name = "%"+name+"%";
        return userMapper.listUsersByNameLike(name);
    }

}
