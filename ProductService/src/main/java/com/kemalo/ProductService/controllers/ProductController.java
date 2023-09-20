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
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable String productId){
        return productService.getProductById(productId);
    }
    @PostMapping("/getbylist")
    public List<Product> getProductsByIdList(@RequestBody List<String> productIdList){
        return productService.getProductsByIdList(productIdList);
    }
    @GetMapping("/getall")
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/{productId}")
    public Product deleteProduct(@PathVariable String productId){
        return productService.deleteProduct(productId);
    }
    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }
}
