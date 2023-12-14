package com.temp.webshop.webshop.service;

import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.webshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product addProductToShop(Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product with id " + productId + " does not exist"));
    }

    public List<Product> getAllProductsFromShop() {
        return productRepository.findAll();
    }

    public Product updateProductInShop(Long productId, Product product) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product with id " + productId + " does not exist");
        } else {
            product.setProductId(productId);
            return productRepository.save(product);

        }
    }

    public void deleteProductFromShop(Long productId) {
        productRepository.deleteById(productId);
    }
}