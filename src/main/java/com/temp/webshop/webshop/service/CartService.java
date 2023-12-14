package com.temp.webshop.webshop.service;

import com.temp.webshop.webshop.entity.Cart;
import com.temp.webshop.webshop.entity.CartItem;
import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.authentication.entity.User;
import com.temp.webshop.webshop.repository.CartRepository;
import com.temp.webshop.webshop.repository.ProductRepository;
import com.temp.webshop.authentication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartService(UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void addProductToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            Long productId,
            int quantity) {
        // Extract username from UserDetails
        String username = userDetails.getUsername();

        // Find customer by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the customer is the correct one
        if (!user.getUsername().equals(userDetails.getUsername())) {
            throw new RuntimeException("Unauthorized operation");
        }

        // Find product by id
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Get cart from customer
        Cart cart = user.getCart();

        // Add product to cart
        List<CartItem> products = cart.getCartItems();

        for (int i = 0; i < quantity; i++) {
            var item = new CartItem();
            item.setProduct(product);
            item.setQuantity(1);
            products.add(item);
        }

        int counter = 0;
        for (CartItem cartItem : products) {
            System.out.println(counter + cartItem.getProduct().getName() + cartItem.getProduct().getPrice() + cartItem.getQuantity());
            counter++;
        }


        // Save cart
        userRepository.save(user);
    }

    // Make so Customer can remove items from cart
    @Transactional
    public void removeProductFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            Long productId,
            int quantity) {
        // Extract username from UserDetails
        String username = userDetails.getUsername();

        // Find customer by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the customer is the correct one
        if (!user.getUsername().equals(userDetails.getUsername())) {
            throw new RuntimeException("Unauthorized operation");
        }

        // Find product by id
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Get cart from customer
        Cart cart = user.getCart();

        // Add product to cart
        List<CartItem> products = cart.getCartItems();

        for (int i = 0; i < quantity; i++) {
            products.remove(products.get(products.size() - 1));
        }
        userRepository.save(user);
    }



    @Transactional
    public void checkOut(
            @AuthenticationPrincipal UserDetails userDetails) {
        // Extract username from UserDetails
        String username = userDetails.getUsername();

        // Find customer by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the customer is the correct one
        if (!user.getUsername().equals(userDetails.getUsername())) {
            throw new RuntimeException("Unauthorized operation");
        }


        // Get cart from customer
        Cart cart = user.getCart();

        List<CartItem> products = cart.getCartItems();

        for (int i = 0; i < products.size(); i++) {
            products.remove(products.get(products.size() - 1));
        }
        userRepository.save(user);

        System.out.println(String.format("Customer %s has checked out ", user.getUsername()));
    }


}
