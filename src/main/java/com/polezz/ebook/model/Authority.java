/**
 * File Name:Authority.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月7日 上午10:47:20
 */
package com.polezz.ebook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月7日 上午10:47:20
 */
@Entity // 实体
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识
    @Column(nullable = false) // 映射为字段，值不能为空
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
