package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "payment", schema = "shop_schema", catalog = "shop")
@org.hibernate.annotations.Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Payment extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "on_delivery")
    private Boolean onDelivery;
    @Basic
    @Column(name = "bank_accunt_number")
    private String bankAccuntNumber;
    @Basic
    @Column(name = "seller_pays_for_consignment")
    private Boolean sellerPaysForConsignment;
    @Basic
    @Column(name = "seller_gives_vat_invoice")
    private Boolean sellerGivesVatInvoice;
    @Basic
    @Column(name = "sending_after")
    private Timestamp sendingAfter;

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

    public Boolean getOnDelivery() {
        return onDelivery;
    }

    public void setOnDelivery(Boolean onDelivery) {
        this.onDelivery = onDelivery;
    }

    public String getBankAccuntNumber() {
        return bankAccuntNumber;
    }

    public void setBankAccuntNumber(String bankAccuntNumber) {
        this.bankAccuntNumber = bankAccuntNumber;
    }

    public Boolean getSellerPaysForConsignment() {
        return sellerPaysForConsignment;
    }

    public void setSellerPaysForConsignment(Boolean sellerPaysForConsignment) {
        this.sellerPaysForConsignment = sellerPaysForConsignment;
    }

    public Boolean getSellerGivesVatInvoice() {
        return sellerGivesVatInvoice;
    }

    public void setSellerGivesVatInvoice(Boolean sellerGivesVatInvoice) {
        this.sellerGivesVatInvoice = sellerGivesVatInvoice;
    }

    public Timestamp getSendingAfter() {
        return sendingAfter;
    }

    public void setSendingAfter(Timestamp sendingAfter) {
        this.sendingAfter = sendingAfter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != null ? !id.equals(payment.id) : payment.id != null) return false;
        if (name != null ? !name.equals(payment.name) : payment.name != null) return false;
        if (onDelivery != null ? !onDelivery.equals(payment.onDelivery) : payment.onDelivery != null) return false;
        if (bankAccuntNumber != null ? !bankAccuntNumber.equals(payment.bankAccuntNumber) : payment.bankAccuntNumber != null)
            return false;
        if (sellerPaysForConsignment != null ? !sellerPaysForConsignment.equals(payment.sellerPaysForConsignment) : payment.sellerPaysForConsignment != null)
            return false;
        if (sellerGivesVatInvoice != null ? !sellerGivesVatInvoice.equals(payment.sellerGivesVatInvoice) : payment.sellerGivesVatInvoice != null)
            return false;
        if (sendingAfter != null ? !sendingAfter.equals(payment.sendingAfter) : payment.sendingAfter != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (onDelivery != null ? onDelivery.hashCode() : 0);
        result = 31 * result + (bankAccuntNumber != null ? bankAccuntNumber.hashCode() : 0);
        result = 31 * result + (sellerPaysForConsignment != null ? sellerPaysForConsignment.hashCode() : 0);
        result = 31 * result + (sellerGivesVatInvoice != null ? sellerGivesVatInvoice.hashCode() : 0);
        result = 31 * result + (sendingAfter != null ? sendingAfter.hashCode() : 0);
        return result;
    }
}
