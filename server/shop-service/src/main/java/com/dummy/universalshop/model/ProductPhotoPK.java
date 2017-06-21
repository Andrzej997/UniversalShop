package com.dummy.universalshop.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ProductPhotoPK implements Serializable {
    @Column(name = "photo_id")
    @Id
    private Long photoId;
    @Column(name = "product_id")
    @Id
    private Long productId;

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPhotoPK that = (ProductPhotoPK) o;

        if (photoId != null ? !photoId.equals(that.photoId) : that.photoId != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = photoId != null ? photoId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
}
