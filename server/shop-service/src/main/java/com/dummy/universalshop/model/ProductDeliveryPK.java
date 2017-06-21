package com.dummy.universalshop.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ProductDeliveryPK implements Serializable {
    @Column(name = "product_id")
    @Id
    private Long productId;
    @Column(name = "deliver_id")
    @Id
    private Long deliverId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDeliveryPK that = (ProductDeliveryPK) o;

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
