package com.dummy.universalshop.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "AUTHORITY", schema = "shop_schema")
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "authority_id", nullable = false)
    @SequenceGenerator(name = "idSeq", sequenceName = "shop_schema.id_seq", initialValue = 2,
            allocationSize = 1, schema = "shop_schema")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSeq")
    private Long authorityId;

    @Column(name = "authority", nullable = false)
    @NotNull
    private String authority;

    @ManyToMany(mappedBy = "authoritySet", fetch = FetchType.LAZY)
    private Set<User> userSet;

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

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
