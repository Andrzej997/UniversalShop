package com.dummy.universalshop.repository;

import com.dummy.universalshop.model.ProductDelivery;
import com.dummy.universalshop.model.ProductDeliveryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDeliveryRepository extends JpaRepository<ProductDelivery, ProductDeliveryPK> {

}