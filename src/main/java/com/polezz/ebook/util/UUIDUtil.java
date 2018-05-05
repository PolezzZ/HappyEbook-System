/**
 * File Name:UUIDUtil.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月5日 下午11:02:07
 */
package com.polezz.ebook.util;

import java.util.UUID;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月5日 下午11:02:07
 */
public class UUIDUtil {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}