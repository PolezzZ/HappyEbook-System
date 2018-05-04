/**
 * File Name:User.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年4月21日 下午2:22:53
 */
package com.polezz.ebook.model;

import java.io.Serializable;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年4月21日 下午2:22:53
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;// 实体的唯一标识
    private String name;
    private String email;
    public User() {

    }
    public User(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email
                + "]";
    }

}
