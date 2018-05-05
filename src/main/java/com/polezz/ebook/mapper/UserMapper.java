/**
 * File Name:User.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年4月21日 下午2:27:40
 */
package com.polezz.ebook.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.polezz.ebook.model.User;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年4月21日 下午2:27:40
 */
@Repository
public interface UserMapper {
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
    void deleteUser(String userId);
    /**
     * 根据userId查询用户
     * @param userId
     * @return
     */
    User getUserById(String userId);
    /**
     * 
     * @param name
     * @return
     */
    List<User> listUsersByNameLike(String name);
}
