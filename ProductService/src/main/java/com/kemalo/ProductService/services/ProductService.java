package com.kemalo.ProductService.services;

import com.kemalo.ProductService.models.dto.CheckoutDTO;
import com.kemalo.ProductService.models.entities.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(String productId);
    Product addProduct(Product product);
    List<Product> getProducts();
    Product updateProduct(Product product);
    Product deleteProduct(String productId);
    Boolean checkout(List<CheckoutDTO> boughtProductList);
    List<Product> addProductBatch(List<Product> products);
}
