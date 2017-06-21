package com.dummy.universalshop.repository;

import com.dummy.universalshop.model.ProductPhoto;
import com.dummy.universalshop.model.ProductPhotoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPhotoRepository extends JpaRepository<ProductPhoto, ProductPhotoPK> {

}