package com.unity.stripe.payments.dao;

import com.unity.stripe.payments.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByProductCode(String id);
    Product findById(int id);
}
