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
    private String id;// 实体的唯一标识
    private String name;
    private String email;
    private String username;
    private String password;
    private String avatar;
    public User() {

    }
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email
                + ", username=" + username + ", password=" + password
                + ", avatar=" + avatar + "]";
    }

}
