package com.ecommerce.dao;

import com.ecommerce.model.Product;

import java.util.List;

public interface ProductDAO {
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    Product getProductById(int productId);
    List<Product> getAllProducts();
}
