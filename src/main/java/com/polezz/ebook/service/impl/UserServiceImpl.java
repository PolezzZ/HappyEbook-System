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

import org.springframework.stereotype.Service;

import com.polezz.ebook.model.User;
import com.polezz.ebook.service.UserService;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年4月21日 下午2:36:30
 */
@Service
public class UserServiceImpl implements UserService {

    private static AtomicLong counter = new AtomicLong(); 
    private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<>();

    @Override
    public User saveOrUpdateUser(User user) {
        Long id = user.getUserId();
        if(id == null) {
            id = counter.incrementAndGet();
            user.setUserId(id);
        }
        this.userMap.put(id, user);
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        this.userMap.remove(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return this.userMap.get(userId);
    }

    @Override
    public List<User> listUsers() {
        return new ArrayList<User>(this.userMap.values());
    }

}
