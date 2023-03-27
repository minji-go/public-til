package com.example.productorderservice.product.application.port;

import com.example.productorderservice.product.domain.Product;

interface ProductPort {
    public void save(Product product);

    Product getProduct(Long productId);
}
