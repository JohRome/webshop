package com.temp.webshop.webshop.service;

import com.temp.webshop.authentication.entity.Customer;
import com.temp.webshop.webshop.entity.Product;
import com.temp.webshop.webshop.entity.ShoppingCart;
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


    public String addProductToCart(Customer user, Product product, int quantity) {
        List<Product> shoppingCart = user.getShoppingCart().getProducts();
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

    public Product getOneProductFromCart(Customer user, Long productId) {
        ShoppingCart selectedShoppingCart = user.getShoppingCart(); //hämtar userns shoppingCart
        List<Product> productsInCart = selectedShoppingCart.getProducts();
        for (Product product : productsInCart) {
            if (product.getProductId() == productId) {
                System.out.println("Product found");
                return product;
            } else {
                System.out.println("Could not find product");
            }
        }
        return productService.getOneProduct(productId).orElse(null);
    }

    public List<Product> getAllProductsFromCart(Customer user) {
        ShoppingCart foundShoppingCart = user.getShoppingCart();
        List<Product> productsInCart = foundShoppingCart.getProducts();
        for (Product product : productsInCart) {
            System.out.println(String.format("Product: %s \nPrice: %d \nDescription: %s",
                    product.getProductName(), product.getCost(), product.getDescription()));
        }
        return productsInCart;
    }

    public String updateQuantity(Customer user, Product product, int newQuantity) {
        Product foundProduct = findProductByName(user, product);
        if(foundProduct != null) {
            foundProduct.setQuantity(newQuantity);
        }
        return "Quantity changed";
    }

    public String removeProductFromCart(Long id) { //kunna ta bort bara en?
        productService.deleteProduct(id);
        return "Product was removed";
    }

    public Product findProductByName(Customer user, Product product) {
        ShoppingCart foundShoppingCart = user.getShoppingCart();
        List<Product> productsInCart = foundShoppingCart.getProducts();
        for (Product products : productsInCart) {
            if (products.getProductName().equalsIgnoreCase(product.getProductName())) {
                return product;
            } else {
                return null;
            }
        }
        return null;
    }
}
