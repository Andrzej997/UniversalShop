package com.dummy.universalshop.repository;

import com.dummy.universalshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mateusz on 11.05.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);
}
