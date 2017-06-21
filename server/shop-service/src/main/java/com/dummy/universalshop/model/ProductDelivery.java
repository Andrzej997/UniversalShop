package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "product_delivery", schema = "shop_schema", catalog = "shop")
@IdClass(ProductDeliveryPK.class)
@org.hibernate.annotations.Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductDelivery extends BaseEntity {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "deliver_id")
    private Long deliverId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "deliver_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Delivery delivery;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Long deliverId) {
        this.deliverId = deliverId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDelivery that = (ProductDelivery) o;

        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (deliverId != null ? !deliverId.equals(that.deliverId) : that.deliverId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (deliverId != null ? deliverId.hashCode() : 0);
        return result;
    }
}
