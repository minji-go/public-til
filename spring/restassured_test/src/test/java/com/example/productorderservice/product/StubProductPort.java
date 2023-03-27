package com.example.productorderservice.product;

import com.example.productorderservice.product.application.port.ProductPort;
import com.example.productorderservice.product.domain.Product;

class StubProductPort implements ProductPort {
    public Product getProduct_will_return;

    @Override
    public void save(Product product) {

    }

    @Override
    public Product getProduct(Long productId) {
        return getProduct_will_return;
    }
}
