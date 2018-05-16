/**
 * File Name:Ebook.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月1日 下午9:57:05
 */
package com.polezz.ebook.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;

import com.github.rjeschke.txtmark.Processor;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月1日 下午9:57:05
 */
// 标识为ES的文档
@Entity // 实体
@Document(indexName = "ebook", type = "ebook")
@SuppressWarnings("deprecation")
public class Ebook implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id;

    @NotEmpty(message = "标题不能为空")
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50) // 映射为字段，值不能为空
    private String title;

    @NotEmpty(message = "摘要不能为空")
    @Size(min = 2, max = 300)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String summary;

    @Lob // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY) // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;

    @Lob // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY) // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String htmlContent; // 将 md 转为 html

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false) // 映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp // 由数据库自动创建时间
    private Timestamp createTime;

    @Column(name = "readSize")
    private Integer readSize = 0; // 访问量、阅读量

    @Column(name = "commentSize")
    private Integer commentSize = 0; // 评论量

    @Column(name = "likeSize")
    private Integer likeSize = 0; // 点赞量

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ebook_comment", joinColumns = @JoinColumn(name = "ebook_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
    private List<Comment> comments;

    protected Ebook() {
    }

    public Ebook(String title, String summary, String content) {
        super();
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.htmlContent = Processor.process(content);
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setLikes(Integer likeSize) {
        this.likeSize = likeSize;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        this.commentSize= this.comments.size();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        this.commentSize=this.comments.size();
    }

    public void removeComment(Long commentId) {
        Iterator<Comment> iterator =  this.comments.iterator();
        while(iterator.hasNext()) {
            Comment commentIn = iterator.next();
            if(commentId.equals(commentIn.getId())) {
                iterator.remove();
            }
        }
        this.commentSize=this.comments.size();
    }

    @Override
    public String toString() {
        return "Ebook [id=" + id + ", title=" + title + ", summary=" + summary
                + ", content=" + content + ", htmlContent=" + htmlContent
                + ", user=" + user + ", createTime=" + createTime + ", readSize="
                + readSize + ", commentSize=" + commentSize + ", likeSize=" + likeSize
                + ", comments=" + comments + "]";
    }
}
