package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "delivery", schema = "shop_schema", catalog = "shop")
@org.hibernate.annotations.Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Delivery extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "type_id")
    private Long typeId;
    @Basic
    @Column(name = "first_piece_price")
    private BigDecimal firstPiecePrice;
    @Basic
    @Column(name = "next_piece_price")
    private BigDecimal nextPiecePrice;
    @Basic
    @Column(name = "max_pieces_in_package")
    private Integer maxPiecesInPackage;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DeliveryType deliveryType;

    @OneToMany(mappedBy = "delivery")
    private List<CartProduct> cartProducts;

    @OneToMany(mappedBy = "delivery")
    private List<ProductDelivery> productDeliveries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public BigDecimal getFirstPiecePrice() {
        return firstPiecePrice;
    }

    public void setFirstPiecePrice(BigDecimal firstPiecePrice) {
        this.firstPiecePrice = firstPiecePrice;
    }

    public BigDecimal getNextPiecePrice() {
        return nextPiecePrice;
    }

    public void setNextPiecePrice(BigDecimal nextPiecePrice) {
        this.nextPiecePrice = nextPiecePrice;
    }

    public Integer getMaxPiecesInPackage() {
        return maxPiecesInPackage;
    }

    public void setMaxPiecesInPackage(Integer maxPiecesInPackage) {
        this.maxPiecesInPackage = maxPiecesInPackage;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public List<ProductDelivery> getProductDeliveries() {
        return productDeliveries;
    }

    public void setProductDeliveries(List<ProductDelivery> productDeliveries) {
        this.productDeliveries = productDeliveries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delivery delivery = (Delivery) o;

        if (id != null ? !id.equals(delivery.id) : delivery.id != null) return false;
        if (name != null ? !name.equals(delivery.name) : delivery.name != null) return false;
        if (typeId != null ? !typeId.equals(delivery.typeId) : delivery.typeId != null) return false;
        if (firstPiecePrice != null ? !firstPiecePrice.equals(delivery.firstPiecePrice) : delivery.firstPiecePrice != null)
            return false;
        if (nextPiecePrice != null ? !nextPiecePrice.equals(delivery.nextPiecePrice) : delivery.nextPiecePrice != null)
            return false;
        if (maxPiecesInPackage != null ? !maxPiecesInPackage.equals(delivery.maxPiecesInPackage) : delivery.maxPiecesInPackage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (firstPiecePrice != null ? firstPiecePrice.hashCode() : 0);
        result = 31 * result + (nextPiecePrice != null ? nextPiecePrice.hashCode() : 0);
        result = 31 * result + (maxPiecesInPackage != null ? maxPiecesInPackage.hashCode() : 0);
        return result;
    }
}
