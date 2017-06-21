package com.dummy.universalshop.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CartProductPK implements Serializable {
    @Column(name = "cart_id")
    @Id
    private Long cartId;
    @Column(name = "product_id")
    @Id
    private Long productId;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
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

        CartProductPK that = (CartProductPK) o;

        if (cartId != null ? !cartId.equals(that.cartId) : that.cartId != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartId != null ? cartId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
}
