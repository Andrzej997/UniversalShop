package com.dummy.universalshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})}, schema = "shop_schema")
public class User implements UserDetails, ClientDetails {

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

    @Column(name = "resource_ids", length = 256)
    private String resourceIds;

    @Column(name = "scope", length = 256)
    private String scope;

    @Column(name = "authorized_grant_types", length = 256)
    private String authorizedGrantTypes;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String getClientId() {
        return userId.toString();
    }

    @Override
    public Set<String> getResourceIds() {
        if (StringUtils.isEmpty(resourceIds)) {
            return null;
        }
        List<String> resultList = Arrays.asList(resourceIds.split("."));
        Set<String> resultSet = new HashSet<>();
        resultSet.addAll(resultList);
        return resultSet;
    }

    @Override
    public boolean isSecretRequired() {
        return this.getClientSecret() != null;
    }

    @Override
    public String getClientSecret() {
        return password;
    }

    @Override
    public boolean isScoped() {
        return getScope() != null && !getScope().isEmpty();
    }

    @Override
    public Set<String> getScope() {
        if (StringUtils.isEmpty(scope)) {
            return null;
        }
        List<String> resultList = Arrays.asList(scope.split("."));
        Set<String> resultSet = new HashSet<>();
        resultSet.addAll(resultList);
        return resultSet;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if (StringUtils.isEmpty(authorizedGrantTypes)) {
            return null;
        }
        List<String> resultList = Arrays.asList(authorizedGrantTypes.split("."));
        Set<String> resultSet = new HashSet<>();
        resultSet.addAll(resultList);
        return resultSet;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (authoritySet == null || authoritySet.isEmpty()) {
            return null;
        }
        Collection<GrantedAuthority> result = new HashSet<>();
        result.addAll(authoritySet);
        return result;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValidity;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return new Date().before(expirationTime);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return new Date().before(passwordExpirationTime);
    }

    @Override
    public boolean isEnabled() {
        return !userEnabled;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }
}
