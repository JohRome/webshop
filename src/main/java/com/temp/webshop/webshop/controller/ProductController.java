package com.temp.webshop.webshop.controller;

import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.webshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Product addProductToShop(@RequestBody Product product) {
        return productService.addProductToShop(product);
    }

    @GetMapping("/{productId}")
    public Product findProductById(@PathVariable Long productId) {
        return productService.findProductById(productId);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProductsFromShop();
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product updateProductInShop(@PathVariable Long productId, @RequestBody Product product) {
        return productService.updateProductInShop(productId, product);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProductFromShop(@PathVariable Long productId) {
        productService.deleteProductFromShop(productId);
    }
}