package com.temp.webshop.webshop.service;

import com.temp.webshop.auth.entity.Customer;
import com.temp.webshop.auth.repository.CustomerRepository;
import com.temp.webshop.webshop.entity.Cart;
import com.temp.webshop.webshop.entity.CartItem;
import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.webshop.repository.CartItemRepository;
import com.temp.webshop.webshop.repository.CartRepository;
import com.temp.webshop.webshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final PurchaseHistoryService purchaseHistoryService;

    @Transactional
    protected Customer findCustomer(String username) {

        // Find customer by username
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the customer is the correct one
        if (!customer.getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized operation");
        } else {
            return customer;
        }
    }

    @Transactional
    public void addProductToCart(String username, Long productId, int quantity) {

        Customer customer = findCustomer(username);

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
    public String getAllProductsFromCart(String username) {

        Customer customer = findCustomer(username);

        Cart cart = customer.getCart();
        double totalCost = 0;
        StringBuilder result = new StringBuilder("");
        for (CartItem cartItem : cart.getCartItems()) {
            totalCost += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            result.append("Product: ").append(cartItem.getProduct().getName())
                    .append(", Price: ").append(cartItem.getProduct().getPrice())
                    .append(", Quantity: ").append(cartItem.getQuantity())
                    .append("\n");
        }
        return result + "Total Cost: " + totalCost;
    }

    @Transactional
    public void removeProductFromCart(String username, Long productId, int quantity) {

        Customer customer = findCustomer(username);

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

    @Transactional
    public String getReceipt(String username) {
        Customer customer = findCustomer(username);

        Cart cart = customer.getCart();
        double totalCost = 0;
        StringBuilder result = new StringBuilder("");
        for (CartItem cartItem : cart.getCartItems()) {
            totalCost += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            result.append("Product: ").append(cartItem.getProduct().getName())
                    .append(", Price: ").append(cartItem.getProduct().getPrice())
                    .append(", Quantity: ").append(cartItem.getQuantity())
                    .append("\n");
        }
        purchaseHistoryService.saveReceipt(username, String.valueOf(result), totalCost);
        cartRepository.save(cart);
        customerRepository.save(customer);
        return "Thank you for shopping! Here's your receipt:\n" + result.toString() + "Total Cost: " + totalCost;
    }

    @Transactional
    public void emptyCart(String username) {
        Customer customer = findCustomer(username);
        Cart cart = customer.getCart();

        for (CartItem item : cart.getCartItems()) {
            cartItemRepository.delete(item);
        }

        cart.getCartItems().clear(); // Clear the items in the cart

        cartRepository.save(cart);
        customerRepository.save(customer);
    }
}
