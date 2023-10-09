package com.kemalo.ProductService.services;

import com.kemalo.ProductService.models.Product;
import com.kemalo.ProductService.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Autowired
    private final ProductRepository productRepository;
    @Override
    public Product getProductById(String productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> getProductsByIdList(List<String> productIdList) {
        return productRepository.findAllById(productIdList);
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        Product updatedProduct = productRepository.findById(product.getProductId()).get();
        updatedProduct.setProductName(
                (product.getProductName() == null || product.getProductName().isEmpty()) ? updatedProduct.getProductName(): product.getProductName());;
        updatedProduct.setProductDesc(
                (product.getProductDesc() == null || product.getProductDesc().isEmpty()) ? updatedProduct.getProductDesc(): product.getProductDesc());;
        updatedProduct.setProductAvailable(
                (product.getProductAvailable() == 0) ? updatedProduct.getProductAvailable(): product.getProductAvailable());;
        return productRepository.save(updatedProduct);
    }

    @Override
    @Transactional
    public Product deleteProduct(String productId) {
        Optional<Product> deletedProduct = productRepository.findById(productId);
        if (deletedProduct.isPresent()) {
            productRepository.deleteById(productId);
            return deletedProduct.get();
        }
        throw new RuntimeException();
    }



}
