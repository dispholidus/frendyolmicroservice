package com.kemalo.ProductService.services;

import com.kemalo.ProductService.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(String productId);
    List<Product> getProductsByIdList(List<String> productIdList);
    Product addProduct(Product product);
    List<Product> getProducts();
    Product updateProduct(Product product);
    Product deleteProduct(String productId);
}
