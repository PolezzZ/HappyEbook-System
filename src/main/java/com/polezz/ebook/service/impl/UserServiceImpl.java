/**
 * File Name:UserServiceImpl.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年4月21日 下午2:36:30
 */
package com.polezz.ebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polezz.ebook.mapper.UserMapper;
import com.polezz.ebook.model.User;
import com.polezz.ebook.service.UserService;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年4月21日 下午2:36:30
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public User saveUser(User user) {
        return userMapper.save(user);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        userMapper.deleteById(id);
    }

    @Transactional
    @Override
    public void removeUsersInBatch(List<User> users) {
        userMapper.deleteInBatch(users);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        return userMapper.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getOne(id);
    }

    @Override
    public List<User> listUsers() {
        return userMapper.findAll();
    }

    @Override
    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        // 模糊查询
        name = "%" + name + "%";
        Page<User> users = userMapper.findByNameLike(name, pageable);
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userMapper.findByUsername(username);
    }
}
