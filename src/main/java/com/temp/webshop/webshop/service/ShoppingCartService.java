package com.temp.webshop.webshop.service;

import com.temp.webshop.authentication.entity.User;
import com.temp.webshop.webshop.entity.Article;
import com.temp.webshop.webshop.entity.ShoppingCart;
import com.temp.webshop.webshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingCartService {

    @Autowired
    private CartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    private List<Article> products;

    //User måste ha en cart kopplad till sig = @JoinColumn


    public String addProductToCart(User user, Article product, int quantity) {
        List<Article> shoppingCart = user.getShoppingCart().getProducts();
        shoppingCart.add(product);
        product.setQuantity(quantity);
        return String.format("Product: %s, was added to cart. \nPrice: \nQuantity: ",
                product.getProductName(), product.getCost(), quantity);
    }

        /*ShoppingCart selectedShoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
        Product foundProduct = productService.getOneProduct(productId).orElse(null);
        for (int i = 0; i < quantity; i++) {
            products.add(foundProduct);
        }

    }*/

    public Article getOneProductFromCart(User user, Long productId) {
        ShoppingCart selectedShoppingCart = user.getShoppingCart(); //hämtar userns shoppingCart
        List<Article> productsInCart = selectedShoppingCart.getProducts();
        for (Article product : productsInCart) {
            if (product.getProductId() == productId) {
                System.out.println("Product found");
                return product;
            } else {
                System.out.println("Could not find product");
            }
        }
        return productService.getOneProduct(productId).orElse(null);
    }

    public List<Article> getAllProductsFromCart(User user) {
        ShoppingCart foundShoppingCart = user.getShoppingCart();
        List<Article> productsInCart = foundShoppingCart.getProducts();
        for (Article product : productsInCart) {
            System.out.println(String.format("Product: %s \nPrice: %d \nDescription: %s",
                    product.getProductName(), product.getCost(), product.getDescription()));
        }
        return productsInCart;
    }

    public String updateQuantity(User user, Article product, int newQuantity) {
        Article foundProduct = findProductByName(user, product);
        if(foundProduct != null) {
            foundProduct.setQuantity(newQuantity);
        }
        return "Quantity changed";
    }

    public String removeProductFromCart(Long id) { //kunna ta bort bara en?
        productService.deleteProduct(id);
        return "Product was removed";
    }

    public Article findProductByName(User user, Article product) {
        ShoppingCart foundShoppingCart = user.getShoppingCart();
        List<Article> productsInCart = foundShoppingCart.getProducts();
        for (Article products : productsInCart) {
            if (products.getProductName().equalsIgnoreCase(product.getProductName())) {
                return product;
            } else {
                return null;
            }
        }
        return null;
    }
}
