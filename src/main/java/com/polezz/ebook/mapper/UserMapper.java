/**
 * File Name:User.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年4月21日 下午2:27:40
 */
package com.polezz.ebook.mapper;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.polezz.ebook.model.User;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年4月21日 下午2:27:40
 */
public interface UserMapper extends JpaRepository<User, Long> {

    /**
     * 根据用户名分页查询用户列表
     * 
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findByNameLike(String name, Pageable pageable);

    User findByUsername(String username);
    /**
     * 根据名称列表查询
     * 
     * @param usernames
     * @return
     */
    List<User> findByUsernameIn(Collection<String> usernames);
}
