package com.codecool.shop.service;

import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<Product, Integer> shoppingCart;

    public Order() {
        shoppingCart = new HashMap<>();
    }

    public void addToCart(Product product, int quantity) {
        shoppingCart.put(product, quantity);
    }

    public void increaseQuantity(Product product) {
        if (shoppingCart.containsKey(product)) {
            int currentValue = shoppingCart.get(product);
            shoppingCart.put(product, currentValue + 1);
        }
    }

    public void decreaseQuantity(Product product) {
        if (shoppingCart.containsKey(product)) {
            int currentValue = shoppingCart.get(product);
            shoppingCart.put(product, currentValue - 1);
        }
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }
}
