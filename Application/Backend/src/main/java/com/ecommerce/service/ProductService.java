package com.ecommerce.service;

import com.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    Product getProduct(int productId);
    List<Product> getAllProducts();
}
