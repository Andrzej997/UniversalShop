package com.dummy.universalshop.repository;

import com.dummy.universalshop.model.ObservedProduct;
import com.dummy.universalshop.model.ObservedProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservedProductRepository extends JpaRepository<ObservedProduct, ObservedProductPK> {

}