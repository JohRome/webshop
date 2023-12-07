package com.temp.webshop.webshop.service;

import com.temp.webshop.authentication.entity.ApplicationUser;
import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.webshop.entity.ShoppingCart;
import com.temp.webshop.webshop.repository.ProductRepository;
import com.temp.webshop.webshop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    private List<Product> products;

    //User måste ha en cart kopplad till sig = @JoinColumn


    public String addProductToCart(Long cartId, Long productId, int quantity) {
        ShoppingCart selectedShoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
        Product foundProduct = productService.getOneProduct(productId).orElse(null);
        for (int i = 0; i < quantity; i++) {
            products.add(foundProduct);
        }
        return String.format("Product: %s, was added to cart. \nPrice: \nQuantity: ",
                foundProduct.getProductName(), foundProduct.getCost(), quantity);
    }

    public Product getOneProductFromCart(ApplicationUser user, Long productId) {
        ShoppingCart selectedShoppingCart = user.getShoppingCart(); //hämtar userns shoppingCart
        List<Product> productsInCart = selectedShoppingCart.getProducts();
        for (Product product : productsInCart) {
            if(product.getProductId() == productId) {
                System.out.println("Product found");
                return product;
            } else {
                System.out.println("Could not find product");
            }
        }
        return productService.getOneProduct(productId).orElse(null);
    }

    public List<Product> getAllProductsFromCart(Long cartId) {
        ShoppingCart foundShoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
        List<Product> productsInCart = new ArrayList<>();
        productsInCart = foundShoppingCart.getProducts();
        for (Product product : productsInCart) {
            System.out.println(String.format("Product: %s \nPrice: %d \nDescription: %s",
                    product.getProductName(), product.getCost(), product.getDescription()));
        }
    }

    public String updateCart(Long id, int amount) {
        //Uppdatera antal av något?
        return"";
    }

    public String removeProductFromCart(Long id) { //kunna ta bort bara en?
        productService.deleteProduct(id);
        return "Product was removed";
    }
}
