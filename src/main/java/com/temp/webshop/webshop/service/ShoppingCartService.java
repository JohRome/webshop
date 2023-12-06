package com.temp.webshop.webshop.service;

import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.webshop.entity.ShoppingCart;
import com.temp.webshop.webshop.repository.ProductRepository;
import com.temp.webshop.webshop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    public String addProductToCart(Long id) {
        Product foundProduct = productService.getOneProduct(id).orElse(null);
        return String.format("Product: %s, was added to cart. \nPrice: ",
                foundProduct.getProductName(), foundProduct.getCost());
    }

    public Product getOneProductFromCart(Long cartId, Long productId) {
        ShoppingCart selectedShoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
        return productService.getOneProduct(productId).orElse(null);
    }

    public List<Product> getAllProductsFromCart(Long cartId) {
        ShoppingCart foundShoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
        Set<Product> productsInCart = new HashSet<>();
        productsInCart = foundShoppingCart.getProducts();
        for (Product product : productsInCart) {
            System.out.println(String.format("Product: %s \nPrice: %d \nDescription: %s",
                    product.getProductName(), product.getCost(), product.getDescription()));
        }
    }

    public String updateCart(Long id, int amount) {
        //Uppdatera antal av något?
    }

    public String removeProductFromCart(Long id) { //kunna ta bort bara en?
        productService.deleteProduct(id);
        return "Product was removed";
    }
}
