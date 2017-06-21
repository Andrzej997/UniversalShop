package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "auction", schema = "shop_schema", catalog = "shop")
@org.hibernate.annotations.Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Auction extends BaseEntity {

    @Id
    @Column(name = "auction_id")
    private Long auctionId;

    @Basic
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "product_id")
    private Long productId;

    @Basic
    @Column(name = "bid")
    private BigDecimal bid;

    @Basic
    @Column(name = "bid_time")
    private Timestamp bidTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public Timestamp getBidTime() {
        return bidTime;
    }

    public void setBidTime(Timestamp bidTime) {
        this.bidTime = bidTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auction auction = (Auction) o;

        if (auctionId != null ? !auctionId.equals(auction.auctionId) : auction.auctionId != null) return false;
        if (userId != null ? !userId.equals(auction.userId) : auction.userId != null) return false;
        if (productId != null ? !productId.equals(auction.productId) : auction.productId != null) return false;
        if (bid != null ? !bid.equals(auction.bid) : auction.bid != null) return false;
        if (bidTime != null ? !bidTime.equals(auction.bidTime) : auction.bidTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = auctionId != null ? auctionId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (bidTime != null ? bidTime.hashCode() : 0);
        return result;
    }
}
