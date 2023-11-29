package com.kemalo.ProductService.controllers;

import com.kemalo.ProductService.models.dto.CheckoutDTO;
import com.kemalo.ProductService.models.entities.Product;
import com.kemalo.ProductService.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @DeleteMapping("/{productId}")
    public Product deleteProduct(@PathVariable String productId){
        return productService.deleteProduct(productId);
    }
    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @PostMapping("/checkout")
    public Boolean checkout(@RequestBody List<CheckoutDTO> boughtProductList){
        return productService.checkout(boughtProductList);
    }

    @PostMapping("/product/batch")
    public List<Product> addProductBatch(@RequestBody List<Product> products){
        return productService.addProductBatch(products);
    }
}
