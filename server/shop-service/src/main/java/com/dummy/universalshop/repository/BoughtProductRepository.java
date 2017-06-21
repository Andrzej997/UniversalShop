package com.dummy.universalshop.repository;

import com.dummy.universalshop.model.BoughtProduct;
import com.dummy.universalshop.model.BoughtProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoughtProductRepository extends JpaRepository<BoughtProduct, BoughtProductPK> {

}