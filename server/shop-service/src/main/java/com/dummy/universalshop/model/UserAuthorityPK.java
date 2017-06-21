package com.dummy.universalshop.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Mateusz on 11.05.2017.
 */
public class UserAuthorityPK implements Serializable {

    private static final long serialVersionUID = 123456789L;

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Id
    @Column(name = "authority_id", nullable = false)
    private Long authorityId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthorityPK that = (UserAuthorityPK) o;

        return userId.equals(that.userId) && authorityId.equals(that.authorityId);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + authorityId.hashCode();
        return result;
    }
}
