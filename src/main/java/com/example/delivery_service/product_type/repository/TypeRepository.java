package com.example.delivery_service.product_type.repository;

import com.example.delivery_service.product_type.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<ProductType, Long> {
    ProductType findByName(String name);
}