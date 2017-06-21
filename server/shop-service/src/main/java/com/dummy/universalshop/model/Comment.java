package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "comment", schema = "shop_schema", catalog = "shop")
@org.hibernate.annotations.Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "inferior_comment_id")
    private Long inferiorCommentId;
    @Basic
    @Column(name = "rate")
    private BigDecimal rate;
    @Basic
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "inferior_comment_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Comment inferiorComment;

    @OneToMany(mappedBy = "inferiorComment")
    private List<Comment> commentList;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable = false, insertable = false)
    private User user;

    @OneToMany(mappedBy = "comment")
    private List<Product> products;

    @OneToMany(mappedBy = "comment")
    private List<BoughtProduct> boughtProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getInferiorCommentId() {
        return inferiorCommentId;
    }

    public void setInferiorCommentId(Long inferiorCommentId) {
        this.inferiorCommentId = inferiorCommentId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Comment getInferiorComment() {
        return inferiorComment;
    }

    public void setInferiorComment(Comment inferiorComment) {
        this.inferiorComment = inferiorComment;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<BoughtProduct> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<BoughtProduct> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        if (inferiorCommentId != null ? !inferiorCommentId.equals(comment.inferiorCommentId) : comment.inferiorCommentId != null)
            return false;
        if (rate != null ? !rate.equals(comment.rate) : comment.rate != null) return false;
        if (userId != null ? !userId.equals(comment.userId) : comment.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (inferiorCommentId != null ? inferiorCommentId.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
