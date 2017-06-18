package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Paweł on 2017-05-24.
 */
@Entity
@Table(name = "ADRESS", schema = "shop_schema")
@Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Adress extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "idSeq", sequenceName = "shop_schema.id_seq", initialValue = 2,
            allocationSize = 1, schema = "shop_schema")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "street", nullable = false)
    @NotNull
    private String street;

    @Column(name = "number")
    private int number;

    @Column(name = "city", nullable = false)
    @NotNull
    private String city;

    @Column(name = "postal_code", nullable = false, length = 6)
    @NotNull
    private String postalCode;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "phone_number_2", length = 20)
    private String phoneNumber2;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
