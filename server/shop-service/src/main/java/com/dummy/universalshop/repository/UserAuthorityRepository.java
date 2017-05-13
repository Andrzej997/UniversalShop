package com.dummy.universalshop.repository;

import com.dummy.universalshop.model.UserAuthority;
import com.dummy.universalshop.model.UserAuthorityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mateusz on 13.05.2017.
 */
@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, UserAuthorityPK> {
}
