package com.temp.webshop.webshop.service;

import com.temp.webshop.webshop.entity.Cart;
import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.webshop.repository.CartRepository;
import com.temp.webshop.webshop.repository.ProductRepository;
import com.temp.webshop.authentication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void addProductToCart(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException());

        Cart cart = user.getCart();

    }

    public void addProductToCurrentUserCart(Long productId) {

    }

    public List<Product> allProductsInCart() {
        return null;
    }

    public void deleteProductFromCart(Long id) {

    }
}
