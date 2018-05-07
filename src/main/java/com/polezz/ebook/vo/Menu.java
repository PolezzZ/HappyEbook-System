/**
 * File Name:Menu.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月7日 上午10:41:54
 */
package com.polezz.ebook.vo;

import java.io.Serializable;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月7日 上午10:41:54
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String url;
    public Menu(String name, String url) {
        super();
        this.name = name;
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
