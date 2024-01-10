package com.temp.webshop.webshop.service;

import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.webshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public Product addProductToShop(Product product) {
        checkAdminRole();

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
        checkAdminRole();

        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product with id " + productId + " does not exist");
        } else {
            product.setId(productId);
            return productRepository.save(product);
        }
    }
    public void deleteProductFromShop(Long productId) {
        checkAdminRole();

        productRepository.deleteById(productId);
    }

    /**
     * Ensuring that only authenticated users with "ROLE_ADMIN" can perform admin related operations
     */
    private void checkAdminRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(r -> r.equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }
    }
}