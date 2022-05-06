package com.unity.stripe.payments.service;

import com.unity.stripe.payments.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(int id);



}
