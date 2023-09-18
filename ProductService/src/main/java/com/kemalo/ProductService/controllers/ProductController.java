package com.kemalo.ProductService.controllers;

import com.kemalo.ProductService.models.Product;
import com.kemalo.ProductService.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/{productId}")
    public Product getProductById(@PathVariable String productId){
        return productService.getProductById(productId);
    }
    @PostMapping("/products")
    public List<Product> getProductsByIdList(@RequestBody List<String> productIdList){
        return productService.getProductsByIdList(productIdList);
    }
    @GetMapping("/product")
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/product/{productId}")
    public Product deleteProduct(@PathVariable String productId){
        return productService.deleteProduct(productId);
    }
    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }
}
