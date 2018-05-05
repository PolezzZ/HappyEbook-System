/**
 * File Name:response.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月5日 下午11:13:27
 */
package com.polezz.ebook.util;

/**
 * 返回对象
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月5日 下午11:13:27
 */
public class Response {
    private boolean success;
    private String message;
    private Object body;
    
    public Response(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }
    public Response(boolean success, String message, Object body) {
        super();
        this.success = success;
        this.message = message;
        this.body = body;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getBody() {
        return body;
    }
    public void setBody(Object body) {
        this.body = body;
    }
    
}
