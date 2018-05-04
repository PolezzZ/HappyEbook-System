/**
 * File Name:Ebook.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月1日 下午9:57:05
 */
package com.polezz.ebook.model;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月1日 下午9:57:05
 */
// 标识为ES的文档
@Document(indexName = "ebook", type = "ebook")
public class Ebook implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ebookId;
    private String title;
    private String summary;
    private String content;
    public String getEbookId() {
        return ebookId;
    }
    public void setEbookId(String ebookId) {
        this.ebookId = ebookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString() {
        return "Ebook [ebookId=" + ebookId + ", title=" + title + ", summary="
                + summary + ", content=" + content + "]";
    }
}
