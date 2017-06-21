package com.dummy.universalshop.repository;

import com.dummy.universalshop.model.ProductState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStateRepository extends JpaRepository<ProductState, Long> {

}