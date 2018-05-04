/**
 * File Name:UserService.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年4月21日 下午2:36:17
 */
package com.polezz.ebook.service;

import java.util.List;

import com.polezz.ebook.model.User;


/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年4月21日 下午2:36:17
 */
public interface UserService {
    /**
     * 创建或修改用户
     * @param user
     * @return
     */
    User saveOrUpdateUser(User user);
    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Long userId);
    /**
     * 根据userId查询用户
     * @param userId
     * @return
     */
    User getUserById(Long userId);
    /**
     * 获取用户列表
     * @return
     */
    List<User> listUsers();
}
