package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by Mateusz on 11.05.2017.
 */
@Entity
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})}, schema = "shop_schema")
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAuthority> userAuthorityList;

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

    public List<UserAuthority> getUserAuthorityList() {
        return userAuthorityList;
    }

    public void setUserAuthorityList(List<UserAuthority> userAuthorityList) {
        this.userAuthorityList = userAuthorityList;
    }
}
