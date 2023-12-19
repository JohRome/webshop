package com.temp.webshop.webshop.service;

import com.temp.webshop.auth.entity.Customer;
import com.temp.webshop.auth.repository.CustomerRepository;
import com.temp.webshop.webshop.dto.CartItemDTO;
import com.temp.webshop.webshop.entity.Cart;
import com.temp.webshop.webshop.entity.CartItem;
import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.webshop.repository.CartItemRepository;
import com.temp.webshop.webshop.repository.CartRepository;
import com.temp.webshop.webshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public void addProductToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            Long productId,
            int quantity) {

        // Extract username from UserDetails
        String username = userDetails.getUsername();

        // Find customer in db with extracted username
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check to see if it's current customer
        if (!customer.getUsername().equals(userDetails.getUsername())) {
            throw new RuntimeException("Unauthorized operation");
        }

        // Find the wanted product by id
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Get current customer's cart
        Cart cart = customer.getCart();

        // Check if product already exists in cart
        CartItem existingItem = null;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().getId().equals(product.getId())) {
                existingItem = cartItem;
                break;
            }
        }

        if (existingItem != null) {
            // If product already exists in cart, update the quantity
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // If product does not exist in cart, create new CartItem
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setCart(cart);
            cart.getCartItems().add(item);
        }

        // Save cart
        customerRepository.save(customer);
    }

    @Transactional
    public ResponseEntity<String> getAllProductsFromCart(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Customer customer = customerRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));;

        Cart cart = customer.getCart();
        double totalCost = 0;
        StringBuilder result = new StringBuilder("Items in cart: \n");
        for (CartItem cartItem : cart.getCartItems()) {
            totalCost += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            result.append("Product: ").append(cartItem.getProduct().getName())
                    .append(", Price: ").append(cartItem.getProduct().getPrice())
                    .append(", Quantity: ").append(cartItem.getQuantity())
                    //.append(", Total Cost: ").append(totalCost)
                    .append("\n");

        /*StringBuilder result = new StringBuilder("Items in cart: \n");
        for (CartItem cartItem : cart.getCartItems()) {
            result.append("Product: ").append(cartItem.getProduct().getName())
                    .append(", Price: ").append(cartItem.getProduct().getPrice())
                    .append(", Quantity: ").append(cartItem.getQuantity())
                    .append("\n");*/
        }
        String resultWithTotalPrice = result.toString() + "Total Cost: " + totalCost;
        return ResponseEntity.ok(resultWithTotalPrice);
        //return ResponseEntity.ok(result.toString());
    }

    @Transactional
    public void removeProductFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            Long productId,
            int quantity) {

        // Extract username from UserDetails
        String username = userDetails.getUsername();

        // Find customer by username
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the customer is the correct one
        if (!customer.getUsername().equals(userDetails.getUsername())) {
            throw new RuntimeException("Unauthorized operation");
        }

        // Find product by id
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Get cart from customer
        Cart cart = customer.getCart();

        // Find the cart item with the product
        CartItem itemToRemove = null;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().getName().equals(product.getName())) {
                itemToRemove = cartItem;
                break;
            }
        }

        // If the item was found, decrease the quantity or remove it if quantity becomes 0
        if (itemToRemove != null) {
            if (itemToRemove.getQuantity() > quantity) {
                itemToRemove.setQuantity(itemToRemove.getQuantity() - quantity);
                cartItemRepository.save(itemToRemove); // Save the updated CartItem
            } else {
                cart.getCartItems().remove(itemToRemove);
                cartItemRepository.delete(itemToRemove); // Delete the CartItem
            }
        }

        // Save the updated cart
        cartRepository.save(cart);
        customerRepository.save(customer);
    }
}