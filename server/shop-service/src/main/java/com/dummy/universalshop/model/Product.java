package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "product", schema = "shop_schema", catalog = "shop")
@org.hibernate.annotations.Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "category_id")
    private Long categoryId;
    @Basic
    @Column(name = "state_id")
    private Long stateId;
    @Basic
    @Column(name = "weight")
    private BigDecimal weight;
    @Basic
    @Column(name = "is_auction")
    private Boolean isAuction;
    @Basic
    @Column(name = "product_count")
    private Long productCount;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "infinity_end_time")
    private Boolean infinityEndTime;
    @Basic
    @Column(name = "end_time")
    private Timestamp endTime;
    @Basic
    @Column(name = "start_date")
    private Timestamp startDate;
    @Basic
    @Column(name = "payment_id")
    private Long paymentId;
    @Basic
    @Column(name = "ean")
    private String ean;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "seller_id")
    private Long sellerId;
    @Basic
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", updatable = false, insertable = false)
    private ProductState productState;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "user_id", updatable = false, insertable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Comment comment;

    @OneToMany(mappedBy = "product")
    private List<Auction> auctions;

    @OneToMany(mappedBy = "product")
    private List<ObservedProduct> observedProducts;

    @OneToMany(mappedBy = "product")
    private List<CartProduct> cartProducts;

    @OneToMany(mappedBy = "product")
    private List<BoughtProduct> boughtProducts;

    @OneToMany(mappedBy = "product")
    private List<ProductDelivery> productDeliveries;

    @OneToMany(mappedBy = "product")
    private List<ProductPhoto> productPhotos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Boolean getAuction() {
        return isAuction;
    }

    public void setAuction(Boolean auction) {
        isAuction = auction;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getInfinityEndTime() {
        return infinityEndTime;
    }

    public void setInfinityEndTime(Boolean infinityEndTime) {
        this.infinityEndTime = infinityEndTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductState getProductState() {
        return productState;
    }

    public void setProductState(ProductState productState) {
        this.productState = productState;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public List<ObservedProduct> getObservedProducts() {
        return observedProducts;
    }

    public void setObservedProducts(List<ObservedProduct> observedProducts) {
        this.observedProducts = observedProducts;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public List<BoughtProduct> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<BoughtProduct> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public List<ProductDelivery> getProductDeliveries() {
        return productDeliveries;
    }

    public void setProductDeliveries(List<ProductDelivery> productDeliveries) {
        this.productDeliveries = productDeliveries;
    }

    public List<ProductPhoto> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<ProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (categoryId != null ? !categoryId.equals(product.categoryId) : product.categoryId != null) return false;
        if (stateId != null ? !stateId.equals(product.stateId) : product.stateId != null) return false;
        if (weight != null ? !weight.equals(product.weight) : product.weight != null) return false;
        if (isAuction != null ? !isAuction.equals(product.isAuction) : product.isAuction != null) return false;
        if (productCount != null ? !productCount.equals(product.productCount) : product.productCount != null)
            return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (infinityEndTime != null ? !infinityEndTime.equals(product.infinityEndTime) : product.infinityEndTime != null)
            return false;
        if (endTime != null ? !endTime.equals(product.endTime) : product.endTime != null) return false;
        if (startDate != null ? !startDate.equals(product.startDate) : product.startDate != null) return false;
        if (paymentId != null ? !paymentId.equals(product.paymentId) : product.paymentId != null) return false;
        if (ean != null ? !ean.equals(product.ean) : product.ean != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (sellerId != null ? !sellerId.equals(product.sellerId) : product.sellerId != null) return false;
        if (commentId != null ? !commentId.equals(product.commentId) : product.commentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (stateId != null ? stateId.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (isAuction != null ? isAuction.hashCode() : 0);
        result = 31 * result + (productCount != null ? productCount.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (infinityEndTime != null ? infinityEndTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (paymentId != null ? paymentId.hashCode() : 0);
        result = 31 * result + (ean != null ? ean.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (sellerId != null ? sellerId.hashCode() : 0);
        result = 31 * result + (commentId != null ? commentId.hashCode() : 0);
        return result;
    }
}
