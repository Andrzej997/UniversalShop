package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Mateusz on 11.05.2017.
 */
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})}, schema = "shop_schema")
@Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    @SequenceGenerator(name = "idSeq", sequenceName = "shop_schema.id_seq", initialValue = 2,
            allocationSize = 1, schema = "shop_schema")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeq")
    private Long userId;

    @Column(name = "username", nullable = false)
    @NotNull
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "surname", nullable = false, length = 100)
    @NotNull
    private String surname;

    @Column(name = "birth_year", nullable = false)
    @NotNull
    private Date birthYear;

    @Column(name = "expiration_time")
    private Date expirationTime;

    @Column(name = "account_locked", nullable = false)
    @NotNull
    private Boolean accountLocked;

    @Column(name = "password_expiration_time")
    private Date passwordExpirationTime;

    @Column(name = "user_enabled", nullable = false)
    @NotNull
    private Boolean userEnabled;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_AUTHORITY", schema = "shop_schema", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, updatable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "authority_id", referencedColumnName = "authority_id", nullable = false, updatable = false)
    })
    private Set<Authority> authoritySet;

    @OneToMany(mappedBy = "user")
    private List<Adress> adressList;

    @OneToMany(mappedBy = "user")
    private List<Product> sellingProducts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Auction> participatingAuctions;

    @OneToMany(mappedBy = "user")
    private List<ObservedProduct> observedProducts;

    @OneToMany(mappedBy = "user")
    private List<BoughtProduct> boughtProducts;

    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> shoppingCarts;

    @Override
    protected void prePersist() {
        if (accountLocked == null) {
            accountLocked = false;
        }
        if (userEnabled == null) {
            userEnabled = false;
        }
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Date birthYear) {
        this.birthYear = birthYear;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Date getPasswordExpirationTime() {
        return passwordExpirationTime;
    }

    public void setPasswordExpirationTime(Date passwordExpirationTime) {
        this.passwordExpirationTime = passwordExpirationTime;
    }

    public Boolean getUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(Boolean userEnabled) {
        this.userEnabled = userEnabled;
    }

    public Set<Authority> getAuthoritySet() {
        return authoritySet;
    }

    public void setAuthoritySet(Set<Authority> authoritySet) {
        this.authoritySet = authoritySet;
    }

    public List<Adress> getAdressList() {
        return adressList;
    }

    public void setAdressList(List<Adress> adressList) {
        this.adressList = adressList;
    }

    public List<Product> getSellingProducts() {
        return sellingProducts;
    }

    public void setSellingProducts(List<Product> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Auction> getParticipatingAuctions() {
        return participatingAuctions;
    }

    public void setParticipatingAuctions(List<Auction> participatingAuctions) {
        this.participatingAuctions = participatingAuctions;
    }

    public List<ObservedProduct> getObservedProducts() {
        return observedProducts;
    }

    public void setObservedProducts(List<ObservedProduct> observedProducts) {
        this.observedProducts = observedProducts;
    }

    public List<BoughtProduct> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<BoughtProduct> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
