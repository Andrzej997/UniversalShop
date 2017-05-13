package com.dummy.universalshop.repository;

import com.dummy.universalshop.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mateusz on 13.05.2017.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
