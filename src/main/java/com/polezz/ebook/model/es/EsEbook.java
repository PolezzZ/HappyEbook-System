/**
 * File Name:EsEbook.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月19日 上午12:16:13
 */
package com.polezz.ebook.model.es;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

import com.polezz.ebook.model.Ebook;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月19日 上午12:16:13
 */
@Document(indexName = "ebook", type = "ebook")
@XmlRootElement // MediaType 转为 XML
public class EsEbook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // 主键
    private String id;
    @Field(index = FieldIndex.not_analyzed)
    private Long ebookId; // Ebook 的 id

    private String title;

    private String summary;

    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String username;
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String avatar;
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private Timestamp createTime;
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private Integer readSize = 0; // 访问量、阅读量
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private Integer commentSize = 0; // 评论量
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private Integer likeSize = 0; // 点赞量
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String fileName;
    private String tags; // 标签

    protected EsEbook() { // JPA 的规范要求无参构造函数；设为 protected 防止直接使用
    }

    public EsEbook(String title) {
        this.title = title;
    }

    public EsEbook(Long ebookId, String title, String summary, String username,
            String avatar, Timestamp createTime, Integer readSize,
            Integer commentSize, Integer likeSize, String fileName,
            String tags) {
        this.ebookId = ebookId;
        this.title = title;
        this.summary = summary;
        this.username = username;
        this.avatar = avatar;
        this.createTime = createTime;
        this.readSize = readSize;
        this.commentSize = commentSize;
        this.likeSize = likeSize;
        this.fileName = fileName;
        this.tags = tags;
    }

    public EsEbook(Ebook ebook) {
        this.ebookId = ebook.getId();
        this.title = ebook.getTitle();
        this.summary = ebook.getSummary();
        this.username = ebook.getUser().getUsername();
        this.avatar = ebook.getUser().getAvatar();
        this.createTime = ebook.getCreateTime();
        this.readSize = ebook.getReadSize();
        this.commentSize = ebook.getCommentSize();
        this.likeSize = ebook.getLikeSize();
        this.fileName = ebook.getFileName();
        this.tags = ebook.getTags();
    }

    public void update(Ebook ebook) {
        this.ebookId = ebook.getId();
        this.title = ebook.getTitle();
        this.summary = ebook.getSummary();
        this.username = ebook.getUser().getUsername();
        this.avatar = ebook.getUser().getAvatar();
        this.createTime = ebook.getCreateTime();
        this.readSize = ebook.getReadSize();
        this.commentSize = ebook.getCommentSize();
        this.likeSize = ebook.getLikeSize();
        this.fileName = ebook.getFileName();
        this.tags = ebook.getTags();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Long getEbookId() {
        return ebookId;
    }

    public void setEbookId(Long ebookId) {
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }

    public Integer getLikeSize() {
        return likeSize;
    }

    public void setLikeSize(Integer likeSize) {
        this.likeSize = likeSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTags() {
        return tags;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, title='%s']", ebookId, title);
    }
}
