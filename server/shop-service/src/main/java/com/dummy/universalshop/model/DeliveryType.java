package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "delivery_type", schema = "shop_schema", catalog = "shop")
@org.hibernate.annotations.Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class DeliveryType extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "first_piece_default_price")
    private BigDecimal firstPieceDefaultPrice;
    @Basic
    @Column(name = "max_pieces_in_package")
    private Integer maxPiecesInPackage;

    @OneToMany(mappedBy = "deliveryType")
    private List<Delivery> deliveryList;

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

    public BigDecimal getFirstPieceDefaultPrice() {
        return firstPieceDefaultPrice;
    }

    public void setFirstPieceDefaultPrice(BigDecimal firstPieceDefaultPrice) {
        this.firstPieceDefaultPrice = firstPieceDefaultPrice;
    }

    public Integer getMaxPiecesInPackage() {
        return maxPiecesInPackage;
    }

    public void setMaxPiecesInPackage(Integer maxPiecesInPackage) {
        this.maxPiecesInPackage = maxPiecesInPackage;
    }

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryType that = (DeliveryType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (firstPieceDefaultPrice != null ? !firstPieceDefaultPrice.equals(that.firstPieceDefaultPrice) : that.firstPieceDefaultPrice != null)
            return false;
        if (maxPiecesInPackage != null ? !maxPiecesInPackage.equals(that.maxPiecesInPackage) : that.maxPiecesInPackage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (firstPieceDefaultPrice != null ? firstPieceDefaultPrice.hashCode() : 0);
        result = 31 * result + (maxPiecesInPackage != null ? maxPiecesInPackage.hashCode() : 0);
        return result;
    }
}
