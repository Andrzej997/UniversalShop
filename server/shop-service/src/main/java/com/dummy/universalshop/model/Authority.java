package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Mateusz on 11.05.2017.
 */
@Entity
@Table(name = "AUTHORITY", schema = "shop_schema")
public class Authority extends BaseEntity {

    @Id
    @Column(name = "authority_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long authorityId;

    @Column(name = "authority", nullable = false)
    @NotNull
    private String authority;

    @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL)
    private List<UserAuthority> userAuthorityList;

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<UserAuthority> getUserAuthorityList() {
        return userAuthorityList;
    }

    public void setUserAuthorityList(List<UserAuthority> userAuthorityList) {
        this.userAuthorityList = userAuthorityList;
    }
}
