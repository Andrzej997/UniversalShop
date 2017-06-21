package com.dummy.universalshop.repository;

import com.dummy.universalshop.model.CartProduct;
import com.dummy.universalshop.model.CartProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductPK> {

}