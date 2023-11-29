package com.kemalo.ProductService.services;

import com.kemalo.ProductService.models.dto.CheckoutDTO;
import com.kemalo.ProductService.models.entities.Product;
import com.kemalo.ProductService.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Autowired
    private final ProductRepository productRepository;
    @Override
    public Product getProductById(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent())
            return product.get();
        throw new RuntimeException("Product not found with given ID");
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

    @Override
    public Boolean checkout(List<CheckoutDTO> boughtProductList) {
        Product product = null;
        List<Product> products = new ArrayList<>();
        for (CheckoutDTO object: boughtProductList) {
            product = this.getProductById(object.getProductId());
            if (product.getProductAvailable() < object.getProductBought()){
                return false;
            }
            int finalProductCount = product.getProductAvailable() - object.getProductBought();
            product.setProductAvailable(finalProductCount);
            products.add(product);
        }
        productRepository.saveAll(products);
        return true;
    }

    @Override
    public List<Product> addProductBatch(List<Product> products) {
        return productRepository.saveAll(products);
    }


}
