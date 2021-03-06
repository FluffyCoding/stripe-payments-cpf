package com.unity.stripe.payments.service.implementation;

import com.unity.stripe.payments.dao.ProductRepository;
import com.unity.stripe.payments.entity.Product;
import com.unity.stripe.payments.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id) ;
    }
}
