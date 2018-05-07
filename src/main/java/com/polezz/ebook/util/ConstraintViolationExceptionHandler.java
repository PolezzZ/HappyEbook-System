/**
 * File Name:ConstraintViolationExceptionHandler.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月5日 下午11:18:48
 */
package com.polezz.ebook.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * ConstraintViolationException 处理器 bean 字段异常处理 (user 不符条件)
 * 
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月5日 下午11:18:48
 */
public class ConstraintViolationExceptionHandler {
    /**
     * 获取批量异常信息
     * 
     * @param e
     * @return
     */
    public static String getMessage(ConstraintViolationException e) {
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e
                .getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = String.join(";", msgList);
        return messages;
    }
}
